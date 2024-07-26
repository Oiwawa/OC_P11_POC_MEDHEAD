package com.medhead.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingParametersException extends Exception {
  public MissingParametersException(String message) {
      super(message);
  }

}
