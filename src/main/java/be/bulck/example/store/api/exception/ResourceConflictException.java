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

/**
 * The exception thrown when a resource is in conflict with an other resource.
 *
 * @author Fabien Vanden Bulck
 */
public class ResourceConflictException extends RuntimeException {
  /**
   * Constructs an instance of resource conflict exception.
   *
   * @param message the message
   */
  public ResourceConflictException(String message) {
    super(message);
  }

  /**
   * Constructs an instance of resource conflict exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public ResourceConflictException(String message, Throwable cause) {
    super(message, cause);
  }
}
