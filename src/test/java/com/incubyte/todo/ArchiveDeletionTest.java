package com.incubyte.todo;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ArchiveDeletionTest {
    @Inject
    ArchiveDeletionJob job;
    @Inject
    TodoRepository todoRepository;
    @Test
    void delete_records_with_archive_status(){
        job.delete();
        List<Todo> todos = todoRepository.findByStatusArchive();
        assertThat(todos).isEmpty();
    }
}