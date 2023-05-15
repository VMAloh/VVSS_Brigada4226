package tasks;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;
import tasks.repository.LinkedTaskList;
import tasks.services.TasksService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TasksServiceIntegrationTest {

    @Test
    public void testGetObservableList() {
        ArrayTaskList list = new ArrayTaskList();
        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);
        list.add(task1);
        list.add(task2);

        TasksService service = new TasksService(list);

        assertEquals(2, service.getObservableList().size());
        assertTrue(service.getObservableList().contains(task1));
        assertTrue(service.getObservableList().contains(task2));
    }

    @Test
    public void testGetIntervalInHours() {
        ArrayTaskList list = new ArrayTaskList();
        Task task = Mockito.mock(Task.class);
        when(task.getRepeatInterval()).thenReturn(3600);  // 1 hour interval
        list.add(task);

        TasksService service = new TasksService(list);

        assertEquals("01:00", service.getIntervalInHours(task));
    }

    @Test
    public void testParseFromStringToSeconds() {
        ArrayTaskList list = new ArrayTaskList();
        Task task = new Task("task", new Date(), new Date(System.currentTimeMillis() + 3600000), 3600);  // 1 hour interval
        list.add(task);

        TasksService service = new TasksService(list);

        assertEquals(3600, service.parseFromStringToSeconds("01:00"));
    }

    @Test
    public void testFilterTasks() {
        ArrayTaskList list = new ArrayTaskList();
        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);

        when(task1.getRepeatInterval()).thenReturn(3600);
        when(task2.getRepeatInterval()).thenReturn(3600);


        Date date1 = new Date();
        Date date2 = new Date();
        date2.setTime(date2.getTime() + 3600 * 1000);
        when(task1.getStartTime()).thenReturn(date1);
        when(task2.getStartTime()).thenReturn(date2);


        list.add(task1);
        list.add(task2);

        TasksService service = new TasksService(list);

        Date startDate = new Date();
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 3600 * 1000);

        Iterable<Task> filteredTasks = service.filterTasks(startDate, endDate);
        int count = 0;
        for (Task task : filteredTasks) {
            count++;
            assertEquals(task2, task);
        }

        assertEquals(1, count);
    }

}
