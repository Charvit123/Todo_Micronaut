package com.incubyte.todo;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

@Singleton
public class ArchiveDeletionJob {
    private final TodoRepository todoRepository;

    // Constructor injection or @Autowired
    public ArchiveDeletionJob(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Scheduled(cron = "0 */1 * * * *")
    public void delete() {
        todoRepository.deleteByStatusArchive();
    }
}
