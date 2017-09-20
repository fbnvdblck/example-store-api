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

package be.bulck.example.store.api.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The entity class representing a product.
 *
 * @author Fabien Vanden Bulck
 */
@Entity
@Table(name = "product")
public class Product implements Serializable, Comparable<Product> {
  private static final long serialVersionUID = 1L;

  /** The identifier of product. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name =" product_id", updatable = false)
  private Long id;

  /** The name of product. */
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  /** The price of product. */
  @Column(name = "price", nullable = false, scale = 2)
  private BigDecimal price;

  /** The description of product. */
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  /**
   * Constructs an instance of product.
   */
  public Product() {}

  /**
   * Gets the identifier of product.
   *
   * @return the identifier of product
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the identifier of product.
   *
   * @param id the new identifier of product
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the name of product.
   *
   * @return the name of product
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of product.
   *
   * @param name the new name of product
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the price of product.
   *
   * @return the price of product
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * Sets the price of product.
   *
   * @param price the new price of product
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * Gets the description of product.
   *
   * @return the description of product
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of product.
   *
   * @param description the new description of product
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Product product = (Product) o;

    if (id != null ? !id.equals(product.id) : product.id != null) return false;
    if (name != null ? !name.equals(product.name) : product.name != null) return false;
    if (price != null ? !price.equals(product.price) : product.price != null) return false;
    return description != null ? description.equals(product.description) : product.description == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }

  @Override
  public int compareTo(Product o) {
    return getName().compareTo(o.getName());
  }
}
