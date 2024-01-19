package com.incubyte.todo;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class ArchiveDeletionTest {
    @Inject
    ArchiveDeletionJob job;
    @Inject
    TodoRepository todoRepository;
    @Test
    void delete_records_with_archive_status(){
        Calendar cutoffTime = Calendar.getInstance();
        cutoffTime.add(Calendar.MINUTE, -5);
        job.delete();
        List<Todo> todos = todoRepository.findByStatusArchive(cutoffTime);
        assertThat(todos).isEmpty();
    }
}