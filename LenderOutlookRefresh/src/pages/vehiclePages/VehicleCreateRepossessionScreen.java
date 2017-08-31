package pages.vehiclePages;

import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehicleCreateRepossessionScreen 
{

	WebDriver driver;
	WebDriverWait waiter;
	String ScreenTitle;
	//By CreateRepossessionScreenTitleXpath=By.xpath("/html/body/div[4]/descendant::h3");
	By CreateRepossessionScreenTitleXpath=By.xpath("/html/body/descendant::h3");
	//By RepoAgentNameXpath=By.xpath("/html/body/div[3]/descendant::h3/following-sibling::div/descendant::input[@id='repoAgentName']");
	By RepoAgentNameXpath=By.xpath("/html/body/descendant::h3/following-sibling::div/descendant::input[@id='repoAgentName']");
	//By RepoAgentMobileXpath=By.xpath("/html/body/div[3]/descendant::h3/following-sibling::div/descendant::input[@id='repoAgentMobile']");
	By RepoAgentMobileXpath=By.xpath("/html/body/descendant::h3/following-sibling::div/descendant::input[@id='repoAgentMobile']");
	//By RepoAgentEmailXpath=By.xpath("/html/body/div[3]/descendant::h3/following-sibling::div/descendant::input[@id='repoAgentEmail']");
	By RepoAgentEmailXpath=By.xpath("/html/body/descendant::h3/following-sibling::div/descendant::input[@id='repoAgentEmail']");
	//By RepossessionNotesXpath=By.xpath("/html/body/div[3]/descendant::h3/following-sibling::div/descendant::textarea[@id='repoAgentNotes']");
	By RepossessionNotesXpath=By.xpath("/html/body/descendant::h3/following-sibling::div/descendant::textarea[@id='repoAgentNotes']");
	//By AddButtonXpath=By.xpath("/html/body/div[3]/descendant::button/descendant::span[text()='ADD']");
	By AddButtonXpath=By.xpath("/html/body/descendant::button/descendant::span[text()='ADD']");
	By LicesePlateVINErrorTextXpath=By.xpath("//*[@id='app']/descendant::span[contains(text(),'VIN and License Plate cannot be null or empty string. in application/controllers/device.php line 5460')]");
	By  RepoSuccessMsgXpath=By.xpath("//*[@id='app']/descendant::span[contains(text(),'Repossession created successfully')]");
	By RepoResultbottomMessageXpath = By.xpath("//*[@id='app']/descendant::span");
	public VehicleCreateRepossessionScreen(WebDriver driver2,WebDriverWait waiter2)
	{

		this.driver = driver2;
		this.waiter = waiter2;
		
	}
	
	public String getScreenTitle()
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(CreateRepossessionScreenTitleXpath));
		ScreenTitle=driver.findElement(CreateRepossessionScreenTitleXpath).getText();
		if (ScreenTitle!=null)
		{
			return ScreenTitle;
		}
		
		else
		{
			ScreenTitle = "FAILED";
			return ScreenTitle;
		}	
	}
	
	public void setRepoAgentName(String RepoAgentName)
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(RepoAgentNameXpath));
		driver.findElement(RepoAgentNameXpath).sendKeys(RepoAgentName);
		
	}
	
	public void setRepoAgentMobile(String RepoAgentMobile)
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(RepoAgentMobileXpath));
		driver.findElement(RepoAgentMobileXpath).sendKeys(RepoAgentMobile);
		
	}
	
	public void setRepoAgentEmail(String RepoAgentEmail)
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(RepoAgentEmailXpath));
		driver.findElement(RepoAgentEmailXpath).sendKeys(RepoAgentEmail);
		
	}
	
	public void setRepossessionNotes(String RepossessionNotes)
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(RepossessionNotesXpath));
		driver.findElement(RepossessionNotesXpath).sendKeys(RepossessionNotes);
		
	}
	
	public void clickAddRepossessionButton()
	{
		waiter.until(ExpectedConditions.visibilityOfElementLocated(AddButtonXpath));
		driver.findElement(AddButtonXpath).click();
		
	}
	
	public void createRepossession(String RepoName,String RepoMobile,String RepoEmail,String RepoNotes)
	{
		String LicesePlateVINErrorText;
		this.setRepoAgentName(RepoName);
		this.setRepoAgentMobile(RepoMobile);
		this.setRepoAgentEmail(RepoEmail);
		this.setRepossessionNotes(RepoNotes);
		this.clickAddRepossessionButton();
		
		waiter.until(ExpectedConditions.visibilityOfElementLocated(LicesePlateVINErrorTextXpath));
		LicesePlateVINErrorText = driver.findElement(LicesePlateVINErrorTextXpath).getText();
		System.out.println("LicesePlateVINErrorText= " +LicesePlateVINErrorText);
	}
	
	@SuppressWarnings("deprecation")
	public boolean RCreateRepossession(LinkedHashMap<String, String> CreateRepoDataMap)
	{
		String LicesePlateVINErrorText;
		String RepoResultText;
		String RepoName,RepoMobile,RepoEmail,RepoNotes;
		RepoName=CreateRepoDataMap.get("RepoName");
		RepoMobile=CreateRepoDataMap.get("RepoMobile");
		RepoEmail=CreateRepoDataMap.get("RepoEmail");
		RepoNotes=CreateRepoDataMap.get("RepoNotes");
		this.setRepoAgentName(RepoName);
		this.setRepoAgentMobile(RepoMobile);
		this.setRepoAgentEmail(RepoEmail);
		this.setRepossessionNotes(RepoNotes);
		this.clickAddRepossessionButton();
		boolean CreateRepoPass=false;
		try
		{
			//waiter.until(ExpectedConditions.visibilityOfElementLocated(RepoSuccessMsgXpath));
			//CreateRepoPass= true;
			if(waiter.until(ExpectedConditions.textToBePresentInElementLocated(RepoSuccessMsgXpath,"Repossession created successfully")))
			{
				CreateRepoPass= true;
				
			}
			
			else 
			{
				RepoResultText = driver.findElement(RepoResultbottomMessageXpath).getText();
				System.out.println("RepoResultbottomMessageXpath= " +RepoResultbottomMessageXpath);
				CreateRepoPass= false;
			}
			
			
		}
		catch (NoSuchElementException e) 
    	{
			//waiter.until(ExpectedConditions.visibilityOfElementLocated(LicesePlateVINErrorTextXpath));
			//LicesePlateVINErrorText = driver.findElement(LicesePlateVINErrorTextXpath).getText();
			//System.out.println("LicesePlateVINErrorText= " +LicesePlateVINErrorText);
			CreateRepoPass= false;
        }
		
		System.out.println("CreateRepoPass= " +CreateRepoPass);
		
		return CreateRepoPass;
		
	}
	
	
}