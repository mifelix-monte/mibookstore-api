package com.mibookstore.mibookstoreapi.service.ipml;

import com.mibookstore.mibookstoreapi.model.*;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import com.mibookstore.mibookstoreapi.repository.CartBookRepository;
import com.mibookstore.mibookstoreapi.repository.CartRepository;
import com.mibookstore.mibookstoreapi.service.BookService;
import com.mibookstore.mibookstoreapi.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class CartServiceIpmlTest {

    @InjectMocks
    CartServiceIpml service;

    @Mock
    CartRepository repository;

    @Mock
    ClientService clientService;

    @Mock
    BookService bookService;

    @Mock
    CartBookRepository cartBookRepository;

    Cart cart;

    Client client;

    Book book;

    CartBook cartBook;

    CartBookId cartBookId;

    CartRequest cartRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCart();
    }

    @Test
    void addItem() {
        when(clientService.getById(any())).thenReturn(client);
        when(bookService.getById(any())).thenReturn(book);
        when(repository.save(any())).thenReturn(cart);

        Cart response = service.addItem(client.getId(), book.getId(), cartRequest);

        assertNotNull(response);
        assertEquals(Cart.class, response.getClass());
    }

    @Test
    void updateCart() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(cart));
        when(bookService.getById(any())).thenReturn(book);
        when(cartBookRepository.findByCartIdAndBookId(any(), any())).thenReturn(cartBook);
        when(repository.save(any())).thenReturn(cart);

        Cart response = service.updateCart(client.getId(), book.getId(), cartRequest);

        assertNotNull(response);
        assertEquals(Cart.class, response.getClass());
    }

    @Test
    void updateCartNull() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(cart));
        when(bookService.getById(any())).thenReturn(book);
        when(cartBookRepository.findByCartIdAndBookId(any(), any())).thenReturn(null);
        when(repository.save(any())).thenReturn(cart);

        Cart response = service.updateCart(client.getId(), book.getId(), cartRequest);

        assertNotNull(response);
    }

    @Test
    void getAll() {
        Page<Cart> carts = Mockito.mock(Page.class);
        PageRequest pageRequest = PageRequest.of(1, 1);

        when(repository.findAll(pageRequest)).thenReturn(carts);

        Page<Cart> response = service.getAll(1,1);

        assertNotNull(response);
    }

    @Test
    void getById() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(cart));

        Cart response = service.getById(cart.getId());

        assertNotNull(response);
        assertEquals(cart.getId(), response.getId());
    }

    @Test
    void removeItem() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(cart));
        when(bookService.getById(any())).thenReturn(book);
        when(cartBookRepository.findByCartIdAndBookId(any(), any())).thenReturn(cartBook);
        when(repository.save(any())).thenReturn(cart);

        Cart response = service.removeItem(cart.getId(), book.getId());

        assertEquals(cart.getCartBooks(), response.getCartBooks());
    }

    @Test
    void removeItemWhenCartBookQuantityIsEqualsOne() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(cart));
        when(bookService.getById(any())).thenReturn(book);
        when(cartBookRepository.findByCartIdAndBookId(any(), any())).thenReturn(cartBook);
        when(repository.save(any())).thenReturn(cart);
        cartBook.setItemQuantity(1);

        Cart response = service.removeItem(cart.getId(), book.getId());

        assertEquals(cart.getCartBooks(), response.getCartBooks());
    }

    @Test
    void clearCart() {
        when(repository.findById(any())).thenReturn(Optional.ofNullable(cart));
        when(repository.save(any())).thenReturn(cart);

        Cart response = service.clearCart(cart.getId());

        assertEquals(cart.getCartItemQuantity(), response.getCartItemQuantity());
    }

    @Test
    void deleteCart() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(cart));
        doNothing().when(repository).delete(cart);
        service.deleteCart(client.getId());
        verify(repository, times(1)).delete(cart);
    }

    private void startCart() {
        client = new Client();
        client.setId(52);
        client.setName("Fabiana da Silva");
        client.setCpf("47884425977");
        client.setEmail("fabiana@hotmail.com");
        client.setAddress("Rua Pico do Baú, 78, Altos da Serra - São José dos Campos/SP");
        client.setCell("11981475521");

        book = new Book();
        book.setId(1);
        book.setTitle("O Cemitério");
        book.setAuthor("Stephen King");
        book.setPages(405);
        book.setPrice(new BigDecimal("35.99"));
        book.setStockQuantity(4);

        cart = new Cart();
        cart.setId(25);
        cart.setAmount(new BigDecimal("205.99"));
        cart.setCartItemQuantity(1);
        cart.setCreationDate(LocalDateTime.now());
        cart.setClient(client);

        cartBookId = new CartBookId(cart.getId(), book.getId());

        cartBook = new CartBook(cart, book);
        cartBook.setId(cartBookId);
        cartBook.setItemQuantity(2);

        cartRequest = new CartRequest(1);
    }

}