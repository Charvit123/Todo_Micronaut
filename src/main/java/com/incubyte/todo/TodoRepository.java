package com.incubyte.todo;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Integer> {
}
