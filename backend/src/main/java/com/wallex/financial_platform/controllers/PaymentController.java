package com.wallex.financial_platform.controllers;

import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import com.wallex.financial_platform.dtos.requests.payment.PaymentRequestDTO;
import com.wallex.financial_platform.services.utils.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @PostMapping("/card")
    public ResponseEntity<Payment> createCardPayment(@RequestBody @Valid PaymentRequestDTO paymentReq){
        return ResponseEntity.ok(paymentService.createPaymentCard(paymentReq));
    }

    @PostMapping("/preference")
    public ResponseEntity<Preference> createPreference(@RequestBody Map<String, BigDecimal> amount){
        return ResponseEntity.ok(paymentService.createPaymentPreference(amount.get("amount")));
    }
}
