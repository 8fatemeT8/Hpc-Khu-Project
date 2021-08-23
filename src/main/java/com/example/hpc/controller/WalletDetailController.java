package com.example.hpc.controller;

import com.example.hpc.config.jwt.JwtUserDetailsService;
import com.example.hpc.model.domain.WalletDetailDomain;
import com.example.hpc.model.entity.Person;
import com.example.hpc.model.entity.User;
import com.example.hpc.service.PersonService;
import com.example.hpc.service.WalletDetailService;
import com.example.hpc.utils.PagedResult;
import com.example.hpc.utils.filtering.criteria.WalletDetailCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/wallet-detail")
public class WalletDetailController{

	private WalletDetailService service;

	public WalletDetailController(WalletDetailService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<PagedResult<WalletDetailDomain>> getAll(WalletDetailCriteria criteria, Pageable pageable) {
		return ResponseEntity.ok().body(service.getAll(criteria, pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<WalletDetailDomain> getById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(service.getOne(id));
	}
}
