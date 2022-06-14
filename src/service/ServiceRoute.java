package service;

import connectdatabase.ConnectDatabase;
import entity.Route;
import main.Main;
import util.FileUtil;

import java.io.Serializable;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ServiceRoute implements Serializable {


    public static void addInfo() {
        System.out.println("Nhập số lượng tuyến cần thêm:");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                if (quantity > 0) {
                    break;
                }
                System.out.println("Số người tuyến phải lớn hơn 0 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Số người tuyến phải là số nguyên nhập lại:");
            }
        } while (true);
        for (int i = 0; i < quantity; ++i) {
            Route route = new Route();
            route.inputInfo();
          insertInfo(route);
            FileUtil.writeDataToFile(Main.routes, "Routesdata.txt");
        }

    }

    public static void showInfo() {
        List<Route> routes = new LinkedList<>();
        String SQL1 = "select * from route";
        try {
            Connection connection = ConnectDatabase.connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL1);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int distance = resultSet.getInt(2);
                int numberStop = resultSet.getInt(3);
                Route route = new Route(id, distance, numberStop);
                routes.add(route);
                Main.routes.add(route);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
      routes.forEach(System.out::println);
    }



    public static void insertInfo(Route routes) {
        String sql = "INSERT INTO route( id,distance,numberstop)"
                + "VALUES(?,?,?)";
        int count = 99;
        String sql1 = "Select * from route";
        try {
            Connection connection = ConnectDatabase.connection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql1);
            while (rs.next()) {
                count = rs.getInt(1);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {

            Connection connection = ConnectDatabase.connection();
            PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, count + 1);
                statement.setInt(2, routes.getDistance());
                statement.setInt(3, routes.getNumberStop());
                statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean isempty() {
        for (Route Route : Main.routes) {
            if (Route != null) {
                return false;
            }
        }
        return true;
    }

}

