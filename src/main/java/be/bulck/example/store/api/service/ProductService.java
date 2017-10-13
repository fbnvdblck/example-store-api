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

package be.bulck.example.store.api.service;

import be.bulck.example.store.api.domain.Product;
import be.bulck.example.store.api.exception.ResourceConflictException;
import be.bulck.example.store.api.exception.ResourceNotFoundException;

import java.util.Collection;

/**
 * The service for product entity.
 *
 * @author Fabien Vanden Bulck
 */
public interface ProductService {
  /**
   * Finds all products.
   *
   * @return a collection containing all products
   */
  Collection<Product> findAll();

  /**
   * Finds a product by identifier.
   *
   * @param id the identifier of product
   *
   * @return the product with corresponding identifier
   *
   * @throws ResourceNotFoundException if the product can not be found
   */
  Product find(Long id);

  /**
   * Finds a product by name.
   *
   * @param name the name of product
   *
   * @return the product with corresponding name
   *
   * @throws ResourceNotFoundException if the product can not be found
   */
  Product findByName(String name);

  /**
   * Creates a product.
   *
   * @param product the product to create
   *
   * @return the product created
   *
   * @throws ResourceConflictException if a product already exists with the name provided
   */
  Product create(Product product);

  /**
   * Updates a product.
   *
   * @param product the product to update
   *
   * @return the product updated
   *
   * @throws ResourceNotFoundException if the product can not be found
   * @throws ResourceConflictException if a product already exists with the name provided
   */
  Product update(Product product);

  /**
   * Deletes a product.
   *
   * @param id the identifier of product to delete
   *
   * @throws ResourceNotFoundException if the product to delete is not found
   */
  void delete(Long id);

  /**
   * Deletes all products.
   */
  void deleteAll();
}
