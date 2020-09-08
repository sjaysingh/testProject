package main.java.com.stryker.NV.scripts.module1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.relevantcodes.extentreports.LogStatus;
import com.stryker.star.CommonLibrary;

import main.java.com.stryker.NV.library.SfdcLibrary;

public class GXICM_Contact {
	
	private CommonLibrary commonLib;
	private SfdcLibrary sfdcLib;
	
	//Constructor to Define Common Library and SDFC Library
	public GXICM_Contact(CommonLibrary commonLib) {
		this.commonLib=commonLib;
		this.sfdcLib = new SfdcLibrary(commonLib);
	}	

public void TC01_GXICM_CreateNewContact()
{
	try {
		String worksheet = commonLib.getConfigValue("SfdcDataSheet");
		List<String> UserData1 = new ArrayList<String>();
		UserData1 = sfdcLib.read_Test_Data(worksheet, "Users", 7, 7, 2, 3);		
		List<String> ICLocalUser_data=	sfdcLib.read_Test_Data(worksheet, "CreateAccount", 1, 1, 6, 6);
		List<String> accountName_Data=	sfdcLib.read_Test_Data(worksheet, "CreateAccount", 1, 1, 1, 1);
		List<String> countryOfOperation_Data=	sfdcLib.read_Test_Data(worksheet, "CreateAccount", 1, 1, 2, 2);			 
		List<String> ServicesProvided_data=	sfdcLib.read_Test_Data(worksheet, "CreateAccount", 1, 1, 3, 3);
		
		String ICLocalUser	=ICLocalUser_data.get(0);
		String accountName	= accountName_Data.get(0)+commonLib.randomInteger(1, 9000);
		String countryOfOperation=countryOfOperation_Data.get(0);
		String ServicesProvided =ServicesProvided_data.get(0);
		commonLib.setTest("TC_01_GXICM_CreateNewContact");
		sfdcLib.login(UserData1.get(0), UserData1.get(1));//Calling Login method to login into the Application.
		commonLib.waitForDynamicTitle("Salesforce", 120);
		commonLib.waitForPageToLoad();
		commonLib.waitForPresenceOfElementLocated("GlobalSearch_Homepage_XPATH");
		Thread.sleep(5*1000);
		commonLib.waitForPresenceOfElementLocated("User_settings_Button_XPATH");     //Waiting Applied to let the loading completed after login
		commonLib.log(LogStatus.INFO, "Login with System Admin Successfully");
		commonLib.clickwithoutWait("User_settings_Button_XPATH");                    // Clicking on Setting button to login with Profile    
		commonLib.waitForElementTimeOut("Service_Setup_XPATH", 20);
		commonLib.clickwithoutWait("Service_Setup_XPATH");                           //Clicking on Settings button.
		String currentHandle=commonLib.getWindowHandle(); 		                     //Saving the Current Handle to switch to Parent Window.
		commonLib.switchWindowWithURL("Setup");   				                     //New method to switch window on the basis of Url.
		commonLib.waitForDynamicTitle("Salesforce", 120);
		commonLib.waitForPresenceOfElementLocated("GlobalSearch_Homepage_XPATH");
		commonLib.log(LogStatus.INFO, "Login with IC Global to Create Contact");
		sfdcLib.search_Profile("IC Local", "GlobalSearch_ProfileName_XPATH", "IC Local", "IC Local");		
		sfdcLib.open_Given_Tab_In_Home_Page("Accounts");	//Method to click on Accounts Tab from Homepage.
		sfdcLib.open_New_Account_In_Accounts_Page();		 //Method to Click on New Account from Accounts Page.
		Map<String, String> AccountInformation_Map = new HashMap<>();
		AccountInformation_Map =sfdcLib.Create_New_Account_In_Accounts_Page(accountName,countryOfOperation,ServicesProvided);//Generic Method to create Account.
		System.out.println("test");
		
		/*
			 * commonLib.waitForVisibilityOf("OpenRelatedContact_AccountPage_XPATH");
			 * commonLib.log(LogStatus.INFO, "Newly Created Account with Name" +
			 * accountName);
			 * commonLib.syso("AccountInformation_Map="+AccountInformation_Map);
			 * sfdcLib.Open_Related_Contact_FromAccount();
			 */System.out.println("test");
		
		
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
}

}
