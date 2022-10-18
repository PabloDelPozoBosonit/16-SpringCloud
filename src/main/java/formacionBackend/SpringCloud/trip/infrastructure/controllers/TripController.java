package formacionBackend.SpringCloud.trip.infrastructure.controllers;

import formacionBackend.SpringCloud.trip.application.TripService;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripClientOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TripOutputDTO;
import formacionBackend.SpringCloud.trip.infrastructure.dtos.TriplnputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("trip")
public class TripController {

    @Autowired
    TripService tripService;

    @PostMapping
    public TripOutputDTO createTrip(@RequestBody TriplnputDTO triplnputDTO) throws Exception {
        return tripService.createTrip(triplnputDTO);
    }

    @GetMapping("get/{id}")
    public TripOutputDTO getTrip(@PathVariable Integer id) throws Exception{
        return  tripService.getTrip(id);
    }

    @PutMapping("update/{id}")
    public TripOutputDTO updateTrip(@RequestBody TriplnputDTO triplnputDTO, @PathVariable Integer id) throws Exception{
        return tripService.updateTrip(triplnputDTO, id);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTrip(@PathVariable Integer id) {
        tripService.deleteTrip(id);
    }

    @PostMapping("addPassenger/{idTrip}/{idPassenger}")
    public TripClientOutputDTO addPassenger(@PathVariable Integer idTrip, @PathVariable Integer idPassenger) {
        return tripService.addPassenger(idTrip,idPassenger);
    }

    @GetMapping("count/{idTrip}")
    public String countPassengers(@PathVariable Integer idTrip) {
        return tripService.countPassengers(idTrip);
    }

    @PostMapping("changeStatus/{idTrip}/{status}")
    public TripOutputDTO changeStatus(@PathVariable Integer idTrip,@PathVariable boolean status) {

        return tripService.changeTripStatus(idTrip,status);
    }

    @GetMapping("getStatus/{tripId}")
    public String getStatus(@PathVariable Integer tripId) {
        return tripService.tripStatus(tripId);
    }

}
