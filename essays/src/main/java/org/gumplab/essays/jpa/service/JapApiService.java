package org.gumplab.essays.jpa.service;

import org.gumpLab.essays.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface JapApiService extends JpaRepository<User, Long> {
}
