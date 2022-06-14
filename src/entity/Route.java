package entity;

import impl.InfoImpl;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Route implements InfoImpl, Serializable {
    private int id;
    private static int autoId = 100;
    private int distance;
    private int numberStop;

    public Route() {
    }

    public Route(int id, int distance, int numberStop) {
        this.id = id;
        this.distance = distance;
        this.numberStop = numberStop;
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getNumberStop() {
        return numberStop;
    }

    public void setNumberStop(int numberStop) {
        this.numberStop = numberStop;
    }

    public void inputInfo() {
        this.id = autoId++;
        System.out.println("Mã tuyến:" + this.getId());
        System.out.println("Nhập khoảng cách:");
        do {
            try {
                this.setDistance(new Scanner(System.in).nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Khoảng cách không được là kí tự nhập lại:");
            }
        } while (true);
        System.out.println("Số điểm dừng của tuyến:");
        do {
            try {
                this.setNumberStop(new Scanner(System.in).nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Khoảng cách không được là kí tự nhập lại:");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Buses{" +
                "id=" + id +
                ", distance=" + distance +
                ", numberStop=" + numberStop +
                '}';
    }


}
