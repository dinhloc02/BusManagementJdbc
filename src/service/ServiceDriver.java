package service;

import connectdatabase.ConnectDatabase;
import entity.Driver;
import entity.Route;
import main.Main;
import util.FileUtil;

import java.sql.*;
import java.util.*;

public class ServiceDriver {
    public static void addInfo() {
        System.out.println("Nhập số lượng lái xe cần thêm:");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                if (quantity > 0) {
                    break;
                }
                System.out.println("Số người lái xe phải lớn hơn 0 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Số người lái xe phải là số nguyên nhập lại:");
            }
        } while (true);
        for (int i = 0; i < quantity; ++i) {
            Driver driver = new Driver();
            driver.inputInfo();
           insertInfo(driver);
            FileUtil.writeDataToFile(Main.drivers, "driverdata.txt");
        }

    }

    public static void showInfo() {
        List<Driver> drivers = new LinkedList<>();
        String SQL1 = "select * from driver";
        try {
            Connection connection = ConnectDatabase.connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL1);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fullName = resultSet.getString(2);
                String adress = resultSet.getString(3);
                int phoneNumber = resultSet.getInt(4);
                String level = resultSet.getString(5);
                Driver driver = new Driver(id, fullName, adress, phoneNumber, level);
                drivers.add(driver);
                Main.drivers.add(driver);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       drivers.forEach(System.out::println);
    }

    public static boolean isempty() {
        for (Driver driver : Main.drivers) {
            if (driver != null) {
                return false;
            }
        }
        return true;
    }


    public static void insertInfo(Driver drivers) {
        String sql = "INSERT INTO driver( id,full_name ,adress ,phone_number,levell)"
                + "VALUES(?,?,?,?,?)";
        int count = 999;
        String sql1 = "Select * from driver";
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
                statement.setString(2, drivers.getFullName());
                statement.setString(3, drivers.getAdress());
                statement.setInt(4, drivers.getPhoneNumber());
                statement.setString(5, drivers.getLevel());
                statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
