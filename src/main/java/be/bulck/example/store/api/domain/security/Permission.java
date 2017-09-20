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

package be.bulck.example.store.api.domain.security;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The entity class representing an user's permission (through roles).
 *
 * @author Fabien Vanden Bulck
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable, Comparable<Permission>, Authority {
  private static final long serialVersionUID = 1L;

  /** The authority prefix. */
  private static final String AUTHORITY_PREFIX = "PERM_";

  /** The identifier of permission. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "permission_id", unique = false)
  private Long id;

  /** The name of permission. */
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  /** The label of permission. */
  @Column(name = "label", nullable = false)
  private String label;

  /** The description of permission. */
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  /** The roles having the permission. */
  @JsonIgnore
  @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
  private Set<Role> roles;

  /**
   * Constructs an instance of permission.
   */
  public Permission() {
    roles = new HashSet<>();
  }

  /**
   * Gets the identifier of permission.
   *
   * @return the identifier of permission
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the identifier of permission.
   *
   * @param id the new identifier of permission
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the name of permission.
   *
   * @return the name of permission
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of permission.
   *
   * @param name the new name of permission
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the label of permission.
   *
   * @return the label of permission
   */
  public String getLabel() {
    return label;
  }

  /**
   * Sets the label of permission.
   *
   * @param label the new label of permission
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Gets the description of permission.
   *
   * @return the description of permission
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of permission.
   *
   * @param description the new description of permission
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the roles having the permission (guaranteed without duplicates).
   *
   * @return a hash set containg the roles having the permission
   */
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * Sets the roles having the permission.
   *
   * @param roles the new hash set which contains the roles having the permission
   */
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String getAuthority() {
    return AUTHORITY_PREFIX + getName();
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Permission that = (Permission) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (label != null ? !label.equals(that.label) : that.label != null) return false;
    return description != null ? description.equals(that.description) : that.description == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (label != null ? label.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }

  @Override
  public int compareTo(Permission o) {
    return getName().compareTo(o.getName());
  }
}
