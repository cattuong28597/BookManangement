package service;

import model.Book;
import model.CustomerInformation;
import writeReadFile.ReadWriteCSVFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private ReadWriteCSVFile readWriteCSVFile = new ReadWriteCSVFile();
    public void customerMenu(){
        Menu menu = new Menu();
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
                    showBooks();
                    break;
                case "2":
                    searchBooks();
                    break;
                case  "3":
                    menu.mainMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while ((choice != "2"));
    }

    public void searchBooks(){
        System.out.println("=================================================");
        System.out.print("Nhập sách bạn cần tìm: ");
        Scanner input = new Scanner(System.in);
        String searchCharacter = input.nextLine();
        ArrayList<Book> listBook = new ArrayList<Book>();
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("src\\data\\Books.csv"));
            while ((line = br.readLine()) != null) {
                List<String> csvLine = readWriteCSVFile.parseCsvLine(line);
                if(csvLine.get(0).contains(searchCharacter)){
                    Book book = new Book(csvLine.get(0),csvLine.get(1),Long.valueOf(csvLine.get(2)));
                    listBook.add(book);
                }
            }
            if(listBook.size()==0){
                System.out.println("Sách tìm kiếm không tồn tại");
            }
            else{
                for(int i=0;i<listBook.size();i++){
                    System.out.println((i+1)+". "+listBook.get(i));
                }
                System.out.println("=================================================");
                confirmBorrow(listBook);
            }
            System.out.println("=================================================");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void addBorrowInformation(Book book, CustomerInformation customer){
        Menu menu = new Menu();
        String fileName = "src\\data\\SaleInformation.csv";
        try{
            BufferedReader br = new BufferedReader(new FileReader("src\\data\\SaleInformation.csv"));
            FileWriter writer = new FileWriter(fileName,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if(br.readLine()==null){
                bufferedWriter.write(customer.getName());
                bufferedWriter.write(";");
                bufferedWriter.write(customer.getAddress());
                bufferedWriter.write(";");
                bufferedWriter.write(customer.getNumber());
                bufferedWriter.write(";");
                bufferedWriter.write(book.getName());
                bufferedWriter.write(";");
                bufferedWriter.write(book.getAuthor());
                bufferedWriter.write(";");
                String price = String.valueOf(book.getPrice());
                bufferedWriter.write(price);
                bufferedWriter.write(";");
                System.out.println("Quý khách đã đặt sách thành công");
                System.out.println("=================================================");
            }
            else{
                bufferedWriter.write("\n");
                bufferedWriter.write(customer.getName());
                bufferedWriter.write(";");
                bufferedWriter.write(customer.getAddress());
                bufferedWriter.write(";");
                bufferedWriter.write(customer.getNumber());
                bufferedWriter.write(";");
                bufferedWriter.write(book.getName());
                bufferedWriter.write(";");
                bufferedWriter.write(book.getAuthor());
                bufferedWriter.write(";");
                String price = String.valueOf(book.getPrice());
                bufferedWriter.write(price);
                bufferedWriter.write(";");
                System.out.println("Quý khách đã đặt sách thành công");
                System.out.println("=================================================");
            }
            bufferedWriter.close();
            menu.customerMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showBooks() {
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("src\\data\\Books.csv"));
            int count = 1;
            ArrayList<Book> listBook = new ArrayList<Book>();
            while ((line = br.readLine()) != null) {
                readWriteCSVFile.PrintBook(readWriteCSVFile.parseCsvLine(line),count);
                List<String> bookLine = readWriteCSVFile.parseCsvLine(line);
                listBook.add(new Book(bookLine.get(0),bookLine.get(1),Long.valueOf(bookLine.get(2))));
                count++;
            }
            System.out.println("=================================================");
            confirmBorrow(listBook);
            System.out.println("=================================================");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void confirmBorrow(ArrayList<Book> books){
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Xác nhận:");
            System.out.println("   1. Nhấn 1 để mượn sách");
            System.out.println("   2. Nhấn 2 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    borrowBook(books);
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

    public void borrowBook(ArrayList<Book> books){
        if(books.size()!=0){
            for(int i=0;i<books.size();i++){
                System.out.println((i+1)+". "+books.get(i));
            }
            System.out.print("Nhập số thứ tự sách cần mượn :");
            Scanner input = new Scanner(System.in);
            int inputIndexBook = input.nextInt();
            System.out.println("=================================================");
            createCustomerInfo(books.get(inputIndexBook-1));
        }
    }

    public void createCustomerInfo(Book book){
        System.out.println("Sách mượn : "+ book.getName()+", tác giả : "+ book.getAuthor()+", giá tiền : "+ book.getPrice());
        System.out.print("Nhập tên người mượn : ");
        Scanner input = new Scanner(System.in);
        String inputNameCustomer = input.nextLine();
        System.out.print("Nhập địa chỉ : ");
        String inputAddressBorrow = input.nextLine();
        System.out.print("Nhập số điện thoại : ");
        String inputPhoneNumberBorrow = input.nextLine();
        System.out.println("=================================================");
        CustomerInformation customerInfo = new CustomerInformation(inputNameCustomer,inputAddressBorrow,inputPhoneNumberBorrow);
        confirmBook(book,customerInfo);
    }

    public void confirmBook(Book book,CustomerInformation customer){
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Xác nhận:");
            System.out.println("   1. Nhấn 1 để xác nhận mượn sách");
            System.out.println("   2. Nhấn 2 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    addBorrowInformation(book,customer);
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
}
