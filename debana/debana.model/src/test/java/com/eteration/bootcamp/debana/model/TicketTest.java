package com.eteration.bootcamp.debana.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicketTest {

	@Test
	public void testGetUser() {
		
         User user = new User();
         user.setName("naci");
         
         Ticket ticket1 = new Ticket();
         ticket1.setTitle("Her yerde cop var");
         ticket1.setDescription("labb adpa das'dfj wuF WIUF FPQ");
         ticket1.setUser(user);
         
         Ticket ticket2 = new Ticket();
         ticket2.setUser(user);
         
         assertEquals(user, ticket1.getUser());
         
	}

}
