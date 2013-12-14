package com.eteration.debana.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.eteration.bootcamp.debana.model.Ticket;
import com.eteration.debana.service.TicketManager;
import com.eteration.debana.service.TicketRepository;

@Stateless
public class TicketManagerImpl implements TicketManager {

	@Inject
	TicketRepository ticketRepository;
	
	public TicketManagerImpl() {
	}

	@Override
	public List<Ticket> latestTickets(int numTickets) {
		List<Ticket>  result = ticketRepository.findTicketsSortedByDate(numTickets);
		return result;
	}
	
	@Override
	public void setupDB() {
		ticketRepository.setupDB();
	
	}
}
