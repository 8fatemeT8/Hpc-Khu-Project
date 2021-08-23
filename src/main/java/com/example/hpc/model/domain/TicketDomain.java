package com.example.hpc.model.domain;


public class TicketDomain extends DomainBase{

    private String  description;

    private String title;

    private AdminDomain admin;

    private PersonDomain person;

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PersonDomain getPerson() {
        return person;
    }

    public void setPerson(PersonDomain person) {
        this.person = person;
    }

    public AdminDomain getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDomain admin) {
        this.admin = admin;
    }
}
