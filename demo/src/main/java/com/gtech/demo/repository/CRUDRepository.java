package com.gtech.demo.repository;

import java.util.List;

import com.gtech.demo.entity.Product;
import com.gtech.demo.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CRUDRepository extends CrudRepository<Product, Long> {
    
}
