package com.codexmind.establishment.usecases.order.pdv;

import com.codexmind.establishment.domain.*;
import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.repository.ItemOrderRepository;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CloseOrder {

    private final OrderRepository orderRepository;
    private final ItemOrderRepository itemOrderRepository;
    private final ProductRepository productRepository;

    public Order execute(Integer orderId, String paymentType, BigDecimal valueReceived) {
        var orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }

        var order = orderOptional.get();
        var items = itemOrderRepository.getAllItemOrdersByOrderId(orderId, StatusComanda.OPENED, PaymentStatus.PENDING);

        processItems(items);
        processPayment(order, paymentType, valueReceived);

        order.setStatus(StatusComanda.CLOSED);
        order.setPaymentStatus(PaymentStatus.PAID);

        return orderRepository.save(order);
    }

    private void processItems(Set<ItemOrder> items) {
        for (var itemOrder : items) {
            var productOptional = productRepository.findById(itemOrder.getProduct().getId());
            if (productOptional.isEmpty()) {
                throw new IllegalArgumentException("Product not found");
            }

            var product = productOptional.get();
            if (product.getEstoque() != null) {
                product.setEstoque(product.getEstoque() - itemOrder.getQuantity());
            }

            itemOrder.setPaymentStatus(PaymentStatus.PAID);
            productRepository.save(product);
        }
    }

    private void processPayment(Order order, String paymentType, BigDecimal valueReceived) {
        switch (paymentType) {
            case "cartao" -> {
                var payment = new PaymentWithCreditCard();
                payment.setOrder(order);
                payment.setInstallments(1);
                payment.setPaymentStatus(PaymentStatus.PAID);
                order.setPayment(payment);
            }
            case "dinheiro" -> {
                var payment = new PaymentWithCash();
                payment.setOrder(order);
                payment.setReceivedValue(valueReceived);
                payment.setPaymentStatus(PaymentStatus.PAID);
                payment.setInstateConfirmation(LocalDate.now());
                order.setPayment(payment);
            }
            case "pix" -> {
                var payment = new PaymentWithPix();
                payment.setOrder(order);
                payment.setPaymentStatus(PaymentStatus.PAID);
                payment.setInstateConfirmation(LocalDate.now());
                order.setPayment(payment);
            }
            default -> throw new IllegalArgumentException("Invalid payment type");
        }
    }
}
