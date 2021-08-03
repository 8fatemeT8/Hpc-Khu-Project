package com.example.hpc.model.dto;


public class WalletDto extends DtoBase{

    private Long credit;

    private PersonDto person;

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }
}
