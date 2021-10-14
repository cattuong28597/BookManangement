package repository;

import model.Book;
import utils.CSVFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {

    public final String filePath = "src/data/Books.csv";

    @Override
    public List<Book> getBooks() throws IOException {
        List<Book> bookList = new ArrayList<>();
        List<String> records = CSVFile.readFile(filePath);
        for (String record: records) {
            bookList.add(new Book(record));
        }
        return bookList;
    }


    @Override
    public void insert(Book book) throws IOException {
        List<Book> bookList = getBooks();
        bookList.add(book);
        saveBooks(bookList);
    }

    @Override
    public void saveBooks(List<Book> books) throws IOException {
        List<String> records = new ArrayList<>();
        for (Book b: books) {
            records.add(b.toString());
        }
        CSVFile.writeFile(filePath, records);
    }
}
