package formacionBackend.SpringCloud.trip.infrastructure.dtos;

import formacionBackend.SpringCloud.trip.domain.Trip;
import lombok.Data;

import java.util.Date;

@Data
public class TripStatusDTO {

    boolean status;
    Integer id;
    String origin;
    String destination;
    Date departureDate;
    Date arrivalDate;


    public TripStatusDTO(Trip trip) {

        this.status = trip.isStatus();
        this.id = trip.getIdTrip();
        this.origin = trip.getOrigin();
        this.destination = trip.getDestination();;
        this.departureDate = trip.getDepartureDate();
        this.arrivalDate = trip.getArrivalDate();
    }

    @Override
    public String toString() {
        String statusP;
        if(this.status)
            statusP = " DISPONIBLE";
        else
            statusP = " CANCELADO!!";

        return "El estado del viaje con id " + id + " con destino " + origin + " - " + destination + " con fecha " + departureDate + " - " + arrivalDate + " es " + statusP;
    }
}
