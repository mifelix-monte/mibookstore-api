package com.mibookstore.mibookstoreapi.service.ipml;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import com.mibookstore.mibookstoreapi.repository.BookRepository;
import com.mibookstore.mibookstoreapi.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceIpml implements BookService {

    private final BookRepository bookRepository;

    public BookServiceIpml(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookRequest bookRequest){
        Book book = new Book(bookRequest);
        return bookRepository.save(book);
    }

    @Override
    public Page<Book> getAll(int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC,"title");
        return bookRepository.findAll(pageRequest);
    }

    @Override
    public Book getById(Integer id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Book updateBook(Integer id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow();

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPrice(bookRequest.getPrice());
        book.setPages(bookRequest.getPages());
        book.setStockQuantity(bookRequest.getStockQuantity());

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
    }
}
