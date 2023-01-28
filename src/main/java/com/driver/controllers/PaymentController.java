package com.driver.controllers;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.ReservationRepository;
import com.driver.services.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
    PaymentServiceImpl paymentService;
    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping("/pay")
    public Payment pay(@RequestParam Integer reservationId, @RequestParam Integer amountSent, @RequestParam String mode) throws Exception{
        //Attempt a payment of amountSent for reservationId using the given mode ("cASh", "card", or "upi")
        //If the amountSent is less than bill, throw "Insufficient Amount" exception, otherwise update payment attributes
        //If the mode contains a string other than "cash", "card", or "upi" (any character in uppercase or lowercase), throw "Payment mode not detected" exception.

        //Note that the reservationId always exists
        Payment payment=null;
        try {
            paymentService.pay(reservationId,amountSent,mode);
        }catch (Exception e){
            return null;
        }
        return payment;
        }

}
