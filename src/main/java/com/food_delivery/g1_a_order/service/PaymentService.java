package com.food_delivery.g1_a_order.service;

import com.food_delivery.g1_a_order.api.dto.payment.PaymentShowDto;
import com.food_delivery.g1_a_order.config.mapper.PaymentMapper;
import com.food_delivery.g1_a_order.persistent.entity.Payment;
import com.food_delivery.g1_a_order.persistent.repository.PaymentRepository;
import com.food_delivery.g1_a_order.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentService  extends BaseService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMapper paymentMapper;

    public List<PaymentShowDto> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        return paymentMapper.toPaymentShowDto(payments);
    }

    public List<PaymentShowDto> getPaymentsByCustomerId(Long customerId) {
        List<Payment> addresses = paymentRepository.findByCustomerId(customerId)
                .orElseThrow(() -> handleServerError("No payments found for this customer"));
        return paymentMapper.toPaymentShowDto(addresses);
    }
}
