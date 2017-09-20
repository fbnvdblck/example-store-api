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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The oAuth2 client authorization configuration.
 *
 * @author Fabien Vanden Bulck
 */
@Configuration
@ConfigurationProperties("oauth2")
public class OAuth2ClientAuthorizationConfiguration {
  /** The list of oAuth2 clients allowed. */
  private List<OAuth2Client> clients;

  /**
   * Constructs an instance of oAuth2 client authorization configuration.
   */
  public OAuth2ClientAuthorizationConfiguration() {
    clients = new ArrayList<>();
  }

  /**
   * Gets the oAuth2 clients allowed.
   *
   * @return a hash set which contains the oAuth2 clients allowed
   */
  public List<OAuth2Client> getClients() {
    return clients;
  }

  /**
   * Sets the oAuth2 clients allowed.
   *
   * @param clients the new hash set which contains the oAuth2 clients allowed
   */
  public void setClients(List<OAuth2Client> clients) {
    this.clients = clients;
  }
}
