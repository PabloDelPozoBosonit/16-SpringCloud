package formacionBackend.SpringCloud.trip.application;

import formacionBackend.SpringCloud.ticket.infrastructure.dtos.TicketOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripClientOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TriplnputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripOutputDTO;

import java.util.Optional;


public interface TripService {


    TripOutputDTO createTrip(TriplnputDTO triplnputDTO) throws  Exception;
    TripOutputDTO updateTrip(TriplnputDTO triplnputDTO, Integer id)  throws  Exception;
    TripOutputDTO getTrip(Integer id)  throws  Exception;
    String deleteTrip(Integer id)throws  Exception;

    TicketOutputDTO addPassenger(Integer tripId, Integer idPassenger)throws Exception;
    String countPassengers(Integer tripId)throws  Exception;
    TripOutputDTO changeTripStatus(Integer tripId, boolean tripStatus) throws Exception;
    String tripStatus(Integer tripId)throws  Exception;

}
