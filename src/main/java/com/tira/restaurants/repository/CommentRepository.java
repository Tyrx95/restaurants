package com.tira.restaurants.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tira.restaurants.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

}
