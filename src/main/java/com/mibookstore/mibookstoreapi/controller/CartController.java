package com.mibookstore.mibookstoreapi.controller;

import com.mibookstore.mibookstoreapi.model.Cart;
import com.mibookstore.mibookstoreapi.model.dto.CartRequest;
import com.mibookstore.mibookstoreapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping
    public Cart addItem(@RequestParam Integer clientId, Integer bookId, CartRequest cartRequest){
        return cartService.addItem(clientId, bookId, cartRequest);
    }

    @GetMapping
    public List<Cart> getAll(){
        return cartService.getAll();
    }

    @PutMapping("/update")
    public Cart updateCart(@RequestParam Integer cartId, Integer bookId, CartRequest cartRequest) {
        return cartService.updateCart(cartId, bookId, cartRequest);
    }


    @PutMapping("/remove")
    public Cart removeItem(@RequestParam Integer cartId, Integer bookId){
       return cartService.removeItem(cartId, bookId);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Integer id){
        cartService.deleteCart(id);
    }

}
