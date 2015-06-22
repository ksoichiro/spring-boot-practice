package com.example.spring.repository;

import com.example.spring.domain.Os;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsRepository extends JpaRepository<Os, Integer> {
}
