package service;
import model.Book;
import model.CustomerInformation;
import writeReadFile.ReadWriteCSVFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private ReadWriteCSVFile readWriteCSVFile = new ReadWriteCSVFile();
    public void checkAdmin(){
        System.out.println("=================================================");
        Scanner input = new Scanner(System.in);
        System.out.println("Mời bạn nhập mã để truy cập quyên admin : ");
        String inputPass = input.nextLine();
        while(!inputPass.equals("admin")){
            System.out.println("Thông tin mã nhập sai, vui lòng nhập lại : ");
            inputPass = input.nextLine();
        }
        adminMenu();
    }

    public void adminMenu(){
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Menu:");
            System.out.println("   1. Nhấn 1 để xem thông tin người mượn");
            System.out.println("   2. Nhấn 2 để xem kho sách");
            System.out.println("   3. Nhấn 3 để tìm sách");
            System.out.println("   4. Nhấn 4 để thêm sách");
            System.out.println("   5. Nhấn 5 để sửa thông tin sách");
            System.out.println("   6. Nhấn 6 để cho mượn sách");
            System.out.println("   7. Nhấn 7 để trở về Menu chính");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    readBorrowInformation();
                    break;
                case "2":
                    readBooks();
                    break;
                case "3":
                    searchBooks();
                    break;
                case "4":
                    addBook();
                    break;
                case "5":

                    break;
                case "6":

                    break;
                case  "7":
                    menu.mainMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while ((choice != "2"));
    }
    public void readBorrowInformation() {
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("src\\data\\ThongTinMuonSach.csv"));
            int count = 1;
            while ((line = br.readLine()) != null) {
                List<String> bookLine = readWriteCSVFile.parseCsvLine(line);
                Book book = new Book(bookLine.get(3),bookLine.get(4),Long.valueOf(bookLine.get(5)));
                CustomerInformation customerInfo = new CustomerInformation(bookLine.get(0),bookLine.get(1),bookLine.get(2));
                readWriteCSVFile.PrintCustomerAndBook(book,customerInfo,count);
                count ++;
            }
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

    public void readBooks() {
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("src\\data\\Books.csv"));
            int count = 1;
            while ((line = br.readLine()) != null) {
                readWriteCSVFile.PrintBook(readWriteCSVFile.parseCsvLine(line),count);
                count++;
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

    public void addBook() {
        System.out.println("=================================================");
        Scanner input = new Scanner(System.in);
        System.out.println("Mời bạn nhập tên sách : ");
        String inputBookName = input.nextLine();
        System.out.println("Mời bạn nhập tên tác giả : ");
        String inputAuthor = input.nextLine();
        System.out.println("Mời bạn nhập giá tiền : ");
        String inputPrice = input.nextLine();
        String fileName = "src/data/Books.csv";
        Book book = new Book(inputBookName,inputAuthor,Long.valueOf(inputPrice));
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            FileWriter writer = new FileWriter(fileName,true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            if(br.readLine()==null){
                bufferedWriter.write(book.getName());
                bufferedWriter.write(";");
                bufferedWriter.write(book.getAuthor());
                bufferedWriter.write(";");
                String price = String.valueOf(book.getPrice());
                bufferedWriter.write(price);
                bufferedWriter.write(";");
                System.out.println("=================================================");
                System.out.println("Thêm sách thành công");
                System.out.println("=================================================");
            }
            else{
                bufferedWriter.write("\n");
                bufferedWriter.write(book.getName());
                bufferedWriter.write(";");
                bufferedWriter.write(book.getAuthor());
                bufferedWriter.write(";");
                String price = String.valueOf(book.getPrice());
                bufferedWriter.write(price);
                bufferedWriter.write(";");
                System.out.println("=================================================");
                System.out.println("Thêm sách thành công");
                System.out.println("=================================================");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            }
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
}

