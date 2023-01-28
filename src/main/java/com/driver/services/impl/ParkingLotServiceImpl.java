package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot= new ParkingLot(name,address);
     parkingLotRepository1.save(parkingLot);
     return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
Spot spot=new Spot(parkingLot,numberOfWheels,pricePerHour);
spotRepository1.save(spot);
return spot;
    }

    @Override
    public void deleteSpot(int spotId) {
Spot spot=spotRepository1.findById(spotId).get();
spotRepository1.delete(spot);

    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
List<Spot> spotList=parkingLot.getSpotList();
Spot spot1=new Spot();
for(Spot spot:spotList){
    spot1=spotRepository1.findById(spotId).get();
    spot1.setPricePerHour(pricePerHour);
}
parkingLotRepository1.save(parkingLot);
//spotRepository1.save(spot1);
return spot1;
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
parkingLotRepository1.deleteById(parkingLotId);
    }
}
