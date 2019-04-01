package com.artisan.order.repository;

import com.artisan.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Order,String>  第一个是要操作的对象，第二个是实体类中标注的@Id的字段的类型 （主键类型）
public interface OrderRepository extends JpaRepository<Order,String> {

}
