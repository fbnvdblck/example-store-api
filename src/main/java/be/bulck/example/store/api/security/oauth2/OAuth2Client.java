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

package be.bulck.example.store.api.security.oauth2;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing an oAuth2 client.
 *
 * @author Fabien Vanden Bulck
 */
public class OAuth2Client {
  /** The identifier of client. */
  private String id;

  /** The secret of client. */
  private String secret;

  /** The resource targeted by client. */
  private String resource;

  /** The name of client. */
  private String name;

  /** The scopes allowed of client. */
  private List<String> scopes;

  /** The authorization grant types allowed of client. */
  private List<String> grantTypes;

  /** The access token validity (in seconds) of client. */
  private int accessTokenValidity;

  /** The refresh token validity (in seconds) of client. */
  private int refreshTokenValidity;

  /**
   * Constructs an instance of client.
   */
  public OAuth2Client() {
    scopes = new ArrayList<>();
    grantTypes = new ArrayList<>();
    accessTokenValidity = 0;
    refreshTokenValidity = 0;
  }

  /**
   * Gets the identifier of client.
   *
   * @return the identifier of client
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the identifier of client.
   *
   * @param id the new identifier of client
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the secret of client.
   *
   * @return the secret of client
   */
  public String getSecret() {
    return secret;
  }

  /**
   * Sets the secret of client.
   *
   * @param secret the new secret of client
   */
  public void setSecret(String secret) {
    this.secret = secret;
  }

  /**
   * Gets the resource targeted by client.
   *
   * @return the resource targeted by client
   */
  public String getResource() {
    return resource;
  }

  /**
   * Sets the resource targeted by client.
   *
   * @param resource the new resource targeted by client
   */
  public void setResource(String resource) {
    this.resource = resource;
  }

  /**
   * Gets the name of client.
   *
   * @return the name of client
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of client.
   *
   * @param name the new name of client
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the scopes allowed of client.
   *
   * @return a hash set which contains the scopes allowed of client
   */
  public List<String> getScopes() {
    return scopes;
  }

  /**
   * Sets the scopes allowed of client.
   *
   * @param scopes the new hash set which contains the scopes allowed of client
   */
  public void setScopes(List<String> scopes) {
    this.scopes = scopes;
  }

  /**
   * Gets the authorization grant types allowed of client.
   *
   * @return a hash set which contains the authorization grant types allowed of client
   */
  public List<String> getGrantTypes() {
    return grantTypes;
  }

  /**
   * Sets the authorization grant types allowed of client.
   *
   * @param grantTypes the new authorization grant types allowed of client
   */
  public void setGrantTypes(List<String> grantTypes) {
    this.grantTypes = grantTypes;
  }

  /**
   * Gets the access token validity (in seconds) of client.
   *
   * @return the access token validity of client
   */
  public int getAccessTokenValidity() {
    return accessTokenValidity;
  }

  /**
   * Sets the access token validity (in seconds) of client.
   *
   * @param accessTokenValidity the new access token validity of client
   */
  public void setAccessTokenValidity(int accessTokenValidity) {
    this.accessTokenValidity = accessTokenValidity;
  }

  /**
   * Gets the refresh token validity (in seconds) of client.
   *
   * @return the refresh token validity of client
   */
  public int getRefreshTokenValidity() {
    return refreshTokenValidity;
  }

  /**
   * Sets the refresh token validity (in seconds) of client.
   *
   * @param refreshTokenValidity the new refresh token validity of client
   */
  public void setRefreshTokenValidity(int refreshTokenValidity) {
    this.refreshTokenValidity = refreshTokenValidity;
  }

  @Override
  public String toString() {
    return id;
  }
}
