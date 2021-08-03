package com.example.hpc.model.entity;

import com.example.hpc.utils.enums.TransactionStatus;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction  extends EntityBase{

    @Column(name = "status")
    private TransactionStatus status;

    @Column(name = "tracking_code")
    private String trackingCode;

    private long amount;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
