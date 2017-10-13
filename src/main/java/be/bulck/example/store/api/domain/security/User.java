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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The entity class representing an user.
 *
 * @author Fabien Vanden Bulck
 */
@Entity
@Table(name = "user")
public class User implements Serializable, Comparable<User>, UserDetails {
  private static final long serialVersionUID = 1L;

  /** The identifier of user. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id", updatable = false)
  private Long id;

  /** The username of user. */
  @Column(name =" username", unique = true, nullable = false)
  private String username;

  /** The e-mail address of user. */
  @Column(name = "email", unique = true, nullable = false)
  private String email;

  /** The password (encrypted) of user. */
  @Column(name = "password", nullable = false)
  private String password;

  /** The first name of user. */
  @Column(name = "first_name", nullable = false)
  private String firstName;

  /** The last name of user. */
  @Column(name = "last_name", nullable = false)
  private String lastName;

  /** The authorization to be used of user. */
  @Column(name = "enabled", nullable = false)
  private boolean enabled;

  /** The roles of user. */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
  )
  private Set<Role> roles;

  /**
   * Constructs an instance of user.
   */
  public User() {
    enabled = false;
    roles = new HashSet<>();
  }

  /**
   * Gets the identifier of user.
   *
   * @return the identifier of user
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets the identifier of user.
   *
   * @param id the new identifier of user
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the username of user.
   *
   * @return the username of user
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of user.
   *
   * @param username the new username of user
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the e-mail address of user.
   *
   * @return the e-mail address of user
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the e-mail address of user.
   *
   * @param email the new e-mail address of user
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password (encrypted) of user.
   *
   * @return the password (encrypted) of user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password (encrypted) of user.
   *
   * @param password the new password (encrypted) of user
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the first name of user.
   *
   * @return the first name of user
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of user.
   *
   * @param firstName the new first name of user
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of user.
   *
   * @return the last name of user
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of user.
   *
   * @param lastName the new last name of user
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the full name of user.
   *
   * @return the full name of user
   */
  public String getFullName() {
    return firstName + " " + lastName;
  }

  /**
   * Gets the authorization to be used of user.
   *
   * @return the authorization to be used of user
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * Sets the authorization to be used of user.
   *
   * @param enabled the new authorization to be used of user
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * Gets the roles of user (guaranteed without duplicates).
   *
   * @return a hash set which contains the roles of user.
   */
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * Sets the roles of user.
   *
   * @param roles the new hash set which contains the roles of user
   */
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  /**
   * Adds a role to user.
   *
   * @param role the role to add to user
   */
  public void addRole(Role role) {
    roles.add(role);
  }

  /**
   * Removes a role from user.
   *
   * @param role the role to remove from user
   */
  public void removeRole(Role role) {
    roles.remove(role);
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<Authority> authorities = new HashSet<>();

    roles.stream().forEach(role -> {
        authorities.add(role);
        role.getPermissions().stream().forEach(permission -> authorities.add(permission));
    });

    return authorities;
  }

  @Override
  public String toString() {
    return getFullName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (enabled != user.enabled) return false;
    if (id != null ? !id.equals(user.id) : user.id != null) return false;
    if (username != null ? !username.equals(user.username) : user.username != null) return false;
    if (email != null ? !email.equals(user.email) : user.email != null) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
    if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
    return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (enabled ? 1 : 0);
    return result;
  }

  @Override
  public int compareTo(User o) {
    return getFullName().compareTo(o.getFullName());
  }
}
