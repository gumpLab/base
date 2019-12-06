package org.gumplab.essays.jpa.service;

import org.gumplab.essays.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BaseService extends JpaRepository<User, Long> {
}
