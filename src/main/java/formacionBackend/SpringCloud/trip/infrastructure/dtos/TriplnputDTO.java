package formacionBackend.SpringCloud.trip.infrastructure.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class TriplnputDTO {

    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private boolean status;
}
