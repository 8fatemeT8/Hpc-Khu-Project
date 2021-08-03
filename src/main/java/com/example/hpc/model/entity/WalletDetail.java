package com.example.hpc.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "wallet_details")
public class WalletDetail extends EntityBase{

    private String description;

    private long amount;

    @OneToOne
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
