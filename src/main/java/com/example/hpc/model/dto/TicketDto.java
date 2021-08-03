package com.example.hpc.model.dto;


public class TicketDto extends DtoBase{

    private String  description;

    private String title;

    private AdminDto admin;

    private PersonDto person;

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

    public AdminDto getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDto admin) {
        this.admin = admin;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }
}
