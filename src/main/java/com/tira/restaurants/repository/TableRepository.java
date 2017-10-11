package com.tira.restaurants.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Table;

@Repository
public interface TableRepository extends CrudRepository<Table, Long> {

}
