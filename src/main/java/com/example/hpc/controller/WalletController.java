package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.WalletDomain;
import com.example.hpc.model.dto.WalletDto;
import com.example.hpc.model.entity.Wallet;
import com.example.hpc.model.repository.WalletRepository;
import com.example.hpc.service.WalletService;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.filtering.criteria.WalletCriteria;
import com.example.hpc.utils.mapper.WalletMapper;
import com.example.hpc.utils.filtering.predicates.WalletPredicate;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
@RequestMapping("/api/wallet")
public class WalletController extends ControllerWithSearchBase<Wallet, WalletDto, WalletDomain,
        WalletRepository, WalletMapper, WalletService, WalletCriteria, WalletPredicate> {

    private JwtUserDetailsService jwtUserDetailsService;

    public WalletController(WalletService walletService, JwtUserDetailsService jwtUserDetailsService) {
        super(walletService);
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    // disable add and delete api for wallet controller
    @Override
    public ResponseEntity<WalletDomain> create(@Valid WalletDto walletDto) {
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PagedResult<WalletDomain>> getAll(WalletCriteria walletCriteria, Pageable pageable) {
        if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(jwtUserDetailsService.getCurrentUser().getRole().getRoleName()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return super.getAll(walletCriteria, pageable);
    }
}
