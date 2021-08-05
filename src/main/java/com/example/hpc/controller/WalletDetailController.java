package com.example.hpc.controller;

import com.example.hpc.controller.bases.ControllerWithSearchBase;
import com.example.hpc.model.domain.WalletDetailDomain;
import com.example.hpc.model.dto.WalletDetailDto;
import com.example.hpc.model.entity.WalletDetail;
import com.example.hpc.model.repository.WalletDetailRepository;
import com.example.hpc.service.WalletDetailService;
import com.example.hpc.utils.criteria.WalletDetailCriteria;
import com.example.hpc.utils.mapper.WalletDetailMapper;
import com.example.hpc.utils.predicates.WalletDetailPredicate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet-detail")
public class WalletDetailController extends ControllerWithSearchBase<WalletDetail, WalletDetailDto, WalletDetailDomain,
		WalletDetailRepository, WalletDetailMapper, WalletDetailService, WalletDetailCriteria, WalletDetailPredicate> {

	public WalletDetailController(WalletDetailService walletDetailService) {
		super(walletDetailService);
	}
}
