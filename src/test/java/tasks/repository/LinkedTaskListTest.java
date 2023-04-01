package tasks.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tasks.model.Task;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LinkedTaskListTest {

    LinkedTaskList linkedTaskList;
    Integer n;

    @BeforeAll
    void setUp() {
        linkedTaskList = new LinkedTaskList();
        linkedTaskList.add(new Task("t1", new Date()));
        linkedTaskList.add(new Task("t1", new Date()));
        linkedTaskList.add(new Task("t1", new Date()));
        linkedTaskList.add(new Task("t1", new Date()));
        n=4;
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 4, 5})
    @DisplayName("NegativeTest")
     void BVANegativeTest(int position) {
        try{
            Task task = linkedTaskList.getTask(position);
            assert(false);
        }catch(Exception e) {

        }
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void BVAPositiveTest(int position) {
        try{
            Task task = linkedTaskList.getTask(position);
            System.out.println("Task retrieved");
        }catch(Exception e) {
            assert(false);
        }
    }

    @Disabled
    @Test
    void BVATest() {
        Task task = linkedTaskList.getTask(1);
    }

    @AfterAll
    void tearDown() {
    }
}