package formacionBackend.SpringCloud.trip.application;

import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientOutputDTO;
import formacionBackend.SpringCloud.client.infrastructure.repository.ClientRepository;
import formacionBackend.SpringCloud.exceptions.MyPersonalException;
import formacionBackend.SpringCloud.exceptions.UnprocessableEntityException;
import formacionBackend.SpringCloud.ticket.domain.Ticket;
import formacionBackend.SpringCloud.ticket.infrastructure.dtos.TicketOutputDTO;
import formacionBackend.SpringCloud.ticket.infrastructure.repository.TicketRepository;
import formacionBackend.SpringCloud.trip.domain.Trip;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripStatusDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TriplnputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
public class TripServiceImpl  implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TicketRepository ticketRepository;

    //todo check error for this method, no  throw UnprocessableEntityException, however throw other method
    @Override
    public TripOutputDTO createTrip(TriplnputDTO triplnputDTO) throws UnprocessableEntityException{

        if(triplnputDTO == null || triplnputDTO.getDestination().equals("") || triplnputDTO.getOrigin().equals("")) {
            throw new UnprocessableEntityException("Values cannot be null, check your information(8080)", 422);
        }

        //Comprobacion de las fechas, que la llegada no se antes de la partida, y que no sean anterior al dia que se crea un viaje
        if(triplnputDTO.getArrivalDate().before(triplnputDTO.getDepartureDate())) {
            throw new MyPersonalException("The arrival date cannot be greater than the departure date.", 404);
        }
        if(triplnputDTO.getDepartureDate().before(new Date())) {
            throw new MyPersonalException("The arrival date cannot be less than today.", 404);
        }
        //Comprobación de destino y origen, no pueden ser el mismo
        if (triplnputDTO.getOrigin().equals(triplnputDTO.getDestination())) {
            throw new MyPersonalException("The origin is the same as the destination!!!", 404);
        }

        Trip trip = new Trip();
        trip.createTrip(triplnputDTO);
        tripRepository.save(trip);

        return new TripOutputDTO(trip);
    }

    @Override
    public TripOutputDTO updateTrip(TriplnputDTO triplnputDTO, Integer id) throws MyPersonalException {

        if(triplnputDTO == null || triplnputDTO.getDestination().equals("") || triplnputDTO.getOrigin().equals("")) {
            throw new UnprocessableEntityException("Values cannot be null, check your information(8080)", 422);
        }

        //Comprobacion de las fechas, que la llegada no se antes de la partida, y que no sean anterior al dia que se crea un viaje
        if(triplnputDTO.getArrivalDate().before(triplnputDTO.getDepartureDate())) {
            throw new MyPersonalException("The arrival date cannot be greater than the departure date.", 404);
        }
        if(triplnputDTO.getDepartureDate().after(new Date())) {
            throw new MyPersonalException("The arrival date cannot be less than today.", 404);
        }
        //Comprobación de destino y origen, no pueden ser el mismo
        if (triplnputDTO.getOrigin().equals(triplnputDTO.getDestination())) {
            throw new MyPersonalException("The origin is the same as the destination!!!", 404);
        }

        Optional<Trip> tripOpt = tripRepository.findById(id);

        if(tripOpt.isEmpty())
            throw new MyPersonalException("The trip does no exist", 404);

        Trip trip = tripOpt.get();
        trip.createTrip(triplnputDTO);
        tripRepository.save(trip);

        return new TripOutputDTO(trip);

    }

    //todo check error for this method, no  throw UnprocessableEntityException, however throw other method
    @Override
    public TripOutputDTO getTrip(Integer id) throws MyPersonalException {

        log.info("tripService, buscando trip con id: " + id);
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new MyPersonalException("Trip not found",404));
        return new TripOutputDTO(trip);
    }

    @Override
    public String deleteTrip(Integer id) throws MyPersonalException {

        Optional<Trip> tripOpt = tripRepository.findById(id);

        if(tripOpt.isEmpty())
            throw new MyPersonalException("Trip not found", 404);

        tripRepository.deleteById(id);

        return "Viaje eliminado con exito";
    }

    @Override
    public TicketOutputDTO addPassenger(Integer tripId, Integer idPassenger) throws MyPersonalException {

        //Obtenemos el viaje y el cliente a asociar
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new MyPersonalException("Trip not found",404));
        Client client = clientRepository.findById(idPassenger).orElseThrow(() -> new MyPersonalException("Client not found",404));

       //IMPLEMENTAR AQUI QUE NO SE PUEDA RESERVAR UN TICKET SI YA ESTAS EN EL

        Set<Client> passengersForTrip = trip.getPassengers();
        if (passengersForTrip.contains(client)) {
            throw new MyPersonalException("This person is already on this trip", 503);
        }



        //Contamos el numero de pasajeros de dicho viaje
        long numberOfPassenger = trip.getPassengers().stream().count();
        //Comprobamos, si tenemos 40 pasajeros, no caben mas
        if(numberOfPassenger >= 40){
            throw new MyPersonalException("El autobús esta completo. No puedes inscribirte en este viaje :(", 503);

        }
        //Comprobamos el estado del viaje
        if(!trip.isStatus()) {
            throw new MyPersonalException("El autobús esta averiado. No puedes inscribirte en este viaje :(", 503);
        }


        //Si el estado del viaje  es true, y tenemos plazas libres..
        else{
            trip.getPassengers().add(client);
            tripRepository.save(trip);

            Ticket ticket = new Ticket();
            ticket.generateTicket(new TripOutputDTO(trip), new ClientOutputDTO(client));
            ticketRepository.save(ticket);

            return new TicketOutputDTO(ticket);
        }


    }

    @Override
    public String countPassengers(Integer tripId) throws MyPersonalException {

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new MyPersonalException("Trip not found",404));

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
    public TripOutputDTO changeTripStatus(Integer tripId, boolean tripStatus) throws MyPersonalException {

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new MyPersonalException("Trip not found",404));

        trip.setStatus(tripStatus);
        tripRepository.save(trip);
        return new TripOutputDTO(trip);
    }

    @Override
    public String tripStatus(Integer tripId) throws MyPersonalException {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new MyPersonalException("Trip not found",404));
        return new TripStatusDTO(trip).toString();
    }

}
