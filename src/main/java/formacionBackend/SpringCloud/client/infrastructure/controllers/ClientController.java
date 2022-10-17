package formacionBackend.SpringCloud.client.infrastructure.controllers;

import formacionBackend.SpringCloud.client.application.ClientService;
import formacionBackend.SpringCloud.client.domain.Client;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientInputDTO;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("client")
@RestController
public class ClientController {

    @Autowired
    ClientService clientService;


    @PostMapping
    public ClientOutputDTO createClient(@RequestBody ClientInputDTO clientInputDTO) throws Exception{

        return clientService.createClient(clientInputDTO);
    }

    @GetMapping("get/{id}")
    public ClientOutputDTO getClient(@PathVariable Integer id) throws  Exception {

        return clientService.getClient(id);
    }

    @PutMapping("update/{id}")
    public ClientOutputDTO updateClient(@RequestBody ClientInputDTO clientInputDTO, @PathVariable Integer id) throws Exception {

        return clientService.updateClient(clientInputDTO, id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteClient(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }

    @GetMapping("getAll")
    public List<Client> findAll() {
        return clientService.findAll();
    }



}
