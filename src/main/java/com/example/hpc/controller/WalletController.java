package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.WalletDomain;
import com.example.hpc.model.dto.WalletDto;
import com.example.hpc.model.entity.Wallet;
import com.example.hpc.model.repository.WalletRepository;
import com.example.hpc.service.WalletService;
import com.example.hpc.utils.criteria.WalletCriteria;
import com.example.hpc.utils.mapper.WalletMapper;
import com.example.hpc.utils.predicates.WalletPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletController extends ControllerWithSearchBase<Wallet, WalletDto, WalletDomain,
		WalletRepository, WalletMapper, WalletService, WalletCriteria, WalletPredicate> {

	public WalletController(WalletService walletService) {
		super(walletService);
	}
}
