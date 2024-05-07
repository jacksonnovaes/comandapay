package com.codexmind.establishment.usecases.establishment;

import com.codexmind.establishment.domain.User;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class GetEstablishmentByEmployeLogin {


    private final EstablishmentRepository establishmentRepository;

    public GetEstablishmentByEmployeLogin(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    public Integer execute(){

        User user = UserService.authenticated();

        assert user != null;
        return establishmentRepository.getEstablishmentByUserLoggedId(user.getId());
    }
}
