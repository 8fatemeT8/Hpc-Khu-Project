package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.WalletDomain;
import com.example.hpc.model.dto.WalletDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.entity.Wallet;
import com.example.hpc.model.repository.WalletRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.filtering.criteria.WalletCriteria;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.WalletMapper;
import com.example.hpc.utils.filtering.predicates.WalletPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class WalletService extends ServiceWithSearchBase<Wallet,
        WalletDto, WalletDomain, WalletRepository, WalletMapper, WalletCriteria, WalletPredicate> implements BeforeUpdate<Wallet, WalletDto> {

    private JwtUserDetailsService jwtUserDetailsService;
    private PersonService personService;

    public WalletService(WalletRepository walletRepository, WalletMapper walletMapper,
                         WalletPredicate walletPredicate, JwtUserDetailsService jwtUserDetailsService, PersonService personService) {
        super(walletRepository, walletMapper, walletPredicate);
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.personService = personService;
    }

    @Override
    public void update(WalletDto walletDto, Wallet wallet) {
        User user = jwtUserDetailsService.getCurrentUser();
        if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(user.getRole().getRoleName()))
            throw new ExceptionHandler("you cant change this object", HttpStatus.FORBIDDEN.value());
    }

    @Override
    public WalletDomain getOne(Long id) throws ExceptionHandler {
        WalletDomain result = super.getOne(id);
        User user = jwtUserDetailsService.getCurrentUser();
        Person person = personService.getPersonByUserUsername(user.getUsername());

        if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(user.getRole().getRoleName())) {
            if (!person.getWallet().getId().equals(result.getId()))
                throw new ExceptionHandler("you cant get this object", HttpStatus.FORBIDDEN.value());

        }
        return result;
    }
}
