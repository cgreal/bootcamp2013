package com.eteration.bootcamp.debana.rest;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.eteration.debana.service.TicketManager;

@Path("/tickets")
public class TicketResource {

	@Inject
	TicketManager ticketManager;

	public TicketResource() {
	}

	@Path("/latest")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public TicketBundle getLatestTickets() {
		TicketBundle bundle = new TicketBundle();
		bundle.setTicket(ticketManager.latestTickets(10));
		return bundle;

	}

	@Path("/init")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String init() {
		ticketManager.setupDB();
		return "OK";

	}

	@POST
	@Path("/add")
	public Response addUser(@FormParam("title") String title, @FormParam("description") String description) {

		return Response.status(200)
				.entity("Add is called, title : " + title + ", description : " + description)
				.build();

	}

}
