package formacionBackend.SpringCloud.ticket.domain;
;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripOutputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class Ticket {


    @Id
    @GeneratedValue
    private Integer id;
    private Integer passengerId;
    private String passengerName;
    private String passengerLastName;
    private String passengerEmail;
    private String tripOrigin;
    private String tripDestination;
    private Date departureDate;
    private Date arrivalDate;


    //todo posiblemenmte cambiar a entidad Trip y Client mejor que OutputDTO
    public void generateTicket(TripOutputDTO tripOutputDTO, ClientOutputDTO clientOutputDTO) {

        this.passengerId = clientOutputDTO.getId();
        this.passengerName = clientOutputDTO.getName();
        this.passengerLastName = clientOutputDTO.getSurname();
        this.passengerEmail = clientOutputDTO.getEmail();
        this.tripOrigin = tripOutputDTO.getOrigin();
        this.tripDestination = tripOutputDTO.getDestination();
        this.departureDate = tripOutputDTO.getDepartureDate();
        this.arrivalDate = tripOutputDTO.getArrivalDate();
    }

}
