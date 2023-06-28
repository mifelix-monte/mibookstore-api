package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import com.mibookstore.mibookstoreapi.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/cart")
@Api(value = "Mi Bookstore API")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    @ApiOperation(value = "Create an instance of cart")
    public ResponseEntity<Cart> createCart(@Valid @RequestParam Integer clientId, Integer bookId, CartRequest cartRequest) {
        return ResponseEntity.status(CREATED).body(cartService.addItem(clientId, bookId, cartRequest));
    }

    @GetMapping
    @ApiOperation(value = "Return a list of all registered carts")
    public ResponseEntity<Page<Cart>> getAll(@RequestParam (required = false, defaultValue = "0") int page,
                                               @RequestParam  (required = false, defaultValue = "3") int size) {

        return ResponseEntity.ok(cartService.getAll(page, size));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Return a cart according to the id")
    public ResponseEntity<Cart> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(cartService.getById(id));
    }

    @PutMapping("/update")
    @ApiOperation(value = "Add more books to cart")
    public ResponseEntity<Cart> updateCart(@RequestParam Integer cartId, Integer bookId, CartRequest cartRequest) {
        return ResponseEntity.ok(cartService.updateCart(cartId, bookId, cartRequest));
    }

    @PutMapping("/remove")
    @ApiOperation(value = "Delete book according to the id")
    public ResponseEntity<Cart> removeItem(@RequestParam Integer cartId, Integer bookId){
       return ResponseEntity.ok(cartService.removeItem(cartId, bookId));
    }

    @PutMapping("/clear/{id}")
    @ApiOperation(value = "Return an empty cart")
    public ResponseEntity<Cart> clearCart(@PathVariable Integer id) {
        return ResponseEntity.ok(cartService.clearCart(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete cart")
    public ResponseEntity deleteCart(@PathVariable Integer id){
        cartService.deleteCart(id);

        return ResponseEntity.noContent().build();
    }

}
