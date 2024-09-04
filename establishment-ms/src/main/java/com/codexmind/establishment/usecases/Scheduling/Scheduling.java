package com.codexmind.establishment.usecases.Scheduling;

import com.codexmind.establishment.domain.SchedulingDomain;
import com.codexmind.establishment.domain.Services;
import com.codexmind.establishment.dto.SchedulingDTO;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.repository.CustomerRepository;
import com.codexmind.establishment.repository.SchedulingRepository;
import com.codexmind.establishment.repository.ServiceRepository;
import com.codexmind.establishment.usecases.customer.DetailCustomer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class Scheduling {

    private final SchedulingRepository schedulingRepository;

    private final CustomerRepository customerRepository;

    private final ServiceRepository serviceRepository;


    public Scheduling(SchedulingRepository schedulingRepository, DetailCustomer detailCustomer, CustomerRepository customerRepository, ServiceRepository serviceRepository) {
        this.schedulingRepository = schedulingRepository;
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
    }

    public SchedulingDomain execute(SchedulingDTO schedulingDTO){

        var customer = customerRepository.findById(schedulingDTO.customerId()).orElseThrow(()-> new EntityNotFoundException("User not Found!"));


        List<Services> servicesList = schedulingDTO.servicesId().stream()
                .map(serviceRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        var scheduling = SchedulingDomain.builder()
                .customer(customer)
                .employee(null)
                .description(schedulingDTO.description())
                .targetDate(schedulingDTO.schedulingDate())
                .services(servicesList)
                .build();
        return schedulingRepository.save(scheduling);

    }
}
