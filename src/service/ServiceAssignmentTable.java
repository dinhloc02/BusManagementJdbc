package service;

import connectdatabase.ConnectDatabase;
import entity.Assignment;
import entity.AssignmentTable;
import entity.Driver;
import entity.Route;
import main.Main;
import util.FileUtil;

import java.sql.*;
import java.util.*;

public class ServiceAssignmentTable {



    public static Driver inputDriver() {
        System.out.println("Nhập mã người lái xe cần phân công:");
        int idDriver = 0;
        Driver driver = null;
        do {
            do {
                try {
                    idDriver = new Scanner(System.in).nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Mã người lái xe phải là số nguyên nhập lại:");
                }
            } while (true);
            for (Driver driver1 : Main.drivers) {
                if (driver1.getId() == idDriver) {
                    driver = driver1;
                    break;
                }
            }
            if (driver != null) {
                break;
            }
            System.out.println("không có mã người lái xe nhập lại");
        } while (true);
        return driver;
    }

    public static List<Assignment> inputAssignment() {
        System.out.println("Nhập số lượng tuyến cần phân công");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                if (quantity > 0 && quantity < 16) {
                    break;
                }
                System.out.println("Số tuyến phân công không thể nhỏ hơn 1 và lớn hơn 15 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Số tuyến cần phân công  phải là số nguyên nhập lại:");
            }
        } while (true);
        Route route1 = null;
        List<Assignment> assignments = new LinkedList<>();
        for (int i = 0; i < quantity; ++i) {
            System.out.println("Nhập mã số tuyến cần phân công: ");
            int idRoutes = 0;
            do {
                do {
                    try {
                        idRoutes = new Scanner(System.in).nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Mã tuyến phải là số nguyên nhập lại:");
                    }
                } while (true);
                boolean check = true;
                for (Route Route : Main.routes) {
                    for (Assignment assignment1 : assignments) {
                        if (assignment1 != null && assignment1.getRoute().getId() == idRoutes) {
                            check = false;
                            break;
                        }
                    }
                    if (!check) {
                        break;
                    }
                    if (Route.getId() == idRoutes) {
                        route1 = Route;
                        break;
                    }
                }
                if (route1 != null) {
                    break;
                }
                System.out.println("Không có mã tuyến hoặc đã có mã tuyến nhập lại:");
            } while (true);
            System.out.println("Nhập số lượt cần phân công:");
            int number = 0;
            do {
                try {
                    number = new Scanner(System.in).nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Số lượt phải là số nguyên nhập lại:");
                }
            } while (true);

            Assignment assignment = new Assignment(route1, number);

            assignments.add(assignment);

        }
        return assignments;
    }

    public static void inputAssignmentTalble() {
        if (ServiceRoute.isempty()|| ServiceDriver.isempty()) {
            System.out.println("Nhập người lái xe và tuyến trước");
            return;
        }
        System.out.println("Nhập số lượng lái xe cần phân công:");
        int quantity = 0;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Số lượt phải là số nguyên nhập lại:");
            }
        } while (true);
        boolean check = false;
        for (int i = 0; i < quantity; ++i) {
            Driver driver = inputDriver();
            for (AssignmentTable assignmentTable : Main.assignmentTables) {
                if (assignmentTable != null && driver.getId() == assignmentTable.getDriver().getId()) {
                    check = true;
                    break;
                }
            }
            if (check) {
                System.out.println("lái xe đã có trong danh sách .");
                continue;
            }
            List<Assignment> assignments = inputAssignment();
            if (numberTurn((LinkedList<Assignment>) assignments) > 15) {
                System.out.println("Số lượt lái xe không được vượt quá 15.");
                continue;
            }
            AssignmentTable assignmentTable = new AssignmentTable(driver, (LinkedList<Assignment>) assignments);

            insertInfo(assignmentTable);
            FileUtil.writeDataToFile(Main.assignmentTables, "asmdata.txt");
        }

    }

    public static int numberTurn(LinkedList<Assignment> assignment) {
        int sum = 0;
        for (Assignment assignments : assignment) {
            sum += assignments.getQuantity();
        }
        return sum;
    }

    public static void sortAlbName() {
        Collections.sort(Main.assignmentTables);
        Main.assignmentTables.forEach(System.out::println);
    }

    public static void sumDistance() {
        for (AssignmentTable assignmentTable : Main.assignmentTables) {
            System.out.println("Tổng khoảng cách của người " + assignmentTable.getDriver().getFullName() + "là:" + assignmentTable.getDistance());
        }
    }

    public static void insertInfo(AssignmentTable assignmentTable) {
        String sql = "INSERT INTO assignment( id,driver_id,route_id,quantity)"
                + "VALUES(?,?,?,?)";
        int count = 0;
        String sql1 = "Select * from assignment";
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
            for(int i=0;i<assignmentTable.getAssignments().size();++i) {
                statement.setInt(1, ++count);
                statement.setInt(2, assignmentTable.getDriver().getId());
                statement.setInt(3, assignmentTable.getAssignments().get(i).getRoute().getId());
                statement.setInt(4, assignmentTable.getAssignments().get(i).getQuantity());
                statement.executeUpdate();
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void showInfo() {
        List<AssignmentTable> assignmentTables = new LinkedList<>();
        String SQL1 = "select * from assignment";
        try {
            Connection connection = ConnectDatabase.connection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL1);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int studentId= resultSet.getInt(2);
                int routeId = resultSet.getInt(3);
                int quantity = resultSet.getInt(4);
                AssignmentTable assignmentTable = new AssignmentTable(id,studentId,routeId,quantity);
                assignmentTables.add(assignmentTable);
            }
            connection.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assignmentTables.forEach(System.out::println);
    }
}


