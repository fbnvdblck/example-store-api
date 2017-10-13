/*
 * Copyright (C) 2017 The Store API Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package be.bulck.example.store.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * The resource exception handler.
 *
 * @author Fabien Vanden Bulck
 */
@RestControllerAdvice
public class ResourceExceptionHandler {
  /** The logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceExceptionHandler.class);

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ResourceExceptionResponse> resourceNotFound(ResourceNotFoundException exception) {
    final int ERROR_CODE = 2;
    final HttpStatus ERROR_STATUS = HttpStatus.NOT_FOUND;
    String errorMessage = exception.getMessage();

    ResourceExceptionResponse response = new ResourceExceptionResponse(ERROR_STATUS, ERROR_CODE, errorMessage);

    LOGGER.info(response.toString());
    return new ResponseEntity<ResourceExceptionResponse>(response, ERROR_STATUS);
  }

  @ExceptionHandler(ResourceConflictException.class)
  public ResponseEntity<ResourceExceptionResponse> resourceConflict(ResourceConflictException exception) {
    final int ERROR_CODE = 2;
    final HttpStatus ERROR_STATUS = HttpStatus.CONFLICT;
    String errorMessage = exception.getMessage();

    ResourceExceptionResponse response = new ResourceExceptionResponse(ERROR_STATUS, ERROR_CODE, errorMessage);

    LOGGER.info(response.toString());
    return new ResponseEntity<ResourceExceptionResponse>(response, ERROR_STATUS);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ResourceExceptionResponse> resourceNotReadable(HttpMessageNotReadableException exception) {
    final int ERROR_CODE = 3;
    final HttpStatus ERROR_STATUS = HttpStatus.BAD_REQUEST;
    final String errorMessage = "Invalid representation: content sent is not readable";

    ResourceExceptionResponse response = new ResourceExceptionResponse(ERROR_STATUS, ERROR_CODE, errorMessage);

    LOGGER.info(response.toString());
    return new ResponseEntity<ResourceExceptionResponse>(response, ERROR_STATUS);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResourceExceptionResponse> resourceNotValid(MethodArgumentNotValidException exception) {
    final int ERROR_CODE = 4;
    final HttpStatus ERROR_STATUS = HttpStatus.BAD_REQUEST;
    StringBuilder errorMessageBuilder = new StringBuilder("Validation error: one or several inputs are invalid");

    BindingResult bindingResult = exception.getBindingResult();

    String fields = bindingResult.getFieldErrors().stream()
        .map(fieldError -> fieldError.getField())
        .collect(Collectors.joining(", "));
    errorMessageBuilder.append(" (").append(fields).append(")");

    ResourceExceptionResponse response = new ResourceExceptionResponse(ERROR_STATUS, ERROR_CODE, errorMessageBuilder.toString());
    response.setAdditionalErrors(
        bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList())
    );

    LOGGER.info(response.toString());
    return new ResponseEntity<ResourceExceptionResponse>(response, ERROR_STATUS);
  }
}
