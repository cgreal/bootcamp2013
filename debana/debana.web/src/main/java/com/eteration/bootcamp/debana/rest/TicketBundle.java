package com.eteration.bootcamp.debana.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.eteration.bootcamp.debana.model.Ticket;

@XmlRootElement
public class TicketBundle {

	private List<Ticket> ticket;

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}
}
