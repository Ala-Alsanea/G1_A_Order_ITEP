package com.food_delivery.g1_a_order.api.controller;

import com.food_delivery.g1_a_order.api.dto.order.OrderShowDto;

import com.food_delivery.g1_a_order.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderShowDto>> getOrders() {

        return ResponseEntity.ok(orderService.getOrders());

    }




    @PutMapping("{orderId}/deliveryAccept")
    public ResponseEntity<OrderShowDto> deliveryAcceptOrder(@PathVariable("orderId") Long orderId,
            @RequestParam("deliveryId") Long deliveryId) {

        return ResponseEntity.ok(orderService.deliveryAcceptOrder(orderId, deliveryId));
    }

    @PutMapping("{orderId}/orderDelivered")
    public ResponseEntity<OrderShowDto> orderDelivered(@PathVariable("orderId") Long orderId,
            @RequestParam("deliveryId") Long deliveryId) {

        return ResponseEntity.ok(orderService.orderDelivered(orderId, deliveryId));
    }


    @GetMapping("readyToPickup/delivery/{deliveryId}")
    public ResponseEntity<List<OrderShowDto>> getReadyToPickupOrdersByDelivery(
            @PathVariable("deliveryId") Long deliveryId) {
        return ResponseEntity
                .ok(orderService.getOrdersByStatusAndDelivery(deliveryId, OrderStatusEnum.READY_TO_PICKUP.status));
    }

}
