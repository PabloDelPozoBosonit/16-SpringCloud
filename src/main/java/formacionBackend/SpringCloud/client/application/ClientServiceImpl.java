package formacionBackend.SpringCloud.client.application;

import formacionBackend.SpringCloud.client.infrastructure.repository.ClientRepository;
import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientInputDTO;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientOutputDTO;
import formacionBackend.SpringCloud.exceptions.MyPersonalException;
import formacionBackend.SpringCloud.exceptions.UnprocessableEntityException;
import formacionBackend.SpringCloud.trip.infrastructure.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TripRepository tripRepository;

    @Override
    public ClientOutputDTO createClient(ClientInputDTO clientInputDTO) throws UnprocessableEntityException {

        if((clientInputDTO.getName() == null) || (clientInputDTO.getName().equals(""))) {
            throw new UnprocessableEntityException("Name field can not be null", 422);
        }

        else if (clientInputDTO.getAge() < 14) {
            throw new UnprocessableEntityException("You must be at least 14 years old to book a trip.", 422);
        }

        else {
            Client client = new Client();
            client.createClient(clientInputDTO);
            clientRepository.save(client);

            return new ClientOutputDTO(client);
        }


    }

    @Override
    public ClientOutputDTO updateClient(ClientInputDTO clientInputDTO, Integer id) throws MyPersonalException {

        if((clientInputDTO.getName() == null) || (clientInputDTO.getName().equals(""))) {
            throw new UnprocessableEntityException("Name field can not be null", 422);
        }

        else if (clientInputDTO.getAge() < 14) {
            throw new UnprocessableEntityException("You must be at least 14 years old to book a trip.", 422);
        }

        Optional<Client> clientOpt = clientRepository.findById(id);

        if(clientOpt.isEmpty())
            throw new MyPersonalException("Client not found", 404);

        Client client = clientOpt.get();

        client.createClient(clientInputDTO);
        clientRepository.save(client);

        return new ClientOutputDTO(client);
    }

    @Override
    public ClientOutputDTO getClient(Integer id) throws MyPersonalException {

        Client client = clientRepository.findById(id).orElseThrow(() -> new MyPersonalException("Client not found",404));

        return new ClientOutputDTO(client);
    }

    @Override
    public String deleteClient(Integer id) throws MyPersonalException {

        Optional<Client> clientOpt = clientRepository.findById(id);

        if(clientOpt.isEmpty())
            throw new MyPersonalException("Client not found", 404);

        Client client = clientOpt.get();


        try {
            clientRepository.deleteById(client.getIdClient());
            return "El cliente ha sido eliminado correctamente";
        }
        catch (Exception e) {
            return "El cliente NO se ha podido eliminar, intentelo de nuevo mas tarde (Posiblemente tenga un viaje de autobús pendiente)";
        }

    }

    @Override
    public List<Client> findAll() throws MyPersonalException {

        try {
            return clientRepository.findAll();
        }
        catch(Exception e) {
            e.printStackTrace();

            throw  new MyPersonalException("No se han podido obtener todos los clientes, intentelo de nuevo", 503);
        }
    }

}
