package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        try {
            task=new Task("new task",Task.getDateFormat().parse("2023-02-12 10:10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTaskCreation() throws ParseException {
       assert task.getTitle() == "new task";
        System.out.println(task.getFormattedDateStart());
        System.out.println(task.getDateFormat().format(Task.getDateFormat().parse("2023-02-12 10:10")));
       assert task.getFormattedDateStart().equals(task.getDateFormat().format(Task.getDateFormat().parse("2023-02-12 10:10")));

    }

    //Lab4 tests

    @Test
    public void testSetTitle() {
        task.setTitle("Test set title");
        assertEquals("Test set title", task.getTitle());
    }

    @Test
    public void testSetActive() {
        task.setActive(true);
        assertEquals(true, task.isActive());
        task.setActive(false);
        assertEquals(false, task.isActive());
    }

    @AfterEach
    void tearDown() {
    }
}