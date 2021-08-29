package com.example.hpc.model.entity;

import com.example.hpc.utils.enums.TicketState;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket extends EntityBase {

    private String description;

    private String title;

    private String attachment;

    private TicketState state;

    @Column(name = "admin_username")
    private String adminUsername;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public TicketState getState() {
        return state;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public void setState(TicketState state) {
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
