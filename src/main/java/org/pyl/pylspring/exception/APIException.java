package org.pyl.pylspring.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class APIException extends Exception {

    private final HttpStatus httpStatus;
    public APIException(String message, HttpStatus httpStatus) {
        super(message);

        this.httpStatus = httpStatus;
    }
}
