package service;

import model.Book;
import model.CustomerInformation;
import model.SaleInformation;
import repository.BookRepository;
import repository.InformationCustomerRepository;
import writeReadFile.ReadWriteCSVFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    public static Scanner input = new Scanner(System.in);
    private ReadWriteCSVFile readWriteCSVFile = new ReadWriteCSVFile();
    BookRepository bookRepository = new BookRepository();
//    InformationCustomerRepository informationCustomerRepository = new InformationCustomerRepository();

    public void customerMenu() throws IOException {
        Menu menu = new Menu();
        menu.customerMenu();
    }

    public void showBooks() throws IOException {
       ArrayList<Book> books = new ArrayList<>(bookRepository.getBooks());
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i+1+"."+books.get(i));
        }
        chooseBookToBuy(books);
    }

    public void searchBooks() throws IOException {
        Menu menu = new Menu();
        System.out.println("=================================================");
        System.out.print("Nhập sách bạn cần tìm: ");
        Scanner input = new Scanner(System.in);
        String searchCharacter = input.nextLine();
        ArrayList<Book> books = new ArrayList<Book>(bookRepository.getBooks());
        ArrayList<Book> bookAfterSearch = new ArrayList<>();
        for (Book book: books) {
            if (book.getName().contains(searchCharacter)) {
                bookAfterSearch.add(book);
            }
        }
        if (bookAfterSearch.size() == 0) {
            System.out.println("Không có sách cần tìm khiếm.");
            menu.customerMenu();

        } else {
            for (Book bookSearch: bookAfterSearch) {
                System.out.println(bookSearch);
            }
            chooseBookToBuy(bookAfterSearch);
        }
    }


    public void SaleBookInformation(SaleInformation saleInformation) throws IOException {
        Menu menu = new Menu();
        String filePath = "src/data/SaleInformation.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            FileWriter writer = new FileWriter(filePath,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if (br.readLine() != null) {
                bufferedWriter.write("\n");
            }
            bufferedWriter.write(saleInformation.toString());
            bufferedWriter.close();
            menu.customerMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void chooseBookToBuy(ArrayList<Book> books) throws IOException {
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Xác nhận:");
            System.out.println("   1. Nhấn 1 để đặt mua sách");
            System.out.println("   2. Nhấn 2 để hủy");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    buyBook(books);
                    break;
                case "2":
                    menu.customerMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while (choice != "2");
    }

    public void buyBook(ArrayList<Book> books) throws IOException {
        try {
            if(books.size()!=0){
                for(int i=0;i<books.size();i++){
                    System.out.println((i+1)+". "+books.get(i));
                }
                System.out.print("Nhập số thứ tự sách cần mua :");
                Scanner input = new Scanner(System.in);
                int inputIndexBook = input.nextInt();
                System.out.println("=================================================");
                createCustomerInfo(books.get(inputIndexBook-1));
            }
        } catch (Exception e) {
            System.out.println("Nhập lại số thứ tự sách cần mua");
        }

    }

    public boolean isFormatNameCustomer(String name) {
        String REGEXT_NAME = "^([A-Z]+[a-z]*[ ]?)+$";
        Pattern pattern = Pattern.compile(REGEXT_NAME);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public boolean isFormatPhoneCustomer(String phone) {
        String REGEXT_PHONE = "^[0][1-9][0-9]{8}$";
        Pattern pattern = Pattern.compile(REGEXT_PHONE);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public void createCustomerInfo(Book book) throws IOException {
        String inputNameCustomer;
        String inputAddressCustomer;
        String inputPhoneNumberCustomer;
        String dateBuy;
        System.out.println("Sách mua : "+ book.getName()+", tác giả : "+ book.getAuthor()+", giá tiền : "+ book.getPrice());
        do {
            System.out.print("Nhập tên người mua : ");
            inputNameCustomer = input.nextLine();
            if (!isFormatNameCustomer(inputNameCustomer)) {
                System.out.println("Không đúng định dạng (Mẫu: Nguyen Van A)");
            }
        } while (!isFormatNameCustomer(inputNameCustomer));


        System.out.print("Nhập địa chỉ : ");
        inputAddressCustomer = input.nextLine();

        do {
            System.out.print("Nhập số điện thoại : ");
            inputPhoneNumberCustomer = input.nextLine();
            if (!isFormatPhoneCustomer(inputPhoneNumberCustomer)) {
                System.out.println("Không đúng định dạng (Mẫu: 0123456789)");
            }
        } while (!isFormatPhoneCustomer(inputPhoneNumberCustomer));

        dateBuy = String.valueOf(java.time.LocalDate.now());

        System.out.println("=================================================");
        SaleInformation saleInformation = new SaleInformation(inputNameCustomer,inputAddressCustomer,
                inputPhoneNumberCustomer, book.getName(), book.getAuthor(), book.getPrice(), dateBuy);
        confirmBook(saleInformation);
    }

    public void confirmBook(SaleInformation saleInformation) throws IOException {
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Xác nhận:");
            System.out.println("   1. Nhấn 1 để xác nhận mua sách");
            System.out.println("   2. Nhấn 2 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    SaleBookInformation(saleInformation);
                    break;
                case "2":
                    menu.customerMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiếp tục");
                    System.out.println("=================================================");
            }
        } while (choice != "2");
    }
}
