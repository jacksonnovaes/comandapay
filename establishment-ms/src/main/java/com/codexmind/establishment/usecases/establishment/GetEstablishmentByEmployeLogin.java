package com.codexmind.establishment.usecases.establishment;

import com.codexmind.establishment.domain.Establishment;
import com.codexmind.establishment.domain.User;
import com.codexmind.establishment.repository.EstablishmentRepository;
import com.codexmind.establishment.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetEstablishmentByEmployeLogin {


    private final EstablishmentRepository establishmentRepository;

    public GetEstablishmentByEmployeLogin(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    public Optional<Establishment> execute() {

        User user = UserService.authenticated();

        assert user != null;
        return Optional.of(establishmentRepository.getEstablishmentByUserLoggedId(user.getId()).get());
    }
}
