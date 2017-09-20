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

package be.bulck.example.store.api.security.oauth2.jwt;

import be.bulck.example.store.api.domain.security.User;
import be.bulck.example.store.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The custom token enhancer to add additional information to token.
 *
 * @author Fabien Vanden Bulck
 */
public class CustomTokenEnhancer implements TokenEnhancer {
  @Autowired
  private UserService userService;

  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    Map<String, Object> additionalInformation = new HashMap<>();
    User user = userService.findByUsername(authentication.getName());

    if (user != null) {
      additionalInformation.put("firstName", user.getFirstName());
      additionalInformation.put("lastName", user.getLastName());
      additionalInformation.put("email", user.getEmail());

      Set<String> roles = new HashSet<>();
      user.getRoles().stream().forEach(role -> roles.add(role.getName()));
      additionalInformation.put("roles", roles);
    }

    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);

    return accessToken;
  }
}
