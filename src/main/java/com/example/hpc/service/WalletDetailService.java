package com.example.hpc.service;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.WalletDetailDomain;
import com.example.hpc.model.dto.WalletDetailDto;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.User;
import com.example.hpc.model.entity.WalletDetail;
import com.example.hpc.model.repository.WalletDetailRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.exceptions.ExceptionHandler;
import com.example.hpc.utils.filtering.criteria.WalletDetailCriteria;
import com.example.hpc.utils.hooks.BeforeAdd;
import com.example.hpc.utils.hooks.BeforeUpdate;
import com.example.hpc.utils.mapper.WalletDetailMapper;
import com.example.hpc.utils.filtering.predicates.WalletDetailPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;

@Service
public class WalletDetailService extends ServiceWithSearchBase<WalletDetail, WalletDetailDto,
        WalletDetailDomain, WalletDetailRepository, WalletDetailMapper, WalletDetailCriteria, WalletDetailPredicate> implements BeforeAdd<WalletDetail, WalletDetailDto>, BeforeUpdate<WalletDetail, WalletDetailDto> {

    private WalletDetailMapper walletDetailMapper;
    private PersonService personService;
    private JwtUserDetailsService jwtUserDetailsService;

    public WalletDetailService(WalletDetailRepository walletDetailRepository,
                               WalletDetailMapper walletDetailMapper, WalletDetailPredicate walletDetailPredicate,
                               PersonService personService, JwtUserDetailsService jwtUserDetailsService) {
        super(walletDetailRepository, walletDetailMapper, walletDetailPredicate);
        this.walletDetailMapper = walletDetailMapper;
        this.personService = personService;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    public void add(WalletDetailDto walletDetailDto, WalletDetail walletDetail) {
        walletDetail.setCreateDate(Instant.now());
    }

    @Override
    public void update(WalletDetailDto walletDetailDto, WalletDetail walletDetail) {
        walletDetail.setUpdateDate(Instant.now());
    }

    @Override
    public WalletDetailDomain getOne(Long id) throws ExceptionHandler {
        WalletDetailDomain result = super.getOne(id);
        User user = jwtUserDetailsService.getCurrentUser();
        Person person = personService.getPersonByUserUsername(user.getUsername());

        if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(user.getRole().getRoleName())) {
            if (!person.getWallet().getId().equals(walletDetailMapper.toEntity(result).getWallet().getId()))
                throw new ExceptionHandler("you cant get this object", HttpStatus.FORBIDDEN.value());
        }
        return result;
    }
}
