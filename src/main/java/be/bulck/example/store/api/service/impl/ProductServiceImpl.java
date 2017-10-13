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
import be.bulck.example.store.api.exception.ResourceConflictException;
import be.bulck.example.store.api.exception.ResourceNotFoundException;
import be.bulck.example.store.api.repository.ProductRepository;
import be.bulck.example.store.api.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * The service implementation for product entity.
 *
 * @author Fabien Vanden Bulck
 */
@Service
public class ProductServiceImpl implements ProductService {
  /** The logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Collection<Product> findAll() {
    return (Collection<Product>) productRepository.findAll();
  }

  @Override
  public Product find(Long id) {
    Product product = productRepository.findOne(id);

    if (product == null) {
      throw new ResourceNotFoundException("Product with id '" + id + "' can not be found");
    }

    return product;
  }

  @Override
  public Product findByName(String name) {
    Product product = productRepository.findByName(name);

    if (product == null) {
      throw new ResourceNotFoundException("Product with name '"+ name + "' can not be found");
    }

    return product;
  }

  @Override
  public Product create(Product product) {
    product.setId(null);
    Product productWithSameName = productRepository.findByName(product.getName());

    if (productWithSameName != null) {
      throw new ResourceConflictException("Unable to create the product provided: " +
          "a product with name '" + productWithSameName.getName() + "' already exists");
    }

    Product productCreated = productRepository.save(product);
    LOGGER.info("Product '" + productCreated.getName() + "' (" + productCreated.getId() + ") has been created.");

    return productCreated;
  }

  @Override
  public Product update(Product product) {
    Product productToUpdate = find(product.getId());
    productToUpdate.setName(product.getName());
    productToUpdate.setPrice(product.getPrice());
    productToUpdate.setDescription(product.getDescription());

    Product productWithSameName = productRepository.findByName(productToUpdate.getName());
    if (productWithSameName != null && !productWithSameName.getId().equals(productToUpdate.getId())) {
      throw new ResourceConflictException("Unable to update the provided provided: " +
          "an other product with name '" + productWithSameName.getName() + "' exists");
    }

    Product productUpdated = productRepository.save(productToUpdate);
    LOGGER.info("Product '" + productUpdated.getName() + "' (" + productUpdated.getId() + ") has been updated.");

    return productUpdated;
  }

  @Override
  public void delete(Long id) {
    Product productToDelete = find(id);

    productRepository.delete(productToDelete);
    LOGGER.info("Product '" + productToDelete.getName() + "' (" + productToDelete.getId() + ") has been deleted");
  }

  @Override
  public void deleteAll() {
    productRepository.deleteAll();;
    LOGGER.info("All products have been deleted");
  }
}
