package formacionBackend.SpringCloud.trip.infrastructure.controllers;

import formacionBackend.SpringCloud.ticket.infrastructure.dtos.TicketOutputDTO;
import formacionBackend.SpringCloud.trip.application.TripService;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripClientOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TriplnputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("trip")
@Slf4j
public class TripController {

    @Autowired
    TripService tripService;

    @PostMapping
    public TripOutputDTO createTrip(@RequestBody TriplnputDTO triplnputDTO) throws Exception {
        return tripService.createTrip(triplnputDTO);
    }

    @GetMapping("get/{id}")
    public TripOutputDTO getTrip(@PathVariable Integer id) throws Exception{
        log.info("TripController, TripId: " + id);
        return  tripService.getTrip(id);
    }

    @PutMapping("update/{id}")
    public TripOutputDTO updateTrip(@RequestBody TriplnputDTO triplnputDTO, @PathVariable Integer id) throws Exception{
        return tripService.updateTrip(triplnputDTO, id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteTrip(@PathVariable Integer id)throws Exception {
        return tripService.deleteTrip(id);
    }

    @PostMapping("addPassenger/{idTrip}/{idPassenger}")
    public TicketOutputDTO addPassenger(@PathVariable Integer idTrip, @PathVariable Integer idPassenger) throws Exception{
        return tripService.addPassenger(idTrip,idPassenger);
    }

    @GetMapping("count/{idTrip}")
    public String countPassengers(@PathVariable Integer idTrip)throws Exception {
        return tripService.countPassengers(idTrip);
    }

    @PostMapping("changeStatus/{idTrip}/{status}")
    public TripOutputDTO changeStatus(@PathVariable Integer idTrip,@PathVariable boolean status) throws Exception{

        return tripService.changeTripStatus(idTrip,status);
    }

    @GetMapping("getStatus/{tripId}")
    public String getStatus(@PathVariable Integer tripId)throws Exception {
        return tripService.tripStatus(tripId);
    }

}
