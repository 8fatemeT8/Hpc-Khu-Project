package com.example.hpc.model.dto;

public class WalletDetailDto extends DtoBase{
    private String description;
    private long amount;

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
}
