package model;

public class SaleInformation {
    private String nameCustomer;
    private String address;
    private String phoneNumber;
    private String nameBook;
    private String author;
    private long price;
    private String date;

    public SaleInformation() {};

    public SaleInformation(String nameCustomer, String address,
                           String phoneNumber, String nameBook,
                           String author, long price, String date) {
        this.nameCustomer = nameCustomer;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nameBook = nameBook;
        this.author = author;
        this.price = price;
        this.date = date;
    }

    public SaleInformation(String raw) {
        String[] fields = raw.split(";");
        this.nameCustomer = nameCustomer;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.nameBook = nameBook;
        this.author = author;
        this.price = price;
        this.date = date;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%d;%s",
                nameCustomer,address,phoneNumber,nameBook,author,price,date);
    }
}
