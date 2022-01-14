package com.example.demo.controller;

import com.example.demo.model.OrderProducts;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity<List<OrderProducts>> getOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get all orders of customer
    @GetMapping("get/customer/id/{customerId}")
    public ResponseEntity<?> getCustomerOrders(@PathVariable int customerId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getCustomerOrders(customerId));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get all orders that contain a specified product
    @GetMapping("get/product/id/{productId}")
    public ResponseEntity<?> getOrdersWithProduct(@PathVariable int productId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersWithProduct(productId));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get all orders from specified shipper
    @GetMapping("get/shipper/id/{shipperId}")
    public ResponseEntity<?> getShipperOrders(@PathVariable int shipperId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.getShipperOrders(shipperId));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<OrderProducts> addOrder(@RequestBody @Valid OrderProducts order) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        order.setOrderDate(date.toString());

        OrderProducts createdOrder = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editOrder(@RequestBody @Valid OrderProducts order) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.editOrder(order));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrder(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
