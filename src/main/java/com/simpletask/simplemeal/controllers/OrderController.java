package com.simpletask.simplemeal.controllers;

import com.simpletask.simplemeal.dto.OrderDTO;
import com.simpletask.simplemeal.exception.NotFoundException;
import com.simpletask.simplemeal.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/order")
@Validated
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping()
    public ResponseEntity<?> addOrder(Principal principal,@Valid @RequestBody OrderDTO dto, BindingResult bindingResult) throws NotFoundException {
        orderService.addOrder(dto, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
