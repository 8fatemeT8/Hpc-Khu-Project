package com.example.hpc.model.domain;

public class WalletDomain extends DomainBase{
    private Long credit;

    private PersonDomain person;

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public PersonDomain getPerson() {
        return person;
    }

    public void setPerson(PersonDomain person) {
        this.person = person;
    }
}
