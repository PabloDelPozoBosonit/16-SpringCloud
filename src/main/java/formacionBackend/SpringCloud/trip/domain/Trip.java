package formacionBackend.SpringCloud.trip.domain;

import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TriplnputDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Trip {

    @Id
    @GeneratedValue
    private Integer idTrip;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "clientId")
    private Set<Client> passengers = new HashSet<>();

    private boolean status;

    public void createTrip(TriplnputDTO triplnputDTO) {

        this.origin = triplnputDTO.getOrigin();
        this.destination = triplnputDTO.getDestination();
        this.departureDate = triplnputDTO.getDepartureDate();
        this.arrivalDate = triplnputDTO.getArrivalDate();
        this.status = triplnputDTO.isStatus();
    }
}
