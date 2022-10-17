package formacionBackend.SpringCloud.trip.infrastructure.dtos;

import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.trip.domain.Trip;
import lombok.Data;

@Data
public class TripClientOutputDTO {

    Integer idClient;
    String nameClient;
    String emailClient;

    Integer idTrip;
    String originTrip;
    String destinationTrip;
    boolean statusTrip;

    public TripClientOutputDTO(Trip trip, Client client) {
        this.idClient = client.getIdClient();
        this.nameClient = client.getName();
        this.emailClient = client.getEmail();

        this.idTrip = trip.getIdTrip();
        this.originTrip = trip.getOrigin();
        this.destinationTrip = trip.getDestination();
        this.statusTrip = trip.isStatus();
    }

}
