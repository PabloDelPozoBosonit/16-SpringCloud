package formacionBackend.SpringCloud.trip.application;

import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripClientOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripStatusDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TriplnputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripOutputDTO;

public interface TripService {


    TripOutputDTO createTrip(TriplnputDTO triplnputDTO) throws  Exception;
    TripOutputDTO updateTrip(TriplnputDTO triplnputDTO, Integer id)  throws  Exception;
    TripOutputDTO getTrip(Integer id)  throws  Exception;
    void deleteTrip(Integer id);

    TripClientOutputDTO addPassenger(Integer tripId, Integer idPassenger);
    String countPassengers(Integer tripId);
    TripOutputDTO changeTripStatus(Integer tripId, boolean tripStatus);
    String tripStatus(Integer tripId);

}
