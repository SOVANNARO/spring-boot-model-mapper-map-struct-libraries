package org.tutorials.springbootmodelmappermapstructlibraries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tutorials.springbootmodelmappermapstructlibraries.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
