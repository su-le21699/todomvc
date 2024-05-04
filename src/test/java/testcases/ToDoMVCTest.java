package testcases;

import base.TestBase;
import common.Browsers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ToDoMVCPage;

import static common.Browsers.click;
import static common.Browsers.openBrowser;

public class ToDoMVCTest extends TestBase {
    ToDoMVCPage toDoMVCPage;
    @BeforeClass
    void setup() {
        Browsers.openBrowser("chrome");
        toDoMVCPage = new ToDoMVCPage();
        toDoMVCPage.open();

    }

    @Test
    void verifyUserAbleCreateNewTask() {
        String taskName = "task 1";
        int itemLeftBefore = toDoMVCPage.getItemLeft();

        toDoMVCPage.createTask(taskName);
        int itemLeftAfter = toDoMVCPage.getItemLeft();
        Assert.assertEquals(itemLeftAfter - itemLeftBefore, 1);

        toDoMVCPage.filterTask("All");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));

        toDoMVCPage.filterTask("Active");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));

        toDoMVCPage.filterTask("Completed");
        Assert.assertFalse(toDoMVCPage.isTaskDisplayed(taskName));

    }
    @Test
    void verifyUserMarkCompleteTask(){
        String taskName = "task 1";

        toDoMVCPage.filterTask("All");
        toDoMVCPage.createTask(taskName);

        int itemLeftBefore = toDoMVCPage.getItemLeft();
        toDoMVCPage.markComplete(taskName);
        //click(By.xpath("//label[.='task 1']/preceding-sibling::input"));

        int itemLeftAfter = toDoMVCPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore - itemLeftAfter, 1);

        toDoMVCPage.filterTask("All");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));

        toDoMVCPage.filterTask("Completed");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed(taskName));
    }
    @Test
    void verifyUserAbleDeleteTask(){
        String taskName = "task 1";
        toDoMVCPage.filterTask("All");
        toDoMVCPage.createTask(taskName);

        int itemLeftBefore = toDoMVCPage.getItemLeft();
        toDoMVCPage.markComplete(taskName);

        toDoMVCPage.deleteComplete(taskName);

        int itemLeftAfter = toDoMVCPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore - itemLeftAfter, 1);
    }
    @Test
    void verifyUserAbleChangeTaskName(){
        String taskName = "task 1";
        toDoMVCPage.filterTask("All");
        toDoMVCPage.createTask(taskName);
        int itemLeftBefore = toDoMVCPage.getItemLeft();

        toDoMVCPage.updateTaskName(taskName,"task 2");
        Assert.assertTrue(toDoMVCPage.isTaskDisplayed("task 2"));
        int itemLeftAfter = toDoMVCPage.getItemLeft();
        Assert.assertEquals(itemLeftBefore,itemLeftAfter);
    }
}