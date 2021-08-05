package com.example.hpc.service;

import com.example.hpc.model.domain.WalletDomain;
import com.example.hpc.model.dto.WalletDto;
import com.example.hpc.model.entity.Wallet;
import com.example.hpc.model.repository.WalletRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.criteria.WalletCriteria;
import com.example.hpc.utils.mapper.WalletMapper;
import com.example.hpc.utils.predicates.WalletPredicate;
import org.springframework.stereotype.Service;

@Service
public class WalletService extends ServiceWithSearchBase<Wallet,
		WalletDto, WalletDomain, WalletRepository, WalletMapper, WalletCriteria, WalletPredicate> {

	public WalletService(WalletRepository walletRepository, WalletMapper walletMapper, WalletPredicate walletPredicate) {
		super(walletRepository, walletMapper, walletPredicate);
	}
}
