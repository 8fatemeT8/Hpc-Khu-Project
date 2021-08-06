package com.example.hpc.model.dto;


public class WalletDto extends DtoBase{

    private Long credit;

    public WalletDto() {
    }

    public WalletDto(Long credit) {
        this.credit = credit;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

}
