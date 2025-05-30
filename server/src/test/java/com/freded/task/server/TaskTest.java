package com.freded.task.server;


import com.freded.dtos.TaskDTO;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@Disabled
@QuarkusTest
@TestSecurity(user = "testUser", roles = {"user"})
public class TaskTest {

     String persistedTaskId;

    @BeforeEach
    void setup() {
        if (persistedTaskId == null) {
            String taskName = "Task Test";
            String taskDescription = "This is a test task";
            TaskDTO newTask = new TaskDTO();

            newTask.setName(taskName);
            newTask.setDescription(taskDescription);

            persistedTaskId = given().contentType(MediaType.APPLICATION_JSON).body(newTask).when().post("/api/tasks").then().statusCode(200).body("name", is(taskName), "description", is(taskDescription)).extract().path("id");
        }
    }

    @Disabled
    @Test
    @TestTransaction
    @Order(1)
    public void testUpdateTask() {
        String taskDescription = "Updated This is a test task";
        String taskName = "Updated Task";


        TaskDTO updatedNewTask = new TaskDTO();
        updatedNewTask.setName(taskName);
        updatedNewTask.setDescription(taskDescription);

        given().contentType(MediaType.APPLICATION_JSON).body(updatedNewTask).when().put("/api/tasks/" + persistedTaskId).then().body("name", is(taskName), "description", is(taskDescription), "id", is(persistedTaskId));
    }

    @Disabled
    @Test
    @TestTransaction
    @Order(2)
    public void testGetAllTask() {


        given()
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get("/api/tasks?limit=100")
                .then()
                .statusCode(200)
                .body("size()", is(2))
                .body("[1].id", is(persistedTaskId))
                .body("[1].name", is("Updated Task"))
                .body("[1].description", is("Updated This is a test task"));


    }

}
