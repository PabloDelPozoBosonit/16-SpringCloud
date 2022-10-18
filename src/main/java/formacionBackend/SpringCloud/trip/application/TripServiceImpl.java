package formacionBackend.SpringCloud.trip.application;

import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.client.infrastructure.repository.ClientRepository;
import formacionBackend.SpringCloud.exceptions.EntityNotFoundException;
import formacionBackend.SpringCloud.exceptions.UnprocessableEntityException;
import formacionBackend.SpringCloud.trip.domain.Trip;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripClientOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripStatusDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TriplnputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TripServiceImpl  implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public TripOutputDTO createTrip(TriplnputDTO triplnputDTO) throws Exception {

        if(triplnputDTO == null || triplnputDTO.getDestination().equals("")) {
            throw  new UnprocessableEntityException("Trip field can not be null", 422);
        }
        Trip trip = new Trip();
        trip.createTrip(triplnputDTO);
        tripRepository.save(trip);

        return new TripOutputDTO(trip);
    }

    @Override
    public TripOutputDTO updateTrip(TriplnputDTO triplnputDTO, Integer id) throws Exception {

        Optional<Trip> tripOpt = tripRepository.findById(id);

        if(tripOpt.isEmpty())
            throw new EntityNotFoundException("The trip does no exist", 404);

        if(triplnputDTO == null || triplnputDTO.getDestination().equals("")) {
            throw  new UnprocessableEntityException("Trip field can not be null", 422);
        }

        Trip trip = tripOpt.get();
        trip.createTrip(triplnputDTO);
        tripRepository.save(trip);

        return new TripOutputDTO(trip);

    }

    @Override
    public TripOutputDTO getTrip(Integer id) throws Exception {

        Trip trip = tripRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Trip not found",404));
        return new TripOutputDTO(trip);
    }

    @Override
    public void deleteTrip(Integer id) {

        Optional<Trip> tripOpt = tripRepository.findById(id);

        if(tripOpt.isEmpty())
            throw new EntityNotFoundException("The trip does no exist", 404);

        tripRepository.deleteById(id);
    }

    @Override
    public TripClientOutputDTO addPassenger(Integer tripId, Integer idPassenger) {

        //Obtenemos el viaje y el cliente a asociar
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found",404));
        Client client = clientRepository.findById(idPassenger).orElseThrow(() -> new EntityNotFoundException("Client not found",404));
        //Contamos el numero de pasajeros de dicho viaje
        long numberOfPassenger = trip.getPassengers().stream().count();
        //Comprobamos, si tenemos 40 pasajeros, no caben mas
        if(numberOfPassenger >= 40){
            throw new EntityNotFoundException("El autobús esta completo. No puedes inscribirte en este viaje :(", 404);
        }

        else{
            trip.getPassengers().add(client);
            tripRepository.save(trip);
        }

        return new TripClientOutputDTO(trip,client);
    }

    @Override
    public String countPassengers(Integer tripId) {

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found",404));

        long numberOfPassenger = trip.getPassengers().stream().count();
        long availableSeats = 40 - numberOfPassenger;
        String texto = "";

        if(numberOfPassenger == 1)
            texto = " pasajero en este viaje";
        else
            texto= " pasajeros en este viaje";

        return "Tenemos " + numberOfPassenger + texto + "\nDISPONEMOS DE " + availableSeats + " ASIENTOS DISPONIBLES";
    }

    @Override
    public TripOutputDTO changeTripStatus(Integer tripId, boolean tripStatus) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found",404));

        trip.setStatus(tripStatus);
        tripRepository.save(trip);
        return new TripOutputDTO(trip);
    }

    @Override
    public String tripStatus(Integer tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new EntityNotFoundException("Trip not found",404));
        return new TripStatusDTO(trip).toString();
    }

}
