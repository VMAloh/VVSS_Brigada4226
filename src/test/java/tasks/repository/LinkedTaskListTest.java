package tasks.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.Task;
import static org.mockito.Mockito.*;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedTaskListTest {

    LinkedTaskList linkedTaskList;
    Integer n;

    @BeforeAll
    void setUp() {
        linkedTaskList = new LinkedTaskList();
        linkedTaskList.add(new Task("task1", new Date()));
        linkedTaskList.add(new Task("task2", new Date()));
        linkedTaskList.add(new Task("task3", new Date()));
        linkedTaskList.add(new Task("task4", new Date()));
        n=4;
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 4, 5})
    @DisplayName("NegativeTest")
     void BVANegativeTest(int position) {
        assertThrows(IndexOutOfBoundsException.class, ()->linkedTaskList.getTask(position));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void BVAPositiveTest(int position) {
        try{
            Task task = linkedTaskList.getTask(position);
            assertNotNull(task);
            String titl = "task"+(position+1);
            assertEquals(titl, task.getTitle());
        }catch(Exception e) {
            assert(false);
        }
    }

    @Disabled
    @Test
    void BVATest() {
        Task task = linkedTaskList.getTask(1);
    }

    //Lab4 tests

    @Test
    void spyingAdd() {
        Task task1=new Task("task1", new Date());
        Task task2=new Task("task2", new Date());
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        LinkedTaskList spyList = spy(linkedTaskList);

        spyList.add(task1);
        spyList.add(task2);

        verify(spyList).add(task1);
        verify(spyList).add(task2);

        assertEquals(2,spyList.size());
    }

    @Test
    void spyingRemove() {
        Task task1=new Task("task1", new Date());
        Task task2=new Task("task2", new Date());
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        linkedTaskList.add(task1);
        linkedTaskList.add(task2);

        LinkedTaskList spyList = spy(linkedTaskList);
        spyList.remove(task1);
        spyList.remove(task2);

        verify(spyList).remove(task1);
        verify(spyList).remove(task2);

        assertEquals(0, spyList.size());
    }

    @AfterAll
    void tearDown() {
    }
}