package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;





public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-l");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        //driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
String currentUrl = driver.getCurrentUrl();
if (currentUrl.contains("calendar")) {
    System.out.println("Current URL contains 'calendar'.");
} else {
    System.out.println("Current URL does not contain 'calendar'.");
}
System.out.println("End Test case: testCase01");
    }


    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");

        try{
      //  Click on the dropdown to select the monthdropdown
            WebElement monthText=driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Month']"));
            monthText.click();
        }
        catch (StaleElementReferenceException e) {
            WebElement monthText=driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Month']"));
            monthText.click();
        }

            Thread.sleep(2000);
    

        // Switch to the month dropdown
        try {
            WebElement monthDropdown = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Month']"));
            monthDropdown.click();
           
        } catch (StaleElementReferenceException e) {
            // Element is stale, re-find the element and retry the action
            WebElement monthDropdown = driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Month']"));
            monthDropdown.click();
        }
        Thread.sleep(2000);

      //  Verify the month text is appearing after selection

      try{
        WebElement monthText=driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Month']"));
        if (monthText != null && monthText.getText().contains("Month")) {
            System.out.println("Month text is appearing: " + monthText.getText());
        } else {
            System.out.println("Month text is not appearing.");
        }
    }
    catch (StaleElementReferenceException e) {
        WebElement monthText=driver.findElement(By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Month']"));
        if (monthText != null && monthText.getText().contains("Month")) {
            System.out.println("Month text is appearing: " + monthText.getText());
        } else {
            System.out.println("Month text is not appearing.");
        }
    }


        

    //     // Switch to the month view 

    //     try {
    //         WebElement monthViewButton=driver.findElement(By.xpath("//span[@class='VfPpkd-StrnGf-rymPhb-b9t22c' and text()='Month']"));
    //         monthViewButton.click();

    //         //WebElement monthTextElement = driver.findElement(By.xpath("//*[contains(text(), 'Month')]")); // Adjust the text 'Month' accordingly

    //         // Verify if the month text is appearing by checking if the text contains 'Month'
    //         if (monthTextElement != null && monthTextElement.getText().contains("Month")) {
    //             System.out.println("Month text is appearing: " + monthTextElement.getText());
    //         } else {
    //             System.out.println("Month text is not appearing.");
    //         }
    //     } catch (NoSuchElementException e) {
    //         // Element is stale, re-find the element and retry the action
    //         WebElement monthViewButton=driver.findElement(By.xpath("//span[@class='VfPpkd-StrnGf-rymPhb-b9t22c' and text()='Month']"));
    //          monthViewButton.click();

    //          WebElement monthViewHeader = driver.findElement(By.xpath("//*[@id='gb']/div[2]/div[2]/div[3]/div/div/div/div[5]/div/div[1]/div[1]/div/button/span"));
    //       if (monthViewHeader.isDisplayed()) {
    // System.out.println("Switch successfull Month view");
    //     } else {
    //        System.out.println("Switch failed Month view");
    //       }

    //     }

    //     // Ensure that you are switching to the month view
    //        WebElement monthViewHeader = driver.findElement(By.xpath("//*[@id='gb']/div[2]/div[2]/div[3]/div/div/div/div[5]/div/div[1]/div[1]/div/button/span"));
    //       if (monthViewHeader.isDisplayed()) {
    // System.out.println("Switch successfull Month view");
    //     } else {
    //        System.out.println("Switch failed Month view");
    //       }

    Thread.sleep(4000);

        // Click on the calendar to add a task

        try {
            WebElement calendarDay=driver.findElement(By.xpath("//div[@data-datekey='27782']"));
            calendarDay.click();
        } catch (StaleElementReferenceException e) {
            // Element is stale, re-find the element and retry the action
            WebElement calendarDay=driver.findElement(By.xpath("//div[@data-datekey='27782']"));
            calendarDay.click();
        }

        // Navigate to the Tasks tab
        driver.switchTo().activeElement();
        Thread.sleep(4000);
        try {
            
            WebElement tasksTab = driver.findElement(By.xpath("//button[@id='tabTask']"));
            tasksTab.click();
        } catch (StaleElementReferenceException e) {
            // Element is stale, re-find the element and retry the action
          //  driver.switchTo().activeElement();
            WebElement tasksTab = driver.findElement(By.xpath("//button[@id='tabTask']"));
        tasksTab.click();
        }


        // Select a date and enter task details
        // try{
        // WebElement taskDateField = driver.findElement(By.xpath("//*[@id='yDmH0d']/div/div/div[2]/span/div/div[1]/div[2]/div[1]/div[3]/div[3]/span/div/div[1]/div/div[1]/div/div/div[2]/div[1]/button/div[2]/div[1]"));
        // taskDateField.click();
        // taskDateField.clear();
        // taskDateField.sendKeys("April 5 2024");
        // taskDateField.sendKeys(Keys.ENTER);
        // }
        // catch (org.openqa.selenium.ElementNotInteractableException e) {   
        //     System.out.println("Element is not interactable: " + e.getMessage());  
        //     WebElement taskDateField = driver.findElement(By.xpath("//*[@id='yDmH0d']/div/div/div[2]/span/div/div[1]/div[2]/div[1]/div[3]/div[3]/span/div/div[1]/div/div[1]/div/div/div[2]/div[1]/button/div[2]/div[1]"));
        //     Thread.sleep(5000);
        //     taskDateField.click();
        //     taskDateField.clear();
        // taskDateField.sendKeys("April 5 2024");
        // taskDateField.sendKeys(Keys.ENTER);       
        // }

        //Enter the Title
        try{
            
        WebElement taskTitleField = driver.findElement(By.xpath("//input[@placeholder='Add title and time']"));
        taskTitleField.click();
        taskTitleField.sendKeys("Crio INTV Task Automation");
    } catch (NoSuchElementException e) {
        // Element is stale, re-find the element and retry the action
        driver.switchTo().activeElement();
        WebElement taskTitleField = driver.findElement(By.xpath("//input[@placeholder='Add title and time']"));
        taskTitleField.click();
        taskTitleField.sendKeys("Crio INTV Task Automation");
    }

        //Enter the Task Description
        try{
        WebElement taskDescriptionField = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        taskDescriptionField.sendKeys("Crio INTV Calendar Task Automation");
    } catch (StaleElementReferenceException e) {
        // Element is stale, re-find the element and retry the action
        WebElement taskDescriptionField = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        taskDescriptionField.sendKeys("Crio INTV Calendar Task Automation");
    }


    try{
        WebElement submitButton = driver.findElement(By.xpath("//span[starts-with(@class, 'VfPpkd') and text()='Save']/.."));
        Thread.sleep(2000);
        submitButton.click();
    }
    catch (ElementNotInteractableException e) {
                //Element is stale, re-find the element and retry the action
                WebElement submitButton = driver.findElement(By.xpath("//span[starts-with(@class, 'VfPpkd') and text()='Save']/.."));
                Thread.sleep(2000);
                submitButton.click();
    }

    System.out.println("end Test case: testCase02");
    }


    public void testCase03() throws InterruptedException{
        System.out.println("Start Test case: testCase03");

        // Click on an existing task
        try{
            WebElement existingTask = driver.findElement(By.xpath("//span[contains(text(), 'Crio INTV Task Automation')]/parent::*[contains(@class, 'QGRmIf')]")); 
        existingTask.click();
        }
        catch (NoSuchElementException e) {
            System.out.println("Error: Task details button not found.");
            WebElement existingTask = driver.findElement(By.xpath("//span[contains(text(), 'Crio INTV Task Automation')]/parent::*[contains(@class, 'QGRmIf')]")); 
        existingTask.click();

        }

        // Open the task details
        WebElement taskDetailsButton = driver.findElement(By.xpath("//button[@aria-label='Edit task']"));
        taskDetailsButton.click();

        // Update the new description
        try{
            WebElement taskDescriptionField = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
            taskDescriptionField.click();
        taskDescriptionField.clear();
        taskDescriptionField.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");

        }catch (StaleElementReferenceException e) {
            System.out.println("retrying the same action with stale");
            WebElement taskDescriptionField = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
            taskDescriptionField.click();
            taskDescriptionField.clear();
            taskDescriptionField.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
        }

        // Submit the Save button
        WebElement submitButton = driver.findElement(By.xpath("//span[starts-with(@class, 'VfPpkd') and text()='Save']/.."));
        submitButton.click();
        Thread.sleep(2000);

        // Wait for success message using FluentWait
        Wait<ChromeDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        WebElement updatedDescriptionSuccess = wait.until(webDriver ->
                webDriver.findElement(By.xpath("//div[@class='VYTiVb' and text()='Task updated']")));

        // Verify the updated description
        if (updatedDescriptionSuccess != null && updatedDescriptionSuccess.getText().contains("Task updated")) {
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Task updation failed.");
        }

        Thread.sleep(2000);

        //  Click on an existing task
         WebElement existing = driver.findElement(By.xpath("//span[contains(text(), 'Crio INTV Task Automation')]/parent::*[contains(@class, 'QGRmIf')]")); // Adjust XPath according to your task
         existing.click();
        Thread.sleep(2000);


         // Retrieve the updated description
        WebElement updatedDescription = driver.findElement(By.xpath("//div[contains(text(), 'Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application')]"));

        // Verify if the description is updated
        String updatedDescriptionText = updatedDescription.getText();
        String expectedUpdatedDescription = "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application"; // Modify this with your expected updated description
        if (updatedDescriptionText.equals(expectedUpdatedDescription)) {
            System.out.println("Description updated successfully: " + updatedDescriptionText);
        } else {
            System.out.println("Description update verification failed.");
        } 



        

//         WebElement updatedDescriptionField = driver.findElement(By.xpath("//textarea[@id='description']"));
//         String updatedDescription = updatedDescriptionField.getAttribute("value");

//         if (updatedDescription.equals("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application")) {
//          System.out.println("Updated description is correct.");
//        } 
//        else {
//          System.out.println("Updated description is incorrect.");
// }

        System.out.println("End Test case: testCase03");

    }


    public void testCase04() throws InterruptedException{
        System.out.println("Start Test case: testCase04");

        driver.navigate().refresh();

        

        try{
            driver.switchTo().defaultContent();
        }
        catch(UnhandledAlertException e){
            driver.switchTo().defaultContent();
        }

        System.out.println("Start Test case: testCase04");
        
        // driver.get("https://calendar.google.com/");
         Thread.sleep(3000);


        // Click on an existing task
        WebElement existing = driver.findElement(By.xpath("//span[contains(text(), 'Crio INTV Task Automation')]/parent::*[contains(@class, 'QGRmIf')]")); // Adjust XPath according to your task
        existing.click();

        // Open the task details
        WebElement taskDetailsButton = driver.findElement(By.xpath("//button[@aria-label='Edit task']"));
        taskDetailsButton.click();

        // Verify the title of the task
        try{
        WebElement taskTitle = driver.findElement(By.xpath("//input[@value='Crio INTV Task Automation']")); 
        String taskTitleText = taskTitle.getAttribute("value");

        if (taskTitleText.equals("Crio INTV Task Automation")) {
            System.out.println("Task title verified successfully.");
        } else {
            System.out.println("Task title is different and Failed.");
        }
    }
    catch(org.openqa.selenium.NoSuchElementException e){
        WebElement taskTitle = driver.findElement(By.xpath("//input[@value='Crio INTV Task Automation']")); 
        String taskTitleText = taskTitle.getAttribute("value");

        if (taskTitleText.equals("Crio INTV Task Automation")) {
            System.out.println("Task title verified successfully.");
        } else {
            System.out.println("Task title is different and Failed.");
        }

    }

        // Delete the task
        WebElement deleteButton = driver.findElement(By.xpath("//button[@aria-label='Delete']"));
        deleteButton.click();
        Thread.sleep(4000);

        // Confirm the task deletion
        WebElement deleteConfirmMessage = driver.findElement(By.xpath("//div[@class='VYTiVb' and text()='Task deleted']"));
        if (deleteConfirmMessage.getText().contains("Task deleted")) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task deletion failed.");
        }
        System.out.println("End Test case: testCase04");

    }
        }
