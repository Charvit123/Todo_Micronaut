package com.incubyte.todo;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.model.Pageable;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo,Integer> {
    List<Todo> findAll(@NonNull Pageable pageable);

    @Query(value = "SELECT * FROM todo_test.todo WHERE status='ARCHIVE'", nativeQuery = true)
    List<Todo> findByStatusArchive();

    @Query(value = "DELETE FROM todo_test.todo WHERE status='ARCHIVE'", nativeQuery = true)
    void deleteByStatusArchive();
}
