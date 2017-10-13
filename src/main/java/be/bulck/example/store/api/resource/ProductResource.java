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

package be.bulck.example.store.api.resource;

import be.bulck.example.store.api.domain.Product;
import be.bulck.example.store.api.dto.ProductCreationDto;
import be.bulck.example.store.api.dto.ProductDto;
import be.bulck.example.store.api.dto.ProductUpdateDto;
import be.bulck.example.store.api.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * The resource for product entity.
 *
 * @author Fabien Vanden Bulck
 */
@RestController
@RequestMapping("/products")
public class ProductResource {
  @Autowired
  private ProductService productService;

  @Autowired
  private ModelMapper modelMapper;

  @RequestMapping(method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('PERM_READ_PRODUCT')")
  public ResponseEntity<?> getProducts() {
    Collection<Product> products = productService.findAll();

    Type listType = new TypeToken<Collection<ProductDto>>() {}.getType();
    Collection<ProductDto> productDtos = modelMapper.map(products, listType);

    return new ResponseEntity(productDtos, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('PERM_READ_PRODUCT')")
  public ResponseEntity<?> getProduct(@PathVariable("id") Long id) {
    Product product = productService.find(id);

    ProductDto productDto = modelMapper.map(product, ProductDto.class);

    return new ResponseEntity(productDto, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  @PreAuthorize("hasAuthority('PERM_WRITE_PRODUCT')")
  public ResponseEntity<?> createProduct(@Valid @RequestBody ProductCreationDto productDto) {
    Product productToCreate = modelMapper.map(productDto, Product.class);

    Product productCreated = productService.create(productToCreate);

    return new ResponseEntity(productCreated, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @PreAuthorize("hasAuthority('PERM_WRITE_PRODUCT')")
  public ResponseEntity<?> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductUpdateDto productDto) {
    Product productToUpdate = modelMapper.map(productDto, Product.class);
    productToUpdate.setId(id);

    Product productUpdated = productService.update(productToUpdate);

    return new ResponseEntity(productUpdated, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.DELETE)
  @PreAuthorize("hasAuthority('PERM_WRITE_PRODUCT')")
  public ResponseEntity<?> deleteProducts() {
    productService.deleteAll();

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @PreAuthorize("hasAuthority('PERM_WRITE_PRODUCT')")
  public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
    productService.delete(id);

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
