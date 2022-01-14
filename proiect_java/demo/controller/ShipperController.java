package com.example.demo.controller;

import com.example.demo.model.Shipper;
import com.example.demo.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shippers")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping("/get")
    public ResponseEntity<List<Shipper>> getShippers() {
        return ResponseEntity.status(HttpStatus.OK).body(shipperService.getShippers());
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<?> getShipper(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shipperService.getShipperById(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get/name/{companyName}")
    public ResponseEntity<?> getShipper(@PathVariable String companyName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shipperService.getShipperByName(companyName));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Shipper> addShipper(@RequestBody @Valid Shipper shipper) {
        Shipper createdShipper = shipperService.addShipper(shipper);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShipper);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editShipper(@RequestBody @Valid Shipper newShipper) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shipperService.editShipper(newShipper));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShipper(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(shipperService.deleteShipper(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
