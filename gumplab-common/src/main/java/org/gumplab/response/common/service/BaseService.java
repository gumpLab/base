package org.gumplab.response.common.service;

import org.gumplab.response.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BaseService extends JpaRepository<User, Long> {
}
