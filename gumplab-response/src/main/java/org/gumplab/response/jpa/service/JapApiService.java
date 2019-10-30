package org.gumplab.response.jpa.service;

import org.gumplab.response.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface JapApiService extends JpaRepository<User, Long> {
}
