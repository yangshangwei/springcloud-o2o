package com.artisan.order.repository;

import com.artisan.order.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail ,String> {
}
