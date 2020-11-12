package com.saket.springboot.repository;

import com.saket.springboot.domain.ShoppingCart;
import com.saket.springboot.domain.User;
import com.saket.springboot.model.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByStatus(ShoppingCartStatus status);

    List<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
