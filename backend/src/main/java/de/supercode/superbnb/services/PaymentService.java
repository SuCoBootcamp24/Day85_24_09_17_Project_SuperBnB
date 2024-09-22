package de.supercode.superbnb.services;

import de.supercode.superbnb.dtos.user.UserUpdateRequestDTO;
import de.supercode.superbnb.entities.Payment;
import de.supercode.superbnb.repositorys.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }



    public Optional<Payment> getPaymentById(long id) {
        return paymentRepository.findById(id);
    }

    public Payment updatePayment(Optional<Payment> existPayment, UserUpdateRequestDTO dto) {
        Payment updatedPayment = existPayment.orElseGet(() -> new Payment());
        updatedPayment.setCardNumber(dto.cardNumber());
        updatedPayment.setCvv(dto.cvv());
        updatedPayment.setExpirationDate(dto.expirationDate());
            return paymentRepository.save(updatedPayment);
    }
}
