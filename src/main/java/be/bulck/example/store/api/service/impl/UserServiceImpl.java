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

package be.bulck.example.store.api.service.impl;

import be.bulck.example.store.api.domain.security.User;
import be.bulck.example.store.api.repository.UserRepository;
import be.bulck.example.store.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The service implementation for user entity.
 *
 * @author Fabien Vanden Bulck
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findByUsername(username);

    if (user != null) {
      return user;
    } else {
      throw new UsernameNotFoundException("The username '" + username + "' is not found");
    }
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findOneByUsername(username);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }
}
