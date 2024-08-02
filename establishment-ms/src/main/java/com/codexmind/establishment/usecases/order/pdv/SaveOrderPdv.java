package com.codexmind.establishment.usecases.order.pdv;

import com.codexmind.establishment.domain.Customer;
import com.codexmind.establishment.domain.Employee;
import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.Order;
import com.codexmind.establishment.domain.enums.StatusComanda;
import com.codexmind.establishment.dto.ItemOrderDTO;
import com.codexmind.establishment.dto.ItemOrderRequestDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.*;
import com.codexmind.establishment.service.UserService;
import com.codexmind.establishment.usecases.order.mobile.AddItemOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SaveOrderPdv {

    private final OrderRepository orderRepository;
    private final ItemOrderRepository itemOrderRepository;
    private final ProductRepository productRepository;
    private final EmployeeRepository employeeRepository;
    private final EstablishmentRepository establishmentRepository;

    private final AddItemOrder addItemOrder;

    public Order execute(List<ItemOrderRequestDTO> itemOrderDTOS) {

        var user = UserService.authenticated();

        var employee  = employeeRepository.findById(user.getId())
                .orElseThrow(()->new EntityNotFoundException("Usuario pode nao ser do tipo correto:" + Employee.class.getTypeName()));
        var statusComanda = StatusComanda.fromValue("OPENED");
        var orderFinded = orderRepository.getOpenedCommandByEmployeeAndStatus(employee.getId(), statusComanda);
        if(orderFinded.isPresent()){
            return addItemOrder.execute(orderFinded.get().getId(), itemOrderDTOS);
        }
        var order = Order.builder().build();
        order.setEmployee(employee);
        order.setEstablishment(employee.getEstablishment());
        order.setOpenInstant(LocalDateTime.now());
        order.setStatus(StatusComanda.OPENED);
        var orderSaved = orderRepository.save(order);
        return addItemOrder.execute(orderSaved.getId(), itemOrderDTOS);

    }
}
