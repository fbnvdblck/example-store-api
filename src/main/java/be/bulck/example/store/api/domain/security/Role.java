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
 * The entity class representing an user's role.
 *
 * @author Fabien Vanden Bulck
 */
@Entity
@Table(name = "role")
public class Role implements Serializable, Comparable<Role>, Authority {
  private static final long serialVersionUID = 1L;

  /** The authority prefix. */
  private static final String AUTHORITY_PREFIX = "ROLE_";

  /** The identifier of role. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id", updatable = false)
  private Long id;

  /** The name of role. */
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  /** The label of role. */
  @Column(name = "label", nullable = false)
  private String label;

  /** The description of role. */
  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  /** The users having the role. */
  @JsonIgnore
  @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
  private Set<User> users;

  /** The permissions of role. */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "role_permission",
      joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id")
  )
  private Set<Permission> permissions;

  /**
   * Constructs an instance of role.
   */
  public Role() {
    users = new HashSet<>();
    permissions = new HashSet<>();
  }

  /**
   * Gets the identifier of role.
   *
   * @return the identifier of role
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the identifier of role
   *
   * @param id the new identifier of role
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the name of role.
   *
   * @return the name of role
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of role.
   *
   * @param name the new name of role
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the label of role.
   *
   * @return the label of role
   */
  public String getLabel() {
    return label;
  }

  /**
   * Sets the label of role.
   *
   * @param label the new label of role
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * Gets the description of role.
   *
   * @return the description of role
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of role.
   *
   * @param description the new description of role
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the users having the role (guaranteed without duplicates).
   *
   * @return a hash set containing the users having the role
   */
  public Set<User> getUsers() {
    return users;
  }

  /**
   * Sets the users having the role.
   *
   * @param users the new hash set containing the users having the role
   */
  public void setUsers(Set<User> users) {
    this.users = users;
  }

  /**
   * Gets the permissions of role (guaranteed without duplicates).
   *
   * @return a hash set which contains the permissions of role
   */
  public Set<Permission> getPermissions() {
    return permissions;
  }

  /**
   * Sets the permissions of role.
   *
   * @param permissions the new hash set which contains the permissions of role
   */
  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  /**
   * Adds a permission to role.
   *
   * @param permission the permission to add to role
   */
  public void addPermission(Permission permission) {
    permissions.add(permission);
  }

  /**
   * Removes a permission from role.
   *
   * @param permission the permission to remove from role
   */
  public void removePermission(Permission permission) {
    permissions.remove(permission);
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

    Role role = (Role) o;

    if (id != null ? !id.equals(role.id) : role.id != null) return false;
    if (name != null ? !name.equals(role.name) : role.name != null) return false;
    if (label != null ? !label.equals(role.label) : role.label != null) return false;
    return description != null ? description.equals(role.description) : role.description == null;
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
  public int compareTo(Role o) {
    return getName().compareTo(o.getName());
  }
}
