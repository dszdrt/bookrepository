package bookrepository.service;

import bookrepository.exception.ApiException;
import bookrepository.model.Book;
import bookrepository.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ApiException("Не удалось найти книгу с идентификатором " + id, 404));
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setYearOfPublication(updatedBook.getYearOfPublication());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Книга не найдена"));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}