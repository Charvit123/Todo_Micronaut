package com.incubyte.todo;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

@Singleton
public class ArchiveDeletionJob {
    private final TodoRepository todoRepository;
    Logger logger = LoggerFactory.getLogger(ArchiveDeletionJob.class);

    public ArchiveDeletionJob(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void delete() {
        logger.info("Running Job");
        Calendar cutoffTime = Calendar.getInstance();
        cutoffTime.add(Calendar.MINUTE, -5);
        todoRepository.deleteByStatusArchive(cutoffTime);
    }
}
