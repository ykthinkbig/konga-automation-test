package KongaAutomationTest;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KongaAutomation {
    private WebDriver driver;
    private WebElement element;

    public void setUp() throws InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();

        //choose the driver base on the operating system in use
        if(os.contains("mac")){
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        }
        else{
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        }

        //initialize the driver
        driver = new ChromeDriver();

        //visit konga page
        driver.get("https://www.konga.com/");

        Thread.sleep(5000);

        //maximize the window
        driver.manage().window().maximize();

        //navigate to the login page
        driver.findElement(By.cssSelector("#app-content-wrapper > div.e5dc4_DR8xw > nav > div._2d4bb_2rbWX > div > div.e5d46_2l87X._16536_xxIKG > div._12e27_1r3kc > a")).click();

        String email = "xxxxx";
        //enter the email
        driver.findElement(By.cssSelector("#username")).sendKeys(email);

        String password = "xxxx";
        //enter the password
        driver.findElement(By.cssSelector("#password")).sendKeys(password);

        //click on the login button
        driver.findElement(By.cssSelector("#app-content-wrapper > div.c6dfe_HidJc > section > section > aside > div._5ed6c_R--fu > div > form > div._2aac2_3bwnD._841f1_1RZK9 > button")).click();

        Thread.sleep(5000);

        //select a category
        driver.findElement(By.cssSelector(".ef511_2c5_i:nth-child(3)")).click();

        //hold on for the subcategory to load
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //select subcategory
        driver.findElement(By.cssSelector(".c0c22_z8ibs:nth-child(4) a")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //search for an item #jsSearchInput
        driver.findElement(By.xpath("/html/body/div[1]/div/section/div[2]/nav/div[1]/div/div[2]/div/form/div[1]/input")).sendKeys("ghana black soap");

        Thread.sleep(2000);

        //click on the search button
        driver.findElement(By.cssSelector("#app-content-wrapper > div.e5dc4_DR8xw > nav > div._2d4bb_2rbWX > div > div._63857_1TmYU > div > form > button")).click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Thread.sleep(2000);

        //add to cart
        try {
            System.out.println("add element to cart");
            WebElement addToCart =  driver.findElement(By.cssSelector("#mainContent > section._9cac3_2I9l4 > section > section > section > section > ul > li:nth-child(1) > div > div > div._4941f_1HCZm > form > div._2aac2_3bwnD._549f7_zvZ8u._49c0c_3Cv2D._977c5_2vBMq > button"));
            addToCart.click();
            //b49ee_2pjyI
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            System.out.println("element becomes stale");
            WebElement addToCart =  driver.findElement(By.cssSelector("#mainContent > section._9cac3_2I9l4 > section > section > section > section > ul > li:nth-child(1) > div > div > div._4941f_1HCZm > form > div._2aac2_3bwnD._549f7_zvZ8u._49c0c_3Cv2D._977c5_2vBMq > button"));
            addToCart.click();
        }

        Thread.sleep(10000);
        //click to view cart preview
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement addToCart =  driver.findElement(By.cssSelector("#app-content-wrapper > div.e5dc4_DR8xw > nav > div._2d4bb_2rbWX > div > div.e5d46_2l87X._16536_xxIKG > a"));
        addToCart.click();

        Thread.sleep(5000);
        System.out.println("get the cart increment button");

        //get the cart increment button
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement incCart =  driver.findElement(By.cssSelector("#js-cart-items-wrapper > div > div > div.ed23a_3OozF > div.bb31e_ax8ka > form > div > div > button.c4079_DW1vB"));


        System.out.println("start increment button");

        //increment cart item
        for(int i=0; i<3; i++){
            incCart.click();
            Thread.sleep(5000);
        }


        System.out.println("done increment button");

        //checkout order

        Thread.sleep(5000);
        System.out.println("checkout order");
        //checking out the order
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement checkOut =  driver.findElement(By.cssSelector("#app-content-wrapper > div.c6dfe_HidJc > section > section > aside > div.fb90d_2mSyi > div > div._2aac2_3bwnD._841f1_1RZK9 > button"));
        checkOut.click();

        Thread.sleep(5000);

        //click on the pay now option

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement payNow =  driver.findElement(By.cssSelector("#mainContent > form > div > section > div > div._83d32_380t6 > form > ul > li:nth-child(1) > div > button"));
        payNow.click();

        Thread.sleep(5000);

        //click on continue to payment
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement continueToPay =  driver.findElement(By.cssSelector("#mainContent > form > div > section > div > div.f082d_safAL > div > button"));
        continueToPay.click();

        Thread.sleep(5000);

        //switch to the iframe element
        driver.switchTo().frame("kpg-frame-component");

        //select card payment
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement cardPayment =  driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[3]/div[1]/div[2]/div/div[1]/button"));
        cardPayment.click();

        //Enter Card details
        driver.findElement(By.cssSelector("#card-number")).sendKeys("5199124265343786");

        //Enter expiry
        driver.findElement(By.cssSelector("#expiry")).sendKeys("02/22");

        //Enter cvv
        driver.findElement(By.cssSelector("#cvv")).sendKeys("925");

        //enter pin
        driver.findElement(By.cssSelector("#card-pin")).sendKeys("1992");

        //select the validateCardForm button
        driver.findElement(By.cssSelector("#validateCardForm")).click();

        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //print the error message
        WebElement getMsg = driver.findElement(By.cssSelector("#card-number_unhappy"));
        System.out.println(getMsg.getText());

    }

    public static void main(String args[]) throws InterruptedException {
        KongaAutomation test= new KongaAutomation();
        test.setUp();

    }
}

