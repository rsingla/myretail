package com.target.retail.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.target.retail.exception.ApiError;
import com.target.retail.exception.ApiException;

@Controller
public class ControllerExceptionHandler {

  protected final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ApiException.class)
  @ResponseBody
  public ResponseEntity<ApiError> handleApiException(HttpServletRequest req, ApiException exception) {

    ApiError error = new ApiError();
    error.setField(exception.getField());
    error.setMessage(exception.getMessage());
    error.setCode(exception.getCode());

    log.error("Error : " + error);

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
