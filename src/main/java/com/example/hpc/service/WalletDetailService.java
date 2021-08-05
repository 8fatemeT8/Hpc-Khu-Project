package com.example.hpc.service;

import com.example.hpc.model.domain.WalletDetailDomain;
import com.example.hpc.model.dto.WalletDetailDto;
import com.example.hpc.model.entity.WalletDetail;
import com.example.hpc.model.repository.WalletDetailRepository;
import com.example.hpc.service.bases.ServiceWithSearchBase;
import com.example.hpc.utils.criteria.WalletDetailCriteria;
import com.example.hpc.utils.mapper.WalletDetailMapper;
import com.example.hpc.utils.predicates.WalletDetailPredicate;
import org.springframework.stereotype.Service;

@Service
public class WalletDetailService extends ServiceWithSearchBase<WalletDetail, WalletDetailDto,
		WalletDetailDomain, WalletDetailRepository, WalletDetailMapper, WalletDetailCriteria, WalletDetailPredicate> {

	public WalletDetailService(WalletDetailRepository walletDetailRepository,
							   WalletDetailMapper walletDetailMapper, WalletDetailPredicate walletDetailPredicate) {
		super(walletDetailRepository, walletDetailMapper, walletDetailPredicate);
	}
}
