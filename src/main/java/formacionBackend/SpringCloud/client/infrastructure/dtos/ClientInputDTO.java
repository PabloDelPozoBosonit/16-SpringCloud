package formacionBackend.SpringCloud.client.infrastructure.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInputDTO {

    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String telf;
}
