package tasks.services;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.Task;
import tasks.repository.ArrayTaskList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TasksServiceTest {
    @Mock
    private ArrayTaskList mockTasks;

    private TasksService tasksService;

    @BeforeAll
     void setUp() {
        MockitoAnnotations.initMocks(this);
        tasksService = new TasksService(mockTasks);
    }

    @Test
     void testGetObservableList() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task("Task 1", new Date()));
        taskList.add(new Task("Task 2", new Date()));
        when(mockTasks.getAll()).thenReturn(taskList);

        ObservableList<Task> observableList = tasksService.getObservableList();
        assertEquals(2, observableList.size());
    }

    @Test
     void testGetIntervalInHours() {
        Task mockTask = mock(Task.class);
        when(mockTask.getRepeatInterval()).thenReturn(7200); // 2 hours in seconds

        String interval = tasksService.getIntervalInHours(mockTask);
        assertEquals("02:00", interval);
    }

    @Test
     void testFormTimeUnit() {
        String timeUnit = tasksService.formTimeUnit(5);
        assertEquals("05", timeUnit);
    }

    @Test
     void testParseFromStringToSeconds() {
        int seconds = tasksService.parseFromStringToSeconds("02:30");
        assertEquals(9000, seconds);
    }
}
