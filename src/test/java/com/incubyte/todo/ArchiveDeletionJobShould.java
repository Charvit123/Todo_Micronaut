package com.incubyte.todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Calendar;

public class ArchiveDeletionJobShould {

    private TodoRepository todoRepository;
    private ArchiveDeletionJob job;

    @BeforeEach
    void setUp() {
        todoRepository = Mockito.mock(TodoRepository.class);
        job = new ArchiveDeletionJob(todoRepository);
    }
    @Test
    void delete_todo_which_has_archive_status_after_specific_time(){
        job.delete();
        Mockito.verify(todoRepository).deleteByStatusArchive(Mockito.any());
    }
}
