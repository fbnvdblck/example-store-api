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
import be.bulck.example.store.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

  @RequestMapping(value = "", method = RequestMethod.GET)
  @PreAuthorize("hasAuthority('PERM_READ_PRODUCT')")
  public List<Product> getProducts() {
    return productService.findAll();
  }
}
