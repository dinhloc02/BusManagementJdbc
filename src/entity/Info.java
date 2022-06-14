package entity;

import impl.InfoImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Info implements InfoImpl {
    protected String fullName;
    protected String adress;
    protected int phoneNumber;

    public Info() {
    }

    public Info(String fullName, String adress, int phoneNumber) {
        this.fullName = fullName;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void inputInfo() {
        System.out.println("Nhập họ tên người lái xe:");
        this.setFullName(new Scanner(System.in).nextLine());
        System.out.println("Nhập địa chỉ người lái xe:");
        this.setAdress(new Scanner(System.in).nextLine());
        System.out.println("Nhập số điện thoại người lái xe:");
        do {
            try {
                this.setPhoneNumber(new Scanner(System.in).nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Số điện thoại không được là kí tự nhập lại:");
            }
        } while (true);
    }

    @Override
    public String toString() {
        return "Info{" +
                "fullName='" + fullName + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
