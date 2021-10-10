package presentation;

import service.Admin;
import service.Customer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer();
        Admin admin = new Admin();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        System.out.println("Thư viện Cát Tường xin chào quý khách");
        do {
            System.out.println("Menu:");
            System.out.println("   1. Nhấn 1 để truy cập vào Thư viện sách");
            System.out.println("   2. Nhấn 2 để truy cập vào quyền Admin");
            System.out.println("   3. Nhấn 3 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    customer.customerMenu();
                    break;
                case "2":
                    admin.checkAdmin();
                    break;
                case "3":

                    System.exit(0);
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while ((choice != "X") && (choice != "x"));
    }
}
