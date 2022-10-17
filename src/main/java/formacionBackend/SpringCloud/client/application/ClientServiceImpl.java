package formacionBackend.SpringCloud.client.application;

import formacionBackend.SpringCloud.client.infrastructure.repository.ClientRepository;
import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientInputDTO;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientOutputDTO;
import formacionBackend.SpringCloud.exceptions.EntityNotFoundException;
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
    public ClientOutputDTO createClient(ClientInputDTO clientInputDTO) throws Exception {

        if((clientInputDTO.getName() == null) || (clientInputDTO.getName().equals("")))
            throw new UnprocessableEntityException("Client field can not be null", 422);

        Client client = new Client();
        client.createClient(clientInputDTO);
        clientRepository.save(client);

        return new ClientOutputDTO(client);
    }

    @Override
    public ClientOutputDTO updateClient(ClientInputDTO clientInputDTO, Integer id) throws EntityNotFoundException {

        Optional<Client> clientOpt = clientRepository.findById(id);

        if(clientOpt.isEmpty())
            throw new EntityNotFoundException("The client does no exist", 404);

        if ((clientInputDTO.getName() == null) || (clientInputDTO.getName().equals("")))
            throw new UnprocessableEntityException("Client field can not be null", 422);

        Client client = clientOpt.get();

        client.createClient(clientInputDTO);
        clientRepository.save(client);

        return new ClientOutputDTO(client);
    }

    @Override
    public ClientOutputDTO getClient(Integer id) throws Exception {

        Client client = clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found",404));

        return new ClientOutputDTO(client);
    }

    @Override
    public String deleteClient(Integer id) {

        Optional<Client> clientOpt = clientRepository.findById(id);

        if(clientOpt.isEmpty())
            throw new EntityNotFoundException("The client does no exist", 404);

        try {
            clientRepository.deleteById(id);
        }
        catch (Exception e) {
            return "El cliente NO se ha podido eliminar, intentelo de nuevo mas tarde";
        }
        return "El cliente ha sido eliminado correctamente";
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
