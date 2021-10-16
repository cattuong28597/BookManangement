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

public class Admin {
    InformationCustomerRepository informationCustomerRepository = new InformationCustomerRepository();
    BookRepository bookRepository = new BookRepository();
    private ReadWriteCSVFile readWriteCSVFile = new ReadWriteCSVFile();
    public void checkAdmin() throws IOException {
        System.out.println("=================================================");
        Scanner input = new Scanner(System.in);
        System.out.println("Mời bạn nhập mã để truy cập quyền admin : ");
        String inputPass = input.nextLine();
        while(!inputPass.equals("admin")){
            System.out.println("Thông tin mã nhập sai, vui lòng nhập lại : ");
            inputPass = input.nextLine();
        }
        adminMenu();
    }
    public void adminMenu() throws IOException {
        Menu menu = new Menu();
        menu.adminMenu();
    }
    public void showSaleInfo() throws IOException {
//        ArrayList<SaleInformation> saleInfo = new ArrayList<SaleInformation>(informationCustomerRepository.getInformation());
//        for (int i = 0; i < saleInfo.size(); i++) {
//            System.out.println(i+1+". "+saleInfo.get(i));
//        }


        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("src\\data\\SaleInformation.csv"));
            int count = 1;
            while ((line = br.readLine()) != null) {
                List<String> bookLine = readWriteCSVFile.parseCsvLine(line);
                Book book = new Book(bookLine.get(3),bookLine.get(4),Long.valueOf(bookLine.get(5)));
                CustomerInformation customerInfo = new CustomerInformation(bookLine.get(0),bookLine.get(1),bookLine.get(2),bookLine.get(6));
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

    public void readBooks() throws IOException {
        ArrayList<Book> books = new ArrayList<>(bookRepository.getBooks());
        for (int i = 0; i < books.size(); i++) {
            System.out.println(i+1+"."+books.get(i));
        }
    }


    public void addBook() throws IOException {
        String bookName;
        String bookAuthor = null;
        System.out.println("=================================================");
        Scanner input = new Scanner(System.in);
        System.out.println("Mời bạn nhập tên sách : ");
        bookName = input.nextLine();
        while(bookName == "") {
             System.out.println("Mời bạn nhập tên sách : ");
             bookName = input.nextLine();
        }
       do {
           System.out.println("Mời bạn nhập tên tác giả : ");
           bookAuthor = input.nextLine();
       } while (bookAuthor == "");
        System.out.println("Mời bạn nhập giá tiền : ");
        String bookPrice = input.nextLine();
        while(bookPrice.matches("[0-9]+") == false || bookPrice.length() < 4 || Long.valueOf(bookPrice) % 1000 != 0){
            System.out.println("Xin ban vui long nhap lai so tien hop le");
            System.out.print("Vui long nhap so tien ");
            bookPrice = input.nextLine();
        }
        Book book = new Book(bookName, bookAuthor,Long.parseLong(bookPrice));
        confirmBookAdd(book);
//        bookRepository.insert(book);
        System.out.println("=================================================");
        System.out.println("Thêm sách thành công");
        System.out.println("=================================================");
    }

    public void confirmBookAdd(Book book) throws IOException {
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Xác nhận:");
            System.out.println("   1. Nhấn 1 để xác nhận thêm");
            System.out.println("   2. Nhấn 2 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    bookRepository.insert(book);
                    menu.adminMenu();
                    break;
                case "2":
                    menu.adminMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiếp tục");
                    System.out.println("=================================================");
            }
        } while (choice != "2");
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


    // Sửa sách
    public void editBookInfo() throws IOException {
        ArrayList<Book> listBook = new ArrayList<Book>(bookRepository.getBooks());
        for (int i = 0; i < listBook.size(); i++) {
            System.out.println(i+1+"."+listBook.get(i));
        }
            System.out.println("=================================================");
            if(listBook.size()!=0){
                System.out.print("Nhập số thứ tự sách cần chỉnh sửa :");
                Scanner input = new Scanner(System.in);
                String inputIndexBook = input.nextLine();
                while(inputIndexBook.matches("[0-9]+") == false || Integer.parseInt(inputIndexBook) > listBook.size() ){
                    System.out.println("Xin bạn vui lòng nhập lại số thứ tự hợp lệ");
                    System.out.print("Nhập số thứ tự sách cần chỉnh sửa ");
                    inputIndexBook = input.nextLine();
                }
                int index = Integer.parseInt(inputIndexBook);
                System.out.println("=================================================");
                System.out.println("Tên sách cần sửa : "+listBook.get(index-1).getName()+", tác giả : "+listBook.get(index-1).getAuthor()+", giá tiền : "+listBook.get(index-1).getPrice());
                Scanner input2 = new Scanner(System.in);
                System.out.print("Nhập tên sách chỉnh sửa : ");
                String inputBookName = input2.nextLine();
                System.out.print("Nhập tác giả : ");
                String inputAuthor = input2.nextLine();
                System.out.print("Nhập giá tiền : ");
                String inputPrice = input2.nextLine();
                while(inputPrice.matches("[0-9]+") == false || inputPrice.length() < 4 || Long.valueOf(inputPrice) % 1000 != 0){
                    System.out.println("Xin ban vui long nhap lai so tien hop le");
                    System.out.print("Vui long nhap so tien ");
                    inputPrice = input.nextLine();
                }
                listBook.get(index-1).setName(inputBookName);
                listBook.get(index-1).setAuthor(inputAuthor);
                listBook.get(index-1).setPrice(Long.valueOf(inputPrice));
                System.out.println("=================================================");
                System.out.println("Danh sách sau khi chỉnh sửa");
                for(int i=0;i<listBook.size();i++){
                    System.out.println((i+1)+". "+listBook.get(i));
                }
                System.out.println("=================================================");
                confirmBookChange(listBook);
            }

    }

    public void confirmBookChange(ArrayList<Book> books) throws IOException {
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Xác nhận:");
            System.out.println("   1. Nhấn 1 để xác nhận lưu thay đổi");
            System.out.println("   2. Nhấn 2 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    saveChangeValueBookToCSV(books);
                    break;
                case "2":
                    menu.customerMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để xác nhận");
                    System.out.println("=================================================");
            }
        } while (choice != "2");
    }

    public void saveChangeValueBookToCSV(ArrayList<Book> books) throws IOException {
        Menu menu = new Menu();
        bookRepository.saveBooks(books);
        System.out.println("=================================================");
        System.out.println("Sửa sách thành công");
        System.out.println("=================================================");
        menu.adminMenu();
    }

    //Xóa sách

    public void DeleteBookInfo() throws IOException {
        ArrayList<Book> listBook = new ArrayList<Book>(bookRepository.getBooks());
        for (int i = 0; i < listBook.size(); i++) {
            System.out.println(i+1+"."+listBook.get(i));
        }
            System.out.println("=================================================");
            if(listBook.size()!=0){
                System.out.print("Nhập số thứ tự sách cần xóa :");
                Scanner input = new Scanner(System.in);
                String inputIndexBook = input.nextLine();
                while(inputIndexBook.matches("[0-9]+") == false || Integer.parseInt(inputIndexBook) > listBook.size() ){
                    System.out.println("Xin bạn vui lòng nhập lại số thứ tự hợp lệ");
                    System.out.print("Nhập số thứ tự sách cần chỉnh sửa ");
                    inputIndexBook = input.nextLine();
                }
                int index1 = Integer.parseInt(inputIndexBook);
                System.out.println("=================================================");
                listBook.remove(index1-1);
                confirmBookDelete(listBook);
            }

    }

    public void confirmBookDelete(ArrayList<Book> books) throws IOException {
        Menu menu = new Menu();
        String choice = "a";
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Xác nhận:");
            System.out.println("   1. Nhấn 1 để xác nhận xóa");
            System.out.println("   2. Nhấn 2 để thoát");
            System.out.println("=================================================");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = input.nextLine();
            switch (choice) {
                case "1":
                    saveChangeAfterDelete(books);
                    break;
                case "2":
                    menu.adminMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiếp tục");
                    System.out.println("=================================================");
            }
        } while (choice != "2");
    }


    public void saveChangeAfterDelete(ArrayList<Book> books) throws IOException {
        Menu menu = new Menu();
        bookRepository.saveBooks(books);
        System.out.println("=================================================");
        System.out.println("Đã xóa sách thành công");
        System.out.println("=================================================");
        menu.adminMenu();
    }
}

