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

import be.bulck.example.store.api.security.oauth2.jwt.CustomTokenEnhancer;
import be.bulck.example.store.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * The oAuth2 authorization server configuration.
 *
 * @author Fabien Vanden Bulck
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
  @Value("${oauth2.jwt.hmac-key}")
  private String jwtHmacKey;

  @Value("${oauth2.token.access.validity:3600}")
  private int accessTokenValidity;

  @Value("${oauth2.token.refresh.validity:1209600}")
  private int refreshTokenValidity;

  @Autowired
  private OAuth2ClientAuthorizationConfiguration clientAuthorizationConfiguration;

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer configurer) throws Exception {
    configurer
        .tokenStore(tokenStore())
        .tokenEnhancer(tokenEnhancerChain())
        .authenticationManager(authenticationManager)
        .userDetailsService(userService);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    InMemoryClientDetailsServiceBuilder clientDetailsServiceBuilder = clients.inMemory();

    clientAuthorizationConfiguration.getClients().stream().forEach(client -> {
      if (client.getAccessTokenValidity() <= 0) {
        client.setAccessTokenValidity(accessTokenValidity);
      }

      if (client.getRefreshTokenValidity() <= 0) {
        client.setRefreshTokenValidity(refreshTokenValidity);
      }

      clientDetailsServiceBuilder
          .withClient(client.getId())
          .secret(client.getSecret())
          .resourceIds(client.getResource())
          .scopes(client.getScopes().toArray(new String[client.getScopes().size()]))
          .authorizedGrantTypes(client.getGrantTypes().toArray(new String[client.getGrantTypes().size()]))
          .accessTokenValiditySeconds(client.getAccessTokenValidity())
          .refreshTokenValiditySeconds(client.getRefreshTokenValidity());
    });
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public TokenEnhancer tokenEnhancer() {
    return new CustomTokenEnhancer();
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(jwtHmacKey);

    return converter;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public TokenEnhancerChain tokenEnhancerChain() {
    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));

    return tokenEnhancerChain;
  }
}
