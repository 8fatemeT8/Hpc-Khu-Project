package com.example.hpc.model.dto;

import java.util.List;

public class AdminDto extends DtoBase{

    List<TicketDto> tickets;

    public List<TicketDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDto> tickets) {
        this.tickets = tickets;
    }
}
