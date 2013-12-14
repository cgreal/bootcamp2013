package com.eteration.debana.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.eteration.bootcamp.debana.model.Ticket;
import com.eteration.bootcamp.debana.model.User;
import com.eteration.debana.service.TicketRepository;

@Stateless
public class TicketRepositoryImpl implements TicketRepository{
	
	@PersistenceContext(name="debana")
	EntityManager em;

	public TicketRepositoryImpl() {
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> findTicketsSortedByDate(int numTickets) {
		
		Query query = em.createQuery("select t from Ticket t");
		 List<Ticket> res = query.getResultList();
		return res;
	}


	@Override
	public void setupDB() {
		Ticket t = new Ticket();
		t.setTitle("title");
		t.setDescription("description");
		User u = new User();
		u.setName("username");
		em.persist(u);
		t.setUser(u);
		em.persist(t);
		
	}

}
