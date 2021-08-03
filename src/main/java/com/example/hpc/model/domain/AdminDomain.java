package com.example.hpc.model.domain;

import java.util.List;

public class AdminDomain extends DomainBase {

    UserDomain user;

    List<TicketDomain> tickets;

    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }

    public List<TicketDomain> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDomain> tickets) {
        this.tickets = tickets;
    }
}
