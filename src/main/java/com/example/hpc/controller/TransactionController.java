package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.TransactionDomain;
import com.example.hpc.model.entity.User;
import com.example.hpc.service.TransactionService;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.enums.UserRoles;
import com.example.hpc.utils.filtering.criteria.TransactionCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private TransactionService service;
    private JwtUserDetailsService jwtUserDetailsService;

    public TransactionController(TransactionService service, JwtUserDetailsService jwtUserDetailsService) {
        this.service = service;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @GetMapping
    public ResponseEntity<PagedResult<TransactionDomain>> getAll(TransactionCriteria criteria, Pageable pageable) {
        return ResponseEntity.ok().body(service.getAll(criteria, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDomain> getById(@PathVariable("id") Long id) {
        User currentUser = jwtUserDetailsService.getCurrentUser();
        TransactionDomain result = service.getOne(id);
        if (Arrays.asList(UserRoles.STUDENT, UserRoles.MASTER).contains(currentUser.getRole().getRoleName()))
            if (!result.getPerson().getUser().getId().equals(currentUser.getId()))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok().body(result);
    }
}
