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

package be.bulck.example.store.api.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The class allowing to write Access-Control directives in response (pre-flight).
 *
 * @author Fabien Vanden Bulck
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
  @Value("${cors.origin-allowed}")
  String originAllowed;

  @Value("${cors.methods-allowed}")
  String methodsAllowed;

  @Value("${cors.headers-allowed}")
  String headersAllowed;

  @Value("${cors.allow-credentials}")
  String allowCredentials;

  @Value("${cors.max-age}")
  String maxAge;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    response.setHeader("Access-Control-Allow-Origin", originAllowed);
    response.setHeader("Access-Control-Allow-Methods", methodsAllowed);
    response.setHeader("Access-Control-Allow-Headers", headersAllowed);
    response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
    response.setHeader("Access-Control-Max-Age", maxAge);

    if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
      response.setStatus(HttpServletResponse.SC_OK);
    }

    chain.doFilter(req, res);
  }

  @Override
  public void destroy() {}
}
