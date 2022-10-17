package formacionBackend.SpringCloud.trip.infrastructure.repository;

import formacionBackend.SpringCloud.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Integer> {
}
