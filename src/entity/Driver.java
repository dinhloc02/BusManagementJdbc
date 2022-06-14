package entity;

import impl.InfoImpl;

import java.io.Serializable;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver extends Info implements Serializable,InfoImpl,Comparable<Driver>, Comparator<Driver> {
    private int id;
    private static int autoId = 10000;
    private String level;
    public static final String LA = " Loại A";
    public static final String LB = " Loại B";
    public static final String LC = " Loại C";
    public static final String LD = " Loại D";
    public static final String LE = " Loại E";
    public static final String LF = " Loại F";

    public Driver() {

    }


    public Driver(int id,String fullName, String adress, int phoneNumber, String level) {
        super(fullName, adress, phoneNumber);
        this.id = id;
        this.level = level;
    }

    public int getId() {
        return id;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void inputInfo() {
        this.id = autoId++;
        System.out.println("Mã lái xe là :" + this.getId());
        super.inputInfo();
        System.out.println("Trình độ người lái xe là :(1.Loại A , 2.Loại B , 3.Loại C ,4.Loại D , 5.Loại E , 6.Loại F)");
        int types = 0;
        do {
            try {
                types = new Scanner(System.in).nextInt();
                if (types > 0 && types < 6) {
                    break;
                }
                System.out.println("Trình độ phải chọn từ 1 đến 6 nhập lại:");
            } catch (InputMismatchException e) {
                System.out.println("Trình độ phải chọn từ 1 đến 6 nhập lại:");
            }
        } while (true);
        switch (types) {
            case 1:
                this.setLevel(Driver.LA);
                break;
            case 2:
                this.setLevel(Driver.LB);
                break;
            case 3:
                this.setLevel(Driver.LC);
                break;
            case 4:
                this.setLevel(Driver.LD);
                break;
            case 5:
                this.setLevel(Driver.LE);
                break;
            case 6:
                this.setLevel(Driver.LF);
                break;
        }
    }


    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", level='" + level + '\'' +
                "} " + super.toString();
    }


    @Override
    public int compare(Driver o1, Driver o2) {
        return 0;
    }

    @Override
    public int compareTo(Driver o) {
        return 0;
    }
}
