package formacionBackend.SpringCloud.client.application;

import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientInputDTO;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientOutputDTO;
import formacionBackend.SpringCloud.exceptions.UnprocessableEntityException;

import java.util.List;

public interface ClientService {

    ClientOutputDTO createClient(ClientInputDTO clientInputDTO) throws UnprocessableEntityException;
    ClientOutputDTO updateClient(ClientInputDTO clientInputDTO, Integer id) throws  Exception;
    ClientOutputDTO getClient(Integer id) throws  Exception;
    String deleteClient(Integer id)throws  Exception;

    List<Client>findAll() throws Exception;

}
