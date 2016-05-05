package PayTollNotice_Package;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;



import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;


public class PayTollNotice_Class extends PerfDB {
	public WebDriver driver;
	String ActTxtCheck1;
	String ActTxtCheck2;
	String ActTxtCheck3;
	String ActTxtCheck4;
	String ActTxtCheck5;
	String ActTxtCheck6;
	String ExpTxtCheck1 = "Customer Charter";
	String ExpTxtCheck2 = "Do it online";
	String ExpTxtCheck3 = "Practice driver knowledge test";
	String ExpTxtCheck4 = "Received a Toll Notice?";
	String ExpTxtCheck5 = "Validate toll notice";
	String ExpTxtCheck6 = "The toll road or toll notice information you have entered does not correspond to the toll notice number. Please check your toll notice and re-enter.";
	
	PerfDB myDB = new PerfDB();
	
	
	public StopWatch LaunchURL = new StopWatch();
	public StopWatch PayMytoll = new StopWatch();
	public StopWatch PayNotice = new StopWatch();
	public StopWatch ValidateMsg = new StopWatch();
	
  @Test
  public void PayTollNotice_Function() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	
	  System.out.println("Todays First commit");
	  
	  myDB.connectDB("jdbc:mysql://atnsw-bench006:3306/monitoringdb", "perfmon", "123");
	  myDB.initializeScriptExec("Pay Toll Notice", "Roads and Maritime Services");
	  
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	  LaunchURL.start();
	  driver.get("http://www.rms.nsw.gov.au/index.html");
	  ActTxtCheck1=driver.findElement(By.linkText("Customer Charter")).getText();
	  Assert.assertEquals(ActTxtCheck1, ExpTxtCheck1);
	  LaunchURL.stop();
	 // driver.manage().window().maximize();
	  
	
	
	//LaunchURL.stop();
	double res_time_sec1 = (double) LaunchURL.getTime();
	res_time_sec1 = res_time_sec1/1000;
	
	if (driver.findElement(By.linkText("Customer Charter")).getText().equals(ExpTxtCheck1)){
		System.out.println("Success RMS Home Page");
	   myDB.recordStepResponseTime("Roads and Maritime Services", "Pay Toll Notice", "RMS Home Page2", "RMS Home Page2", "Pass", "Successfully executed", res_time_sec1);
	}
	else
		{
		System.out.println("Failure RMS Home Page");
		myDB.recordStepExecFailure ("Roads and Maritime Services", "Pay Toll Notice", "RMS Home Page2", "RMS Home Page2", "Fail", "Page not found");
		myDB.updateScriptExecutionFailure("Roads and Maritime Services", "Pay Toll Notice", "RMS Home Page2");
		myDB.closeConnections();
		driver.close();
		}
		
	ActTxtCheck2=driver.findElement(By.xpath(".//*[@id='content']/div/div[3]/div[1]/h2/span")).getText();
	Assert.assertEquals(ActTxtCheck2, ExpTxtCheck2);
	ActTxtCheck3=driver.findElement(By.xpath(".//*[@id='content']/div/div[3]/div[1]/div/div/div/div[1]/ul/li[3]/a")).getText();
	Assert.assertEquals(ActTxtCheck3, ExpTxtCheck3);
	
	//Click Pay my toll
	PayMytoll.start();
	driver.findElement(By.xpath(".//*[@id='content']/div/div[3]/div[1]/div/div/div/div[2]/ul/li[3]/a")).click();
	//Thread.sleep(200);
	//ActTxtCheck4=driver.findElement(By.xpath(".//*[@id='home_right_contianer']/div[3]/h4")).getText();
	//Assert.assertEquals(ActTxtCheck4, ExpTxtCheck4);
	PayMytoll.stop();
	double res_time_sec2 = (double) PayMytoll.getTime();
	res_time_sec2 = res_time_sec2/1000;
	
	
	
	if (driver.findElement(By.xpath(".//*[@id='home_right_contianer']/div[3]/h4")).getText().equals(ExpTxtCheck4)) 
		myDB.recordStepResponseTime("Roads and Maritime Services", "Pay Toll Notice", "Pay My Toll", "Pay My Toll", "Pass", "Successfully executed", res_time_sec2);
	else
	{
		
		System.out.println("Failure recording");
		myDB.recordStepExecFailure("Roads and Maritime Services", "Pay Toll Notice", "Pay My Toll", "Pay My Toll", "Fail", "Pay My Toll Unsuccessful");
		myDB.updateScriptExecutionFailure("Roads and Maritime Services", "Pay Toll Notice", "Pay My Toll");
		myDB.closeConnections();
		driver.close();
	}	
		
	//Click Pay Toll Notice
	PayNotice.start();
	driver.findElement(By.xpath(".//*[@id='home_right_contianer']/div[3]/p/a")).click();
	//Thread.sleep(1000);
	
	//New Window
	 
	
	String pid, cid;
	Set<String> ids=driver.getWindowHandles();
	Iterator<String> it=ids.iterator();
	pid=it.next();
	cid=it.next();
	driver.switchTo().window(cid);
	
	ActTxtCheck5=driver.findElement(By.xpath("html/body/table/tbody/tr[3]/td[2]/table/tbody/tr/td/form/table/tbody/tr/td/table/tbody/tr[9]/td/div/table/tbody/tr[1]/td/span")).getText();
	Assert.assertEquals(ActTxtCheck5, ExpTxtCheck5);
	PayNotice.stop();
	double res_time_sec3 = (double) PayNotice.getTime();
	res_time_sec3 = res_time_sec3/1000;
	
	if(driver.findElement(By.xpath("html/body/table/tbody/tr[3]/td[2]/table/tbody/tr/td/form/table/tbody/tr/td/table/tbody/tr[9]/td/div/table/tbody/tr[1]/td/span")).getText().equals(ExpTxtCheck5))
		myDB.recordStepResponseTime("Roads and Maritime Services", "Pay Toll Notice", "Pay Toll Notice", "Pay Toll Notice", "Pass", "Successfully executed", res_time_sec3);
	else
	{
		myDB.recordStepExecFailure ("Roads and Maritime Services", "Pay Toll Notice", "Pay Toll Notice", "Pay Toll Notice", "Fail", "Page not found");
		myDB.updateScriptExecutionFailure("Roads and Maritime Services", "Pay Toll Notice", "Pay Toll Notice");
		myDB.closeConnections();
	}
	
	Select TollSel = new Select(driver.findElement(By.id("tollRoad")));
	TollSel.selectByVisibleText("Sydney Harbour Bridge");
	driver.findElement(By.id("tollNoticeNumber")).sendKeys("12345678909");
	driver.findElement(By.id("numberPlate")).sendKeys("XYZ321");
	
	//Click Next
	ValidateMsg.start();
	driver.findElement(By.xpath(".//*[@id='nextButton']")).click();
	
	ActTxtCheck6=driver.findElement(By.xpath(".//*[@id='errorsCell']/font/b/span")).getText();
	Assert.assertEquals(ActTxtCheck6, ExpTxtCheck6);
	ValidateMsg.stop();
	double res_time_sec4 = (double) ValidateMsg.getTime();
	res_time_sec4 = res_time_sec4/1000;
	
	if(driver.findElement(By.xpath(".//*[@id='errorsCell']/font/b/span")).getText().equals(ExpTxtCheck6))
		myDB.recordStepResponseTime("Roads and Maritime Services", "Pay Toll Notice", "Toll Details Next", "Toll Details Next", "Pass", "Successfully executed", res_time_sec4);
	else
	{
		myDB.recordStepExecFailure ("Roads and Maritime Services", "Pay Toll Notice", "Toll Details Next", "Toll Details Next", "Fail", "Page not found");
		myDB.updateScriptExecutionFailure("Roads and Maritime Services", "Pay Toll Notice", "Toll Details Next");
		myDB.closeConnections();
	}
	
	myDB.updateScriptExecSuccess("Pay Toll Notice", "Roads and Maritime Services");
	myDB.closeConnections();
	
  }
    
  @AfterMethod
  public void afterMethod() {
  driver.quit();
  }

}
