package com.mibookstore.mibookstoreapi.service;

import com.mibookstore.mibookstoreapi.model.Book;
import com.mibookstore.mibookstoreapi.model.dto.BookRequest;
import org.springframework.data.domain.Page;

public interface BookService {

    Book createBook(BookRequest bookRequest);
    Page<Book> getAll(int page, int size);
    Book getById(Integer id);
    Book updateBook(Integer id, BookRequest bookRequest);
    void deleteBook(Integer id);

}
