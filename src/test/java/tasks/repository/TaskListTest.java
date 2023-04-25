package tasks.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    private ArrayTaskList arrayTaskList;
    private LinkedTaskList linkedTaskList;

    @BeforeEach
    void setUp() {
        arrayTaskList = new ArrayTaskList();
        linkedTaskList = new LinkedTaskList();
    }

    @Test
    void testIncoming_ArrayTaskList_NoTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        TaskList incomingTasks = arrayTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof ArrayTaskList);
        assertEquals(0, incomingTasks.size());
    }

    @Test
    void testIncoming_LinkedTaskList_NoTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        TaskList incomingTasks = linkedTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof LinkedTaskList);
        assertEquals(0, incomingTasks.size());
    }

    @Test
    void testIncoming_ArrayTaskList_WithTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        Task task = new Task("Task 1", new Date(50000L));
        task.setActive(true);
        arrayTaskList.add(task);

        TaskList incomingTasks = arrayTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof ArrayTaskList);
        assertEquals(1, incomingTasks.size());
        assertSame(task, incomingTasks.getTask(0));
    }

    @Test
    void testIncoming_LinkedTaskList_WithTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        Task task = new Task("Task 1", new Date(50000L));
        task.setActive(true);
        linkedTaskList.add(task);

        TaskList incomingTasks = linkedTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof LinkedTaskList);
        assertEquals(1, incomingTasks.size());
        assertSame(task, incomingTasks.getTask(0));
    }

    @Test
    void testIncoming_ArrayTaskList_WithRepeatedTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        Task task = new Task("Repeated Task", new Date(500000L));
        task.setActive(true);
        arrayTaskList.add(task);

        TaskList incomingTasks = arrayTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof ArrayTaskList);
        assertEquals(0, incomingTasks.size());
    }

    @Test
    void testIncoming_LinkedTaskList_WithRepeatedTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        Task task = new Task("Repeated Task", new Date(500000L));
        task.setActive(true);
        linkedTaskList.add(task);

        TaskList incomingTasks = linkedTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof LinkedTaskList);
        assertEquals(0, incomingTasks.size());
    }

    @Test
    void testIncoming_ArrayTaskList_NullTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        arrayTaskList.add(null);

        TaskList incomingTasks = arrayTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof ArrayTaskList);
        assertEquals(0, incomingTasks.size());
    }

    @Test
    void testIncoming_LinkedTaskList_NullTasks() {
        Date from = new Date(0L);
        Date to = new Date(100000L);

        linkedTaskList.add(null);

        TaskList incomingTasks = linkedTaskList.incoming(from, to);
        assertTrue(incomingTasks instanceof LinkedTaskList);
        assertEquals(0, incomingTasks.size());
    }

}