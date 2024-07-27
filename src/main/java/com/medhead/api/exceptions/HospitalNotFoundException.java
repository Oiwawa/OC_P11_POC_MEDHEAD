
package com.medhead.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class HospitalNotFoundException extends RuntimeException{
    public HospitalNotFoundException(String message) {
      super(message);
  }
}
