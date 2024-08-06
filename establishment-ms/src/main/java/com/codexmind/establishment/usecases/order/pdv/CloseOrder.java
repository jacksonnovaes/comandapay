package com.codexmind.establishment.usecases.order.pdv;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.PaymentWithCash;
import com.codexmind.establishment.domain.PaymentWithCreditCard;
import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.repository.ItemOrderRepository;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class CloseOrder {

    private final OrderRepository orderRepository;

    private final ItemOrderRepository itemOrderRepository;

    private final ProductRepository productRepository;



    public Order execute(Integer orderId, String paymentType, BigDecimal valueReceived) {
        var orderFinded = orderRepository.findById(orderId);

        var itens = itemOrderRepository.getAllItemOrdersByOrderId(orderId, StatusComanda.OPENED);
        itens.stream().forEach(itemOrder -> {
            var product = productRepository.findById(itemOrder.getProduct().getId());
                product.get().setEstoque(product.get().getEstoque() - itemOrder.getQuantity());
                productRepository.save(product.get());
        });
        if (paymentType.equals("cartao")) {
            PaymentWithCreditCard paymentWithCreditCard = new PaymentWithCreditCard();
            paymentWithCreditCard.setOrder(orderFinded.get());
            paymentWithCreditCard.setInstallments(1);
            paymentWithCreditCard.setPaymentStatus(PaymentStatus.PAID);
            orderFinded.get().setPayment(paymentWithCreditCard);
        }else if (paymentType.equals("dinheiro")){
            PaymentWithCash paymentWithCash = new PaymentWithCash();
            paymentWithCash.setOrder(orderFinded.get());
            paymentWithCash.setReceivedValue(valueReceived);
            paymentWithCash.setPaymentStatus(PaymentStatus.PAID);
            paymentWithCash.setInstateConfirmation(LocalDate.now());
            orderFinded.get().setPayment(paymentWithCash);
        }
        orderFinded.get().setStatus(StatusComanda.CLOSED);
        orderRepository.save(orderFinded.get());
        return orderFinded.get();
    }
}
