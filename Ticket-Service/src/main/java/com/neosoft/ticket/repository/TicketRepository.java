package com.neosoft.ticket.repository;

import com.neosoft.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    Ticket findByTicketNo(int ticketNo);

}
