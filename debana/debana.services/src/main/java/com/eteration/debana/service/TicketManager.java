package com.eteration.debana.service;

import java.util.List;

import com.eteration.bootcamp.debana.model.Ticket;

public interface TicketManager {

	List<Ticket> latestTickets(int numTickets);

	void setupDB();

}
