package org.gumplab.essays.module.service;

import org.gumplab.essays.module.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BaseService extends JpaRepository<User, Long> {
}
