package formacionBackend.SpringCloud.client.infrastructure.dtos;

import formacionBackend.SpringCloud.client.domain.Client;
import lombok.Data;

@Data
public class ClientOutputDTO {

    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String telf;

    public ClientOutputDTO(Client client) {

        this.id = client.getIdClient();
        this.name = client.getName();
        this.surname = client.getSurname();
        this.age = client.getAge();
        this.email = client.getEmail();
        this.telf = client.getTelf();
    }
}
