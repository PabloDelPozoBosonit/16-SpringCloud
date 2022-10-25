package formacionBackend.SpringCloud.client.domain;

import com.sun.istack.NotNull;
import formacionBackend.SpringCloud.client.infrastructure.dtos.ClientInputDTO;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Client {

    @Id
    @GeneratedValue
    private Integer idClient;

    private String name;
    private String surname;
    private Integer age;
    @Column(unique=true)
    private String email;
    private String telf;

    public void createClient(ClientInputDTO clientInputDTO) {

        this.name = clientInputDTO.getName();
        this.surname = clientInputDTO.getSurname();
        this.age = clientInputDTO.getAge();
        this.email = clientInputDTO.getEmail();
        this.telf = clientInputDTO.getTelf();
    }
}
