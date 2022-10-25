package formacionBackend.SpringCloud.ticket.infrastructure.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TicketInputDTO {

    private Integer id;
    private Integer passengerId;
    private String passengerName;
    private String passengerLastName;
    private String passengerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;
}
