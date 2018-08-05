package com.github.topefremov.dockertest.domain.repository;

import com.github.topefremov.dockertest.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<User, Long> {
}
