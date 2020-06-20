package org.auto.plate.utils.impl;

import com.sun.org.apache.xml.internal.security.Init;
import org.apache.log4j.Logger;
import org.auto.plate.entity.AutoElement;
import org.auto.plate.entity.ExecuteCaseLog;
import org.auto.plate.utils.ElementOperate;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ElementOperateIml implements ElementOperate {

    private static Logger logger = Logger.getLogger(ElementOperateIml.class);


    /**
     * 执行case
     * @param projectaddress
     * @param elementList
     * @param paramList
     * @param model
     * @param executeCaseLog
     * @return
     */
    @Override
    public ExecuteCaseLog excuteTestCase(String projectaddress, List<AutoElement> elementList,
                                         List<String> paramList, int model, ExecuteCaseLog executeCaseLog, String browserType) throws Exception{
        WebDriver driver = this.getDriver(model, browserType);
        logger.debug("*****auto_test_case_start*****");
        executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + "*****auto_test_case_start*****" + "</br>");
        driver.get(projectaddress);
        try {
            for (int i = 0; i < elementList.size(); i++) {
                driver =  this.operateElement(driver,elementList.get(i),paramList.get(i),executeCaseLog);
            }
        } catch (Exception e) {
            driver.quit();
            return executeCaseLog;
        }
        logger.debug("*****auto_test_case_end*****");
        executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + "*****auto_test_case_end*****" + "</br></br>");
        driver.quit();
        return executeCaseLog;
    }

    /**
     * 获取元素
     * @param localType
     * @param localAddress
     * @return
     */
    private WebElement getElement(WebDriver driver, String localType, String localAddress) throws NoSuchElementException {
        int timeOut = 10;
        switch(localType){
            case "id" :
                return new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.id(localAddress)));
            case "classname" :
                return new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.className(localAddress)));
            case "xpath" :
                return new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.xpath(localAddress)));
            case "linkText" :
                return new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.linkText(localAddress)));
            case "name" :
                return new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.name(localAddress)));
            case "partialLinkText" :
                return new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(localAddress)));
            case "tagName" :
                return  new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.tagName(localAddress)));
            case "cssSelector" :
                return new WebDriverWait(driver, timeOut).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(localAddress)));
            case "Document" :
                return (WebElement) ((JavascriptExecutor)driver).executeScript(localAddress);
            default : //可选
                throw new NoSuchElementException("无法匹配获取元素的方法！！！");
        }
    }

    /**
     * 操作元素
     * @param driver
     * @param param
     * @param  element
     * @return
     */
     public Object operate(WebDriver driver, WebElement webElement, String param, AutoElement element) throws Exception {
        String operateType = element.getEleeventname();
        switch(operateType) {
            case "click" :
                webElement.click();
                return null;
            case "sendKeys":
                webElement.sendKeys(param);
                return null;
            case "getText":
                return webElement.getText();
            case "isSelected":
                webElement.isSelected();
                return null;
            case "textClear":
                webElement.clear();
                return null;
            case "getAttribute":
                return webElement.getAttribute(param);
            case "getTagName":
                return webElement.getTagName();
            case "isDisplayed":
                webElement.isDisplayed();
                return null;
            case "isEnabled":
                webElement.isEnabled();
                return null;
            case "onMouseOver":
                Actions action = new Actions(driver);
                action.moveToElement(webElement).perform();
                Thread.sleep(3000);
                return driver;
            default:
                throw new Exception("没有匹配到元素操作的方法！！！");
        }
     }

    /**
     * 执行元素操作事件
     * @param driver
     * @param element
     * @param param
     * @param executeCaseLog
     * @return
     * @throws Exception
     */
    public WebDriver operateElement(WebDriver driver, AutoElement element, String param, ExecuteCaseLog executeCaseLog) throws Exception {
        WebElement elementObj = null;
        try {
            logger.debug(element.getElename() + " local start");
            executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + element.getElename() + " local start" + "</br>");
            elementObj = this.getElement(driver, element.getElelocaltype(), element.getEleaddress());
            logger.debug(element.getElename() + " begin execute " + element.getEleeventname() + " event");
            executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + element.getElename() + " begin execute " + element.getEleeventname() + " event" + "</br>");
            Object res = this.operate(driver, elementObj, param, element);

            if (res instanceof WebDriver) {
                driver = (WebDriver) res;
            }
            //  若参数不等于template 且 element操作返回值不为null ---> 走校验
            if (res != null && !param.equals("template")) {
                this.assertLogicMethod(element, param, executeCaseLog, res);
            }
            logger.debug(element.getElename() + " execute success");
            executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + element.getElename() + " execute success" + "</br></br>");
        } catch (Exception e){
            executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() + "<div style='color: red;'> ERROR: " + e.getMessage() + "</div>");
            throw new Exception(e.getMessage());
        }
        return driver;
    }

    /**
     * 断言逻辑
     * @param element
     * @param param
     * @param executeCaseLog
     * @param res
     */
    private void assertLogicMethod(AutoElement element, String param, ExecuteCaseLog executeCaseLog, Object res) {
        //  进行断言操作，
        Map<String, Object> map = new SeleAssert().verifyEquals(res, param, "");
        Object obj = null;
        String assertResult = "";
        // 接收断言信息，判断断言结果
        for(String key : map.keySet()){
            assertResult = key;
            obj = map.get(key);
        }
        if (assertResult == "SUCCESS") {
            logger.debug(element.getElename() + " Assert Result " + obj);
            executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() +
                    " <span style='color: blue;'> " + element.getElename() + " ASSERT RESULT: SUCCESS, ASSERT DESCRIPTION: " + obj + "</span>" + "</br>");
        }else if (assertResult == "ERROR") {
            logger.debug(element.getElename() + " Assert Result " + obj);
            executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() +
                    "<span style='color: red;'>" + element.getElename() + " ASSERT RESULT: ERROR, ASSERT DESCRIPTION: " + obj + "</span>" + "</br> ");
        }else {
            logger.debug(element.getElename() + " Assert Result " + obj);
            executeCaseLog.getExecuteCaseLogList().add(this.getCurrentTime() +
                    "<span style='color: red;'>" + element.getElename() + " ASSERT RESULT: FAIL, ASSERT DESCRIPTION: " + obj + "</span>" + "</br> ");
        }
    }

    /**
     * 获取匹配到的浏览器Driver
     * @param model
     * @param browserType
     * @return
     */
    public WebDriver getDriver(Integer model, String browserType) {
        switch (browserType) {
            case "CHROME":
                return this.getChromeDriver(model);
            case "FIREFOX":
                return this.getFireFox(model);
        }
        return null;
    }

    /**
     * 获取FireFoxDriver
     * @param model
     * @return
     */
    private WebDriver getFireFox(Integer model) {
        return null;
    }

    /**
     * 获取ChromeDriver
     * @param model
     * @return
     */
    private WebDriver getChromeDriver(Integer model) {
        WebDriver driver = null;
        //String path = "D://server//plateform//src//main//resources//browser//chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver", path);
        //System.out.println(path);
        ChromeOptions chromeOptions = new ChromeOptions();
        if (model == 0) {
            //设置浏览器窗口打开大小
            chromeOptions.addArguments("--window-size=2000,2000");
            chromeOptions.addArguments("--no-sandbox");
            driver = new ChromeDriver(chromeOptions);
            System.out.println(driver);
            //driver.manage().window().maximize();
        }else if (model == 1){
            //设置为 headless 模式 （必须）
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("blink-settings=imagesEnabled=false");//禁用图片
            driver = new ChromeDriver(chromeOptions);
            System.out.println(driver);
        }
        return driver;
    }

    private String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return "[" + dateFormat.format(date) + "]";
    }

}
