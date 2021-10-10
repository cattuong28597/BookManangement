package service;

import java.util.Scanner;


public class Menu {
    private Customer customer = new Customer();
    private Admin admin = new Admin();
    public void mainMenu(){
        String choice = "a";
        Scanner input = new Scanner(System.in);
        System.out.println("Thư viện Cát Tường xin chào quý khách");
        do {
            System.out.println("Menu:");
            System.out.println("   1. Nhấn 1 để truy cập vào Thư viện sách");
            System.out.println("   2. Nhấn 2 để truy cập vào quyền Admin");
            System.out.println("   3. Nhấn X để thoát");
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
        } while ((choice != "2"));
    }

    public void adminMenu(){
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Menu:");
            System.out.println("   1. Nhấn 1 để xem thông tin người mượn");
            System.out.println("   2. Nhấn 2 để xem kho sách");
            System.out.println("   3. Nhấn 3 để thêm sách");
            System.out.println("   4. Nhấn 4 để sửa thông tin sách");
            System.out.println("   5. Nhấn 5 để cho mượn sách");
            System.out.println("   6. Nhấn X để trở về Menu chính");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    admin.readBorrowInformation();
                    break;
                case "2":
                    admin.readBooks();
                    break;
                case "3":
                    admin.addBook();
                    break;
                case "4":

                    break;
                case "5":

                    break;
                case  "6":
                    mainMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while ((choice != "2"));
    }

    public void customerMenu(){
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
        } while ((choice != "2"));
    }
}
