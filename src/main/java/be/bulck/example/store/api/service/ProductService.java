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

import java.util.List;

/**
 * The service for product entity.
 *
 * @author Fabien Vanden Bulck
 */
public interface ProductService {
  /**
   * Finds all products.
   *
   * @return a list containing all products
   */
  List<Product> findAll();

  /**
   * Saves a product.
   *
   * @param product the product to save
   *
   * @return the product saved
   */
  Product save(Product product);
}
