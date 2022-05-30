package com.ticket.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ticket.model.Ticket;
import com.ticket.repository.TicketRepository;

public class TicketServiceImp implements TicketService{
	
    @Autowired
    private TicketRepository ticketRepository;
	@Override
	public List<Ticket> getAllTicket() {
		
		return (List<Ticket>) ticketRepository.findAll();
	}

}
