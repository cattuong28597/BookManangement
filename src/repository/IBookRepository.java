package repository;


import model.Book;

import java.io.IOException;
import java.util.List;

public interface IBookRepository {
    List<Book> getBooks() throws IOException;

    void insert(Book book) throws IOException;

    void saveBooks(List<Book> books) throws IOException;
}
