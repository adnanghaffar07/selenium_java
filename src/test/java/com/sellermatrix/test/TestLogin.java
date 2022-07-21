package com.sellermatrix.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jaxen.expr.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sellermatrix.base.BaseClass;
import com.sellermatrix.base.PropertiesReader;
import com.sellermatrix.errorCollectors.ErrorCollector;
import com.sellermatrix.pages.LoginPage;

import net.bytebuddy.asm.Advice.OnDefaultValue;

public class TestLogin extends BaseClass{
	LoginPage lp;

	

@Test 
	public void Login(){

		    initConfiguration();
			lp = new LoginPage();
			ErrorCollector.extentLogInfo("Step 1 : Visit web url");
			openURL("AppURL");
			
			ErrorCollector.extentLogInfo("Step 2 : Click on login button");
			lp.clickOnLoginButton();
			
			String email = PropertiesReader.getPropertyValue(env + "_EmailId");
			ErrorCollector.extentLogInfo("Step 3 : Enter Email : "+email);
			lp.enterEmail(email);

			String pass = PropertiesReader.getPropertyValue(env + "_Password");
			ErrorCollector.extentLogInfo("Step 4 : Enter Password : "+pass);
			lp.enterPassword(pass);
			
			ErrorCollector.extentLogInfo("Step 5 : Click on Login button");
			lp.clickOnLoginButton();
			
			ErrorCollector.extentLogInfo("Step 6 : Verify User Dashboard is displaying");
			waitTime(3000);
			ErrorCollector.verifyTrue(lp.isUserDashboardDisplaying(),"Verified User Dashboard is displaying");
			
			
	}


@Test
public void getData() {
	WebDriver driver = null;
	ArrayList<String> testSteps = new ArrayList<>();
	LoginPage lp;

	driver = initConfiguration();
	//initConfiguration();
	//lp = new LoginPage(driver);
	//Object[][] dataArr = getData("testData", "TestData", driver);
	
	waitTime(3000);
	driver.get("https://booking-devon-mobile-mechanic.zippity.cc/pricing/vehicle-lookup");
	
	
	waitTime(5000);
	
	driver.findElement(By.xpath("//*[contains(text(),'Year Make Model')]")).click();
	waitTime(3000);
	
	driver.findElement(By.xpath("(//input)[1]")).click();
	
	
	ArrayList<String> yearStrings = new ArrayList<>();
	yearStrings.add("2004");
	//yearStrings.add("2005");
	int yearID = 20;
	int makeID=5169;
	int modelID=6111;
	int trimID=7979;
	int engineID=32793;
	JSONArray makeMap=new JSONArray();
	JSONArray modelMap = new JSONArray();
	JSONArray trimMap = new JSONArray();
	JSONArray engineMap = new JSONArray();
	for(int i=0;i<yearStrings.size();i++) {
		if(i==1)
			yearID=18;
		if (i==2) {
			yearID = 18;
		}
		WebElement year = driver.findElement(By.xpath("((//div[@class='input-field col'])[1]//*[contains(text(),'"+yearStrings.get(i)+"')])[1]"));
		scrollIntoViewSmoothly(year);
		waitTime(3000);
		click(year);
		waitTime(5000);
		ArrayList<String> makeStrings = new ArrayList<>();
		driver.findElement(By.xpath("(//input)[2]")).click();
		List<WebElement> makes = driver.findElements(By.xpath("(//div[@class='input-field col'])[2]//li[contains(@id,'select-options-')]"));
		for(int j=2;j<=makes.size();j++) {
			WebElement makeElement = driver.findElement(By.xpath("((//div[@class='input-field col'])[2]//li[contains(@id,'select-options-')])["+j+"]"));
			
			scrollIntoViewSmoothly(makeElement);
			waitTime(3000);
			String makeString = getElementText(makeElement);
			makeStrings.add(makeString);
			printString("Make: "+makeString);
			click(makeElement);
			
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", makeID);
			jsonObject.put("label", makeString);
			jsonObject.put("ParentId", yearID);
			
			makeMap.put(jsonObject);
			
			
			waitTime(5000);
			driver.findElement(By.xpath("(//input)[3]")).click();
			List<WebElement> models = driver.findElements(By.xpath("(//div[@class='input-field col'])[3]//li[contains(@id,'select-options-')]"));
			ArrayList<String> modelStrings = new ArrayList<>();
			for(int k=2;k<=models.size();k++) {
				WebElement modElement = driver.findElement(By.xpath("((//div[@class='input-field col'])[3]//li[contains(@id,'select-options-')])["+k+"]"));
				scrollIntoViewSmoothly(modElement);
				waitTime(3000);
				String modelString = getElementText(modElement);
				modelStrings.add(modelString);
				printString("Model: "+modelString);
				click(modElement);
				
				JSONObject modeljsonObject=new JSONObject();
				modeljsonObject.put("id", modelID);
				modeljsonObject.put("label", modelString);
				modeljsonObject.put("ParentId", makeID);
				
				modelMap.put(modeljsonObject);
				
				waitTime(5000);
				driver.findElement(By.xpath("(//input)[4]")).click();
				waitTime(1000);
				List<WebElement> trims = driver.findElements(By.xpath("(//div[@class='input-field col'])[4]//li[contains(@id,'select-options-')]"));
				ArrayList<String> trimStrings = new ArrayList<>();
				for(int l=2;l<=trims.size();l++) {
					WebElement trimElement = driver.findElement(By.xpath("((//div[@class='input-field col'])[4]//li[contains(@id,'select-options-')])["+l+"]"));
					scrollIntoViewSmoothly(trimElement);
					waitTime(3000);
					String trimString = getElementText(trimElement);
					trimStrings.add(trimString);
					printString("Trim: "+trimString);
					click(trimElement);
					waitTime(5000);
					
					
					JSONObject trimjsonObject=new JSONObject();
					trimjsonObject.put("id", trimID);
					trimjsonObject.put("label", trimString);
					trimjsonObject.put("ParentId", modelID);
					
					trimMap.put(trimjsonObject);
					
					if(driver.findElements(By.xpath("(//*[@id='Engine']//..)[1]")).size()>0) {
						printString("Engine Available");
						driver.findElement(By.xpath("(//input)[5]")).click();
						List<WebElement> engines = driver.findElements(By.xpath("(//div[@class='input-field col'])[5]//li[contains(@id,'select-options-')]"));
						ArrayList<String> enginesStrings = new ArrayList<>();
						for(int m=2;m<=engines.size();m++) {
							try {
								WebElement engineElement = driver.findElement(By.xpath("((//div[@class='input-field col'])[5]//li[contains(@id,'select-options-')])["+m+"]"));
								scrollIntoViewSmoothly(engineElement);
								waitTime(3000);
								String engineString = getElementText(engineElement);
								enginesStrings.add(engineString);
								printString("Engine: "+engineString);
								click(engineElement);
								waitTime(2000);
								
								//Write Data to File Here
								JSONObject enginejsonObject=new JSONObject();
								enginejsonObject.put("id", engineID);
								enginejsonObject.put("label", engineString);
								enginejsonObject.put("ParentId", trimID);
								
								engineMap.put(enginejsonObject);
							}catch(Exception e) {}
							
							
							click(driver.findElement(By.xpath("(//input)[5]")));
							engineID++;
						}
					}else {
						printString("Engine Not Available");
						//Write Data to File Here
						
					}
					
					driver.findElement(By.xpath("(//input)[4]")).click();
					trimID++;
				}
				
				driver.findElement(By.xpath("(//input)[3]")).click();
				modelID++;
			}
			driver.findElement(By.xpath("(//input)[2]")).click();
			makeID++;
		}
		
		driver.findElement(By.xpath("(//input)[1]")).click();
	}
	
	waitTime(5000);
	
	JSONObject finalJsonObject=new JSONObject();
	finalJsonObject.put("yearMapList", makeMap);
	finalJsonObject.put("makeMap",modelMap);
	finalJsonObject.put("modelMap", trimMap);
	finalJsonObject.put("trimMap", engineMap);
	
    try {  

    	// Writing to a file  
        File file=new File("JsonFile.json");  
        file.createNewFile();  
        FileWriter fileWriter = new FileWriter(file);  
        System.out.println("Writing JSON object to file");  
        System.out.println("-----------------------");  
        System.out.print(finalJsonObject);  

        fileWriter.write(finalJsonObject.toString());  
        fileWriter.flush();  
        fileWriter.close();

    } catch (IOException e) {  
        e.printStackTrace();  
    } 
}

public static void main(String [] args) {
//	JSONObject finalJsonObject=new JSONObject();
//	finalJsonObject.put("yearMapList", "Makes Data");
//	finalJsonObject.put("makeMap","Models Data");
//	finalJsonObject.put("modelMap", "Trim Data");
//	finalJsonObject.put("trimMap", "Engines Data");
//	ObjectMapper mapper = new ObjectMapper();
//	
//	Country countryObj = new Country();  
//    countryObj.name = "India";
//    countryObj.population = 1000000;
//
//    List<String> listOfStates = new ArrayList<String>();  
//    listOfStates.add("Madhya Pradesh");  
//    listOfStates.add("Maharastra");  
//    listOfStates.add("Rajasthan"); 
//    countryObj.states = listOfStates ;
//
//    try {  
//
//        // Writing to a file   
//        mapper.writeValue(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\data\\data.json"), countryObj );
//
//    } catch (IOException e) {  
//        e.printStackTrace();  
//    } 
	
	try {  
		
		//getData();
		
//		JSONObject countryObj = new JSONObject();  
//        countryObj.put("Name", "India");  
//        countryObj.put("Population", new Integer(1000000));  
//
//        JSONArray listOfStates = new JSONArray();  
//        listOfStates.put("Madhya Pradesh");  
//        listOfStates.put("Maharastra");  
//        listOfStates.put("Rajasthan");  
//
//        countryObj.put("States", listOfStates);
//
//        // Writing to a file  
//        File file=new File("JsonFile.json");  
//        file.createNewFile();  
//        FileWriter fileWriter = new FileWriter(file);  
//        System.out.println("Writing JSON object to file");  
//        System.out.println("-----------------------");  
//        System.out.print(countryObj);  
//
//        fileWriter.write(countryObj.toString());  
//        fileWriter.flush();  
//        fileWriter.close();  

    } catch (Exception e) {  
        e.printStackTrace();  
    }  
}
}
