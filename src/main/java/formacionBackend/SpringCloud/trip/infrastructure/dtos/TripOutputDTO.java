package formacionBackend.SpringCloud.trip.infrastructure.dtos;

import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.trip.domain.Trip;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TripOutputDTO {

    private Integer id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private Set<Client> passengers;
    private boolean status;

    public TripOutputDTO(Trip trip) {
        this.id = trip.getIdTrip();
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();
        this.passengers = trip.getPassengers();
        this.status = trip.isStatus();
    }
}
