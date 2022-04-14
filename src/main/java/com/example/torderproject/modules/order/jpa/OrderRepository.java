package com.example.torderproject.modules.order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByAccountId(Long accountId);

    Order findByAccountId(Long accountId);
}
