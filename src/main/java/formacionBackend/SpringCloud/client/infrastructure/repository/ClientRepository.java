package formacionBackend.SpringCloud.client.infrastructure.repository;

import formacionBackend.SpringCloud.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
