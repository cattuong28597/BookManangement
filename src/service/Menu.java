package service;

import java.io.IOException;
import java.util.Scanner;


public class Menu {
    private Customer customer = new Customer();
    private Admin admin = new Admin();
    public void mainMenu() throws IOException {
        String choice = "a";
        Scanner input = new Scanner(System.in);
        System.out.println("Chào mừng bạn đến với BookCorner");
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
                    customerMenu();
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
        } while ((choice != "3"));
    }

    public void adminMenu() throws IOException {
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Menu:");
            System.out.println("   1. Nhấn 1 để xem thông tin người mua");
            System.out.println("   2. Nhấn 2 để xem kho sách");
            System.out.println("   3. Nhấn 3 để tìm sách");
            System.out.println("   4. Nhấn 4 để thêm sách");
            System.out.println("   5. Nhấn 5 để sửa thông tin sách");
            System.out.println("   6. Nhấn 6 để xóa sách");
            System.out.println("   7. Nhấn 7 để trở về Menu chính");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    admin.showSaleInfo();
                    break;
                case "2":
                    admin.readBooks();
                    break;
                case "3":
                    admin.searchBooks();
                    break;
                case "4":
                    admin.addBook();
                    break;
                case "5":
                    admin.editBookInfo();
                    break;
                case "6":
                    admin.DeleteBookInfo();
                    break;
                case  "7":
                    mainMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while ((choice != "7"));
    }

    public void customerMenu() throws IOException {
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Menu:");
            System.out.println("   1. Nhấn 1 để xem sách");
            System.out.println("   2. Nhấn 2 để tìm sách");
            System.out.println("   3. Nhấn 3 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    customer.showBooks();
                    break;
                case "2":
                    customer.searchBooks();
                    break;
                case "3":
                    mainMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while ((choice != "3"));
    }
}
