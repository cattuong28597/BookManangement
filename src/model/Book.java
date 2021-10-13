package model;

public class Book {
    private String name;
    private String author;
    private long price;

    public Book(String name, String author, long price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(String raw) {
        String[] fields = raw.split(";");
        this.name = fields[0];
        this.author = fields[1];
        this.price = Long.parseLong(fields[2]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("%s;%s;%d", name, author, price);
    }
}
