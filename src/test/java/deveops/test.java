package deveops;

import static org.testng.Assert.fail;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




public class test {
	 private WebDriver driver;
	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();
	  
	  
	  public static String tableCell(WebDriver driver,int row)
	  {
	      String text = null;
	      int column=row;
	      //去掉表头
	      row=row+1;
	     
	      String xpath1="//div[@class='table-container']/table/tbody/tr["+row+"]/td["+column+"]";
	      WebElement table_name=driver.findElement(By.xpath(xpath1));
	      String xpath_btn1="//div[@class='table-container']/table/tbody/tr["+row+"]/td[8]/span[1]";
	      WebElement table_btn1=driver.findElement(By.xpath(xpath_btn1));
	      table_btn1.click();
	      text=table_name.getText();
	      return text;
	  }
	  
	  @Parameters({"UserName","Password"})
	  @BeforeClass(alwaysRun = true)
	  public void setUp(String UserName,String Password) throws Exception {
		System.setProperty("webdriver.chrome.driver","tool\\chromedriver.exe");
		driver = new ChromeDriver();
	   // driver = new FirefoxDriver();
		baseUrl = "http://dev.in.myspacex.cn/git/index.html";
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.get(baseUrl );
	    //登录
	    driver.findElement(By.id("txtUserName")).clear();
	    driver.findElement(By.id("txtUserName")).sendKeys(UserName);
	    driver.findElement(By.id("txtPassword")).clear();
	    driver.findElement(By.id("txtPassword")).sendKeys(Password);
	    driver.findElement(By.name("btnSubmit")).click();
	    System.out.println("登录成功啦~~~");
	    Thread.sleep(1500);  
	  }


	@Parameters({"num"})
	@Test
	  public void apartment_slow(String num) throws Exception {
		 int num_int=Integer.parseInt( num );
		 driver.switchTo().frame("mainframe");
		 Thread.sleep(1500); 
		 tableCell(driver,num_int);
		 Thread.sleep(1500);  
		   
	  }

	@AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
}
