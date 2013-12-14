package com.eteration.debana.service;

import java.util.List;

import com.eteration.bootcamp.debana.model.Ticket;

public interface TicketRepository {

	List<Ticket> findTicketsSortedByDate(int numTickets);

	void setupDB();

}
