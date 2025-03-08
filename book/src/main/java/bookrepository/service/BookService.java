package bookrepository.service;

import bookrepository.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book addBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);
}
