package com.example.torderproject.menu.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MenuRepository extends JpaRepository<Menu, Long> {
}
