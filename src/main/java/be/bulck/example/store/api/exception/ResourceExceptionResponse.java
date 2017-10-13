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

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The class representing a HTTP response when a resource exception is thrown.
 *
 * @author Fabien Vanden Bulck
 */
public class ResourceExceptionResponse implements Serializable {
  private static final long serialVersionUID = 1L;

  /** The error code. */
  private int code;

  /** The HTTP status. */
  private HttpStatus status;

  /** The error message. */
  private String message;

  /** The additional errors. */
  private List<String> additionalErrors;

  /**
   * Constructs an instance of resource exception response.
   *
   * @param status the error HTTP status
   * @param code the error code
   * @param message the error message
   */
  public ResourceExceptionResponse(HttpStatus status, int code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
    this.additionalErrors = new ArrayList<>();
  }

  /**
   * Gets the error code.
   *
   * @return the error code.
   */
  public int getCode() {
    return code;
  }

  /**
   * Sets the error code.
   *
   * @param code the new error code
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * Gets the error HTTP status.
   *
   * @return the error HTTP status
   */
  public HttpStatus getStatus() {
    return status;
  }

  /**
   * Sets the error HTTP status.
   *
   * @param status the new error HTTP status
   */
  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  /**
   * Gets the error message.
   *
   * @return the error message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the error message.
   *
   * @param message the new error message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets additional errors.
   *
   * @return an array list containing additional errors
   */
  public List<String> getAdditionalErrors() {
    return additionalErrors;
  }

  /**
   * Sets additional errors.
   *
   * @param additionalErrors the new array list containing additional errors
   */
  public void setAdditionalErrors(List<String> additionalErrors) {
    this.additionalErrors = additionalErrors;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder
        .append("[#")
        .append(getCode())
        .append(" ")
        .append(getStatus().value())
        .append(":")
        .append(getStatus().getReasonPhrase())
        .append("] ")
        .append(getMessage());

    return builder.toString();
  }
}
