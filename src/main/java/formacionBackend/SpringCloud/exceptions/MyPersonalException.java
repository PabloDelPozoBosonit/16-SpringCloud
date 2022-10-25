package formacionBackend.SpringCloud.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class MyPersonalException extends RuntimeException{

    Date timeStamp;
    int httpCode;

    public MyPersonalException(String message, int httpCode){
        super(message);
        setTimeStamp(new Date());
        setHttpCode(httpCode);
    }
}