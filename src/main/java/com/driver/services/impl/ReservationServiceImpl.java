package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
//Reserve a spot in the given parkingLot such that the total price is minimum. Note that the price per hour for each spot is different
        try {
            ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();
            User user = userRepository3.findById(userId).get();
            if (parkingLot == null || user == null) {
                throw new Exception("Cannot make reservation");
            }
            List<Spot> spotList = parkingLot.getSpotList();

            Integer minimum = Integer.MAX_VALUE;
            Spot spot1 = null;
            for (Spot spot : spotList) {
                if (returnSpotType(spot.getSpotType()) >= numberOfWheels && spot.getOccupied() == false && spot.getPricePerHour() * timeInHours < minimum) {
                    minimum = spot.getPricePerHour() * timeInHours;
                    spot1 = spot;
                }
            }

        if(spot1==null){
            throw new Exception("Cannot make reservation");
        }
        Reservation reservation=new Reservation(timeInHours);
        reservation.setUser(user);
        List<Reservation> reservationList=user.getReservationList();
        if(reservationList==null){
        reservationList=new ArrayList<>();
        }
        reservationList.add(reservation);
        user.setReservationList(reservationList);
        reservation.setSpot(spot1);
        List<Reservation> reservationList1=spot1.getReservationList();
        if(reservationList1==null){
            reservationList=new ArrayList<>();
        }
        reservationList1.add(reservation);
        spot1.setReservationList(reservationList1);
        spot1.setOccupied(true);
        userRepository3.save(user);
        spotRepository3.save(spot1);
        return  reservation;
        }
        catch(Exception e){
return null;

        }}
        public int returnSpotType(SpotType spotType){
            if (spotType == SpotType.TWO_WHEELER){
                return 2;
            } else if (spotType== SpotType.FOUR_WHEELER) {
                return 4;
            }
            else return Integer.MAX_VALUE;
        }
}
