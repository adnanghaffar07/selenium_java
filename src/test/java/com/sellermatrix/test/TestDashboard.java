package com.sellermatrix.test;

import org.jaxen.expr.Step;
import org.testng.annotations.Test;

import com.sellermatrix.base.BaseClass;
import com.sellermatrix.base.PropertiesReader;
import com.sellermatrix.errorCollectors.ErrorCollector;
import com.sellermatrix.pages.LoginPage;

import net.bytebuddy.asm.Advice.OnDefaultValue;

public class TestDashboard extends BaseClass{
	LoginPage lp;

	

@Test 
	public void Dashboard(){

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
			
			ErrorCollector.extentLogInfo("Step 7 : Verify 'Sales' is showing on User Dashboard");
			ErrorCollector.verifyTrue(lp.isDisplayingOnUserDashboard("Sales"),"Verified 'Sales' is showing on User Dashboard");
			
			ErrorCollector.extentLogInfo("Step 8 : Verify 'Net Profit' is showing on User Dashboard");
			ErrorCollector.verifyTrue(lp.isDisplayingOnUserDashboard("Net Profit"),"Verified 'Net Profit' is showing on User Dashboard");
			
			ErrorCollector.extentLogInfo("Step 9 : Verify 'Orders' is showing on User Dashboard");
			ErrorCollector.verifyTrue(lp.isDisplayingOnUserDashboard("Orders"),"Verified 'Orders' is showing on User Dashboard");
			
			ErrorCollector.extentLogInfo("Step 10 : Verify 'Units' is showing on User Dashboard");
			ErrorCollector.verifyTrue(lp.isDisplayingOnUserDashboard("Units"),"Verified 'Units' is showing on User Dashboard");
			
			ErrorCollector.extentLogInfo("Step 11 : Verify 'Ad Spend' is showing on User Dashboard");
			ErrorCollector.verifyTrue(lp.isDisplayingOnUserDashboard("Ad Spend"),"Verified 'Ad Spend' is showing on User Dashboard");
			
			ErrorCollector.extentLogInfo("Step 12 : Verify 'Refunds' is showing on User Dashboard");
			ErrorCollector.verifyTrue(lp.isDisplayingOnUserDashboard("Refunds"),"Verified 'Refunds' is showing on User Dashboard");
			
			
	}
	
}
