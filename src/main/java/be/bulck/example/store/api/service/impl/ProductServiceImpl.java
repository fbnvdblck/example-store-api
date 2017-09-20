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

package be.bulck.example.store.api.service.impl;

import be.bulck.example.store.api.domain.Product;
import be.bulck.example.store.api.repository.ProductRepository;
import be.bulck.example.store.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The service implementation for product entity.
 *
 * @author Fabien Vanden Bulck
 */
@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    return (List<Product>) productRepository.findAll();
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }
}
