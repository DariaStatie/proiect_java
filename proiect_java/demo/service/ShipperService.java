package com.example.demo.service;

import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.model.Shipper;
import com.example.demo.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipperService {

    @Autowired
    ShipperRepository shipperRepository;

    public List<Shipper> getShippers() { return shipperRepository.getShippers(); }

    public Optional<Shipper> getShipperById(int id) {
        Optional<Shipper> existingShipper = shipperRepository.getShipperById(id);

        if (existingShipper.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista transportatorul cu id-ul sepcificat!");
        }

        return existingShipper;
    }

    public Optional<Shipper> getShipperByName(String companyName) {
        Optional<Shipper> existingShipper = shipperRepository.getShipperByName(companyName);

        if (existingShipper.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista transportatorul cu id-ul sepcificat!");
        }

        return existingShipper;
    }

    public Shipper addShipper(Shipper shipper) { return shipperRepository.addShipper(shipper); }

    public Shipper editShipper(Shipper newShipper) {
        Optional<Shipper> existingShipper = shipperRepository.getShipperById(newShipper.getId());

        if (existingShipper.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista transportatorul cu id-ul sepcificat!");
        }

        return shipperRepository.editShipper(newShipper);
    }

    public Optional<Shipper> deleteShipper(int id){
        Optional<Shipper> existingShipper = shipperRepository.getShipperById(id);

        if (existingShipper.isEmpty()) {
            throw new ObjectNotFoundException("Nu exista transportatorul cu id-ul sepcificat!");
        }

        return shipperRepository.deleteShipper(id);
    }
}
