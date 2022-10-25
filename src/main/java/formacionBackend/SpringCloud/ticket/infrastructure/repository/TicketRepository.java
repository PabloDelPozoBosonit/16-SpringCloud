package formacionBackend.SpringCloud.ticket.infrastructure.repository;

import formacionBackend.SpringCloud.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
