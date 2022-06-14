package main;

import entity.AssignmentTable;
import entity.Driver;
import entity.Route;

import java.util.LinkedList;

public class Main {
    public static LinkedList<Driver> drivers = new LinkedList<>();
    public static LinkedList<Route> routes = new LinkedList<>();
    public static LinkedList<AssignmentTable> assignmentTables = new LinkedList<>();

    public static void main(String[] args) {
        ServiceMain.menu();
    }
}
