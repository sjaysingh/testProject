package main.java.com.stryker.NV.scripts.module1;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.relevantcodes.extentreports.LogStatus;
import com.stryker.star.CommonLibrary;

import main.java.com.stryker.NV.library.SfdcLibrary;

public class GXICM_E2E_Search_Existing_Account_Off_Level_Risk_Adjustment_138962 {

	private CommonLibrary commonLib;
	private SfdcLibrary sfdcLib;
	Date date= new Date();
	long ts = date.getTime();
	Timestamp timeStamp = new Timestamp(date.getTime());
	String Subject_Name = "Automation Task " +timeStamp.toString();
	
	public GXICM_E2E_Search_Existing_Account_Off_Level_Risk_Adjustment_138962(CommonLibrary commonLib)
	{
		this.commonLib=commonLib;
		this.sfdcLib = new SfdcLibrary(commonLib);
		System.out.println("test");
	}
	
	public void Test_Script_GXICM_OffLevel_RiskAdjustment() throws InterruptedException, IOException
	{
		
		try{      

			String worksheet = commonLib.getConfigValue("SfdcDataSheet");

			int rowcount=sfdcLib.getRowCount(worksheet, "Off_Level_Risk_138962");
			System.out.println(rowcount);
			List<String> UserData1 = new ArrayList<String>();		
			UserData1 = sfdcLib.read_Test_Data(worksheet, "Users", 7, 7, 2, 3);
			
			
			sfdcLib.login(UserData1.get(0), UserData1.get(1));//Calling Login method to login into the Application.
			commonLib.waitForDynamicTitle("Salesforce", 120);
			commonLib.waitForPageToLoad();
			commonLib.waitForPresenceOfElementLocated("GlobalSearch_Homepage_XPATH");
			Thread.sleep(5*1000);			
			commonLib.waitForPresenceOfElementLocated("User_settings_Button_XPATH");
			//Calling method to click on Setup button from Homepage after login.
			String currentHandle=sfdcLib.click_Setup_FromHomePage_AfterLogin();
			commonLib.syso("************** Running test data for Iteration");
			commonLib.setTest("Search_Existing_Account_Off_Level_Risk_Adjustment_138962");
			
			//Getting All test Data in List
			List<String> Run_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount, 0, 0);
			List<String> ICRegional_data=    sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount, 1, 1);
			List<String> existingAccountName_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount, 2, 2);
			List<String> Record_Type_value_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,3,3);
			List<String> issueCategory_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,4,4);
			List<String> issueSubCategory_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,5,5);
			List<String> subject_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,6,6);
			List<String> description_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,7,7);
			List<String> action_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,8,8);
			List<String> dueDate_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,9,9);
			List<String> closeNotes_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,10,10);
			List<String> priority_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount,11,11);
			List<String> ICGlobalUser_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount, 12, 12);
			List<String> ActiveRiskRating_userData=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount, 13, 12);
			
			for(int i=1;i<=rowcount;i++)
			{
				//Getting Data from the excel sheet
				String Run_Staus = Run_data.get(i-1);
				String ICRegionalUser=ICRegional_data.get(i-1);
				String existingAccountName=existingAccountName_data.get(i-1);  //This thing we need to change as will be coming from Pre intake
				String Record_Type=Record_Type_value_data.get(i-1);
				String priority=priority_data.get(i-1);
				String issueCategory=issueCategory_data.get(i-1);
				String issueSubCategory=issueSubCategory_data.get(i-1);
				String subject=subject_data.get(i-1);
				String description=description_data.get(i-1);
				String action=action_data.get(i-1);
				String dueDate=dueDate_data.get(i-1);
				String closeNotes=closeNotes_data.get(i-1);
				String ICGlobalUser=ICGlobalUser_data.get(i-1);
				String ActiveRiskRating=ActiveRiskRating_userData.get(i-1);
				
				//Execution starts
				if(Run_Staus.equalsIgnoreCase("Yes"))
				{
					commonLib.log(LogStatus.INFO,"***** Iteration: "+i + "(Starts) *****");
					commonLib.syso("***** Iteration: "+i + "(Starts)*****");
					commonLib.log(LogStatus.INFO, "*** Test Case 138964 :Verify that ICM Regional is able to Search and existing IC and create a support case");
					sfdcLib.search_Profile_GXICM(ICRegionalUser);
					commonLib.log(LogStatus.PASS, "Step 1:Login to SFDC GX as IC Regional");
					sfdcLib.Navigate_To_Existing_Account(existingAccountName);
					commonLib.log(LogStatus.PASS, "Step 2:Opened exiting account with name: " + existingAccountName);
					commonLib.syso("Opened the given exisitng Account i.e.: " );
					sfdcLib.Open_Given_Link_Through_RelatedListQuickLinks_In_Accounts_Page("Support Cases");
					String supportCaseNumber=sfdcLib.Create_New_Support_Case_From_RelatedQuickLinks(Record_Type, priority, issueCategory, issueSubCategory, subject, description, action, dueDate, closeNotes);
					commonLib.log(LogStatus.PASS, "Step 3:Created Support case with ID: " + supportCaseNumber);
					
					
					//Code for Upload file should come here
					commonLib.log(LogStatus.PASS, "Step 4:Uploaded documents successfully");					
					
					sfdcLib.logout();
					commonLib.syso("Logged out from " + ICRegionalUser + " profile");
					commonLib.log(LogStatus.PASS, "Step 5:Successfully Logged out from the " + ICRegionalUser + " profile");
					
					commonLib.log(LogStatus.PASS, "*** Test Case 138964 : PASSED ***");
					
					commonLib.log(LogStatus.INFO, "*** Test Case 138965 : Verify that ICM Global is able to change the Active Risk Rating for support case created by ICM Regional");
					commonLib.syso("Switch User profile to " + ICGlobalUser );
					//Login with IC Global User
					sfdcLib.search_Profile_GXICM(ICGlobalUser);
					commonLib.log(LogStatus.PASS, "Step 1:Login to SFDC GX as IC Global");
					

					sfdcLib.navigate_To_Existing_SupportCase_From_Global_Search(supportCaseNumber);
					commonLib.log(LogStatus.PASS, "Step 2:Search existing IC/Support Case created in previous test case");
					
					commonLib.clickWithDynamicValue("SupportCaseDetailPage_AccountLink_XPATH", existingAccountName);
					boolean accountName_Exist= commonLib.waitForVisibilityOf_DynamicXpath("AccountName_Text_Header_In_Accounts_Page_XPATH", existingAccountName);

					if(accountName_Exist)
					{
						commonLib.log(LogStatus.PASS, "Step 3:Navigate to account detail page");	
					}
					else
					{
						commonLib.log(LogStatus.FAIL, "Step3: Account detail page is not opened from support case page");	
					}
					commonLib.click("AccountDetail_EditActiveRiskRating_XPATH");
					commonLib.waitforInvisibilityOfElement("AccountDetail_EditActiveRiskRating_XPATH");
					commonLib.click("AccountDetail_RiskLevelRatingDropdown_XPATH");
					Thread.sleep(2000);
					commonLib.clickWithDynamicValue("AccountDetail_RiskLevelRatingDropdown__L5_XPATH", ActiveRiskRating);
					


				
					
					
					
					
				}
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
}
