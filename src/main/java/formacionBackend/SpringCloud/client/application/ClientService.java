package formacionBackend.SpringCloud.client.application;

import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientInputDTO;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientOutputDTO;

import java.util.List;

public interface ClientService {

    ClientOutputDTO createClient(ClientInputDTO clientInputDTO) throws Exception;
    ClientOutputDTO updateClient(ClientInputDTO clientInputDTO, Integer id) throws  Exception;
    ClientOutputDTO getClient(Integer id) throws  Exception;
    String deleteClient(Integer id);

    List<Client>findAll();

}
