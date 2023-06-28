package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import com.mibookstore.mibookstoreapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public ResponseEntity<Cart> createCart(@Valid @RequestParam Integer clientId, Integer bookId, CartRequest cartRequest) {
        return ResponseEntity.status(CREATED).body(cartService.addItem(clientId, bookId, cartRequest));
    }

    @GetMapping
    public ResponseEntity<Page<Cart>> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                                               @RequestParam  (required = false, defaultValue = "3") int size) {

        return ResponseEntity.ok(cartService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(cartService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Cart> updateCart(@RequestParam Integer cartId, Integer bookId, CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.updateCart(cartId, bookId, cartRequest));
    }

    @PutMapping("/remove")
    public ResponseEntity<Cart> removeItem(@RequestParam Integer cartId, Integer bookId){
       return ResponseEntity.ok(cartService.removeItem(cartId, bookId));
    }

    @PutMapping("/clear/{id}")
    public ResponseEntity<Cart> clearCart(@PathVariable Integer id) {
        return ResponseEntity.ok(cartService.clearCart(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCart(@PathVariable Integer id){
        cartService.deleteCart(id);

        return ResponseEntity.noContent().build();
    }

}
