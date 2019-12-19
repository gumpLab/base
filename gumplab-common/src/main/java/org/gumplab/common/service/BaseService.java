package org.gumplab.common.service;

import org.gumplab.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BaseService extends JpaRepository<User, Long> {
}
