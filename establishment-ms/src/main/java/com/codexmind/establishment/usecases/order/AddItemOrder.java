package com.codexmind.establishment.usecases.order;

import com.codexmind.establishment.domain.ItemOrder;
import com.codexmind.establishment.domain.Product;
import com.codexmind.establishment.domain.enums.PaymentStatus;
import com.codexmind.establishment.dto.ItemOrderRequestDTO;
import com.codexmind.establishment.repository.ItemOrderRepository;
import org.springframework.stereotype.Service;

import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.OrderRepository;
import com.codexmind.establishment.repository.ProductRepository;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddItemOrder {


    private final OrderRepository orderRepository;

    private final ItemOrderRepository itemOrderRepository;

    private final ProductRepository productRepository;

    public Order execute(Integer orderId, List<ItemOrderRequestDTO> itemOrderDTOs) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        List<ItemOrder> itemOrders = itemOrderRepository.findByOrder(order);
        for (ItemOrderRequestDTO itemOrderDTO : itemOrderDTOs) {
            Product product = productRepository.findById(itemOrderDTO.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found"));

            Optional<ItemOrder> existingItemOrder = itemOrders.stream()
                    .filter(io -> io.getProduct().getId().equals(itemOrderDTO.productId()))
                    .findFirst();

            if (existingItemOrder.isPresent()) {
                ItemOrder itemOrder = existingItemOrder.get();
                int newQuantity = itemOrder.getQuantity() + itemOrderDTO.quantity();
                BigDecimal newTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(newQuantity));
                itemOrder.setQuantity(newQuantity);
                itemOrder.setTotalAmount(newTotalPrice);
                itemOrder.setStatus(PaymentStatus.PENDING);
                itemOrderRepository.save(itemOrder);
            } else {
                BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(itemOrderDTO.quantity()));
                ItemOrder itemOrder = new ItemOrder();
                itemOrder.setProduct(product);
                itemOrder.setQuantity(itemOrderDTO.quantity());
                itemOrder.setTotalAmount(totalPrice);
                itemOrder.setUnitPrice(product.getPrice());
                itemOrder.setOrder(order);
                itemOrderRepository.save(itemOrder);
            }
        }

        BigDecimal orderTotalPrice = itemOrderRepository.findByOrder(order).stream()
                .map(ItemOrder::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalOrder(orderTotalPrice);

        return orderRepository.save(order);
    }

}
