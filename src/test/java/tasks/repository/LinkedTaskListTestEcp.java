package tasks.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import tasks.model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedTaskListTestEcp {

    private LinkedTaskList singleTaskList;
    private LinkedTaskList multipleTaskList;

    @BeforeEach
    void setUp() {
        singleTaskList = new LinkedTaskList();
        Task task1 = new Task("task1", new Date());
        singleTaskList.add(task1);

        multipleTaskList = new LinkedTaskList();
        Task task2 = new Task("task2", new Date());
        Task task3 = new Task("task3", new Date());
        multipleTaskList.add(task1);
        multipleTaskList.add(task2);
        multipleTaskList.add(task3);
    }

    // Valid Test Case 1: Lista cu un singur element, get task cu index 0
    @Test
    void getTask_SingleTaskList() {
        Task task = singleTaskList.getTask(0);
        assertNotNull(task);
        assertEquals("task1", task.getTitle());
    }

    // Valid Test Case 2: Lista cu mai multe taskuri, get task cu un index valid (ex: 1)
    @Test
    void getTask_MultipleTaskList() {
        Task task = multipleTaskList.getTask(1);
        assertNotNull(task);
        assertEquals("task2", task.getTitle());
    }

    // Invalid Test Case 1: Index mai mic decat 0
    @Test
    void getTask_IndexLessThanZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> singleTaskList.getTask(-1));
    }

    // Invalid Test Case 2: Index mai mare sau egal decat lungimea listei
    @Test
    void getTask_IndexGreaterThanOrEqualToSize() {
        assertThrows(IndexOutOfBoundsException.class, () -> singleTaskList.getTask(singleTaskList.size()));
    }
}