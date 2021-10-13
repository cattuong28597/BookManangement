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
            System.out.println("   6. Nhấn 6 để xóa sách");
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
                    fixBookInfo();
                    break;
                case "6":
                    DeleteBookInfo();
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
            br = new BufferedReader(new FileReader("src\\data\\SaleInformation.csv"));
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
    // Sửa sách
    public void fixBookInfo(){
        BufferedReader br = null;
        try {
            String line;
            ArrayList<Book> listBook = new ArrayList<Book>();
            br = new BufferedReader(new FileReader("src\\data\\Books.csv"));
            int count = 1;
            while ((line = br.readLine()) != null) {
                readWriteCSVFile.PrintBook(readWriteCSVFile.parseCsvLine(line),count);
                List<String> bookLine = readWriteCSVFile.parseCsvLine(line);
                listBook.add(new Book(bookLine.get(0),bookLine.get(1),Long.valueOf(bookLine.get(2))));
                count++;
            }
            System.out.println("=================================================");
            if(listBook.size()!=0){
                System.out.print("Nhập số thứ tự sách cần chỉnh sửa :");
                Scanner input = new Scanner(System.in);
                int inputIndexBook = input.nextInt();
                System.out.println("=================================================");
                System.out.println("Tên sách cần sửa : "+listBook.get(inputIndexBook-1).getName()+", tác giả : "+listBook.get(inputIndexBook-1).getAuthor()+", giá tiền : "+listBook.get(inputIndexBook-1).getPrice());
                Scanner input2 = new Scanner(System.in);
                System.out.print("Nhập tên sách chỉnh sửa : ");
                String inputBookName = input2.nextLine();
                System.out.print("Nhập tác giả : ");
                String inputAuthor = input2.nextLine();
                System.out.print("Nhập giá tiền : ");
                String inputPrice = input2.nextLine();
                listBook.get(inputIndexBook-1).setName(inputBookName);
                listBook.get(inputIndexBook-1).setAuthor(inputAuthor);
                listBook.get(inputIndexBook-1).setPrice(Long.valueOf(inputPrice));
                System.out.println("=================================================");
                System.out.println("Danh sach sau khi chinh sua");
                for(int i=0;i<listBook.size();i++){
                    System.out.println((i+1)+". "+listBook.get(i));
                }
                System.out.println("=================================================");
                confirmBookChange(listBook);
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

    public void confirmBookChange(ArrayList<Book> books) {
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
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while (choice != "2");
    }

    public void saveChangeValueBookToCSV(ArrayList<Book> books) {
        Menu menu = new Menu();
        String fileName = "src/data/Books.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(books.get(0).getName());
            bufferedWriter.write(";");
            bufferedWriter.write(books.get(0).getAuthor());
            bufferedWriter.write(";");
            String price = String.valueOf(books.get(0).getPrice());
            bufferedWriter.write(price);
            bufferedWriter.write(";");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=1;i<books.size();i++){
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                FileWriter writer = new FileWriter(fileName,true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("\n");
                bufferedWriter.write(books.get(i).getName());
                bufferedWriter.write(";");
                bufferedWriter.write(books.get(i).getAuthor());
                bufferedWriter.write(";");
                String price = String.valueOf(books.get(i).getPrice());
                bufferedWriter.write(price);
                bufferedWriter.write(";");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=================================================");
        System.out.println("Sửa sách thành công");
        System.out.println("=================================================");
        menu.adminMenu();
    }

    //Xóa sách

    public void DeleteBookInfo(){
        BufferedReader br = null;
        try {
            String line;
            ArrayList<Book> listBook = new ArrayList<Book>();
            br = new BufferedReader(new FileReader("src\\data\\Books.csv"));
            int count = 1;
            while ((line = br.readLine()) != null) {
                readWriteCSVFile.PrintBook(readWriteCSVFile.parseCsvLine(line),count);
                List<String> bookLine = readWriteCSVFile.parseCsvLine(line);
                listBook.add(new Book(bookLine.get(0),bookLine.get(1),Long.valueOf(bookLine.get(2))));
                count++;
            }
            System.out.println("=================================================");
            if(listBook.size()!=0){
                System.out.print("Nhập số thứ tự sách cần xóa :");
                Scanner input = new Scanner(System.in);
                int inputIndexBook = input.nextInt();
                System.out.println("=================================================");
                listBook.remove(inputIndexBook-1);
                confirmBookDelete(listBook);
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

    public void confirmBookDelete(ArrayList<Book> books) {
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
                    menu.customerMenu();
                    break;
                default:
                    System.out.println("Bạn nhập sai chức năng");
                    System.out.println("Bấm nút theo menu để tiêp tục");
                    System.out.println("=================================================");
            }
        } while (choice != "2");
    }


    public void saveChangeAfterDelete(ArrayList<Book> books) {
        Menu menu = new Menu();
        String fileName = "src/data/Books.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(books.get(0).getName());
            bufferedWriter.write(";");
            bufferedWriter.write(books.get(0).getAuthor());
            bufferedWriter.write(";");
            String price = String.valueOf(books.get(0).getPrice());
            bufferedWriter.write(price);
            bufferedWriter.write(";");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=1;i<books.size();i++){
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                FileWriter writer = new FileWriter(fileName,true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                bufferedWriter.write("\n");
                bufferedWriter.write(books.get(i).getName());
                bufferedWriter.write(";");
                bufferedWriter.write(books.get(i).getAuthor());
                bufferedWriter.write(";");
                String price = String.valueOf(books.get(i).getPrice());
                bufferedWriter.write(price);
                bufferedWriter.write(";");
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("=================================================");
        System.out.println("Đã xóa sách thành công");
        System.out.println("=================================================");
        menu.adminMenu();
    }
}

