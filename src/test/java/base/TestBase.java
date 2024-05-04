package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import static common.Browsers.captureScreenShot;
import static common.Browsers.closeBrowser;

public class TestBase {
    @AfterMethod(alwaysRun = true)
    protected void tearDown(ITestResult testResult) {
        String tcName = testResult.getMethod().getMethodName();
        if (!testResult.isSuccess()) {
            captureScreenShot(tcName);
        }
    }
    @AfterClass
    protected void tearDown(){
        closeBrowser();
    }
}
