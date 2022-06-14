package entity;


import java.io.Serializable;
import java.util.LinkedList;

public class AssignmentTable extends Assignment implements Comparable<AssignmentTable>, Serializable {
    private static int auto_Id = 1;
    private int id;
    private int driver_id;
    private int route_id;
    private Driver driver;
    private LinkedList<Assignment> assignments;

    public AssignmentTable( int id, int driver_id, int route_id,int quantity) {
        this.quantity = quantity;
        this.id = id;
        this.driver_id = driver_id;
        this.route_id = route_id;
    }

    public AssignmentTable(Driver driver, LinkedList<Assignment> assignments) {
        this.id = this.auto_Id++;
        this.driver = driver;
        this.assignments = assignments;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public LinkedList<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(LinkedList<Assignment> assignments) {
        this.assignments = assignments;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AssignmentTable{" +
                "id=" + id +
                ", driver=" + driver +
                ", assignments=" + assignments +
                '}';
    }

    public int compareTo(AssignmentTable assignmentTable) {
        return this.getDriver().getFullName().compareTo(assignmentTable.getDriver().getFullName());
    }

    public int getDistance() {
        if (isEmptyOrNull(assignments.toArray())) {
            return 0;
        }
        int sum = 0;
        for (Assignment assignment : assignments) {
            sum += assignment.getQuantity() * assignment.getRoute().getDistance();
        }
        return sum;
    }

    public static boolean isEmptyOrNull(Object[] obj) {
        return obj.length == 0 && obj == null;
    }
}