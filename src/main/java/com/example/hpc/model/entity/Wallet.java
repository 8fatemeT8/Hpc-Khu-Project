package com.example.hpc.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallets")
public class Wallet extends EntityBase{

    private Long credit;

    public Wallet(Long credit) {
        this.credit = credit;
    }

    public Wallet() {
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }
}
