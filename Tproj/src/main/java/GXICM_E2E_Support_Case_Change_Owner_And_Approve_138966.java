package main.java.com.stryker.NV.scripts.module1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.stryker.star.CommonLibrary;

import junit.framework.Assert;
import main.java.com.stryker.NV.library.SfdcLibrary;

public class GXICM_E2E_Support_Case_Change_Owner_And_Approve_138966 {
	private CommonLibrary commonLib;
	private SfdcLibrary sfdcLib;
	Date date= new Date();
	long ts = date.getTime();
	Timestamp timeStamp = new Timestamp(date.getTime());
	String Subject_Name = "Automation Task " +timeStamp.toString();

	public GXICM_E2E_Support_Case_Change_Owner_And_Approve_138966(CommonLibrary commonLib) {
		this.commonLib=commonLib;
		this.sfdcLib = new SfdcLibrary(commonLib);
	}

	public void Test_Script_GXICM_E2E_Support_Case_Change_Owner_And_Approve_138966() {


		try{      

			String worksheet = commonLib.getConfigValue("SfdcDataSheet");

			int rowcount=sfdcLib.getRowCount(worksheet, "CreateSupport");	
			commonLib.syso("rowcount="+rowcount);
			//Support Case Data 
			List<String> Record_Type_value_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,0,0);
			commonLib.syso("Record_Type_value_data="+Record_Type_value_data);
			
			List<String> priority_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,1,1);
			List<String> issueCategory_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,2,2);
			List<String> issueSubCategory_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,3,3);
			List<String> subject_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,4,4);
			List<String> description_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,5,5);
			List<String> action_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,6,6);
			List<String> dueDate_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,7,7);
			List<String> closeNotes_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,8,8);
			List<String> changeOwner_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,9,9);
			List<String> support_Status_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,10,10);
			List<String> support_OutlinerReviewStatus_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount,11,11);
			List<String> ICLocalUser_data=    sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount, 12, 12);
			List<String> ICGlobalUser_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount, 13, 13);
			
			List<String> Run_data=sfdcLib.read_Test_Data(worksheet, "CreateSupport", 1, rowcount, 14, 14);
			
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
			commonLib.setTest("Support_Case_Change_Owner_And_Approve_138966");
			
			commonLib.syso("************** Running test data for Iteration");
			for(int i=1;i<=rowcount;i++)
			{	
			
				//Support Test data
				String Record_Type=Record_Type_value_data.get(i-1);
				String priority=priority_data.get(i-1);
				String issueCategory=issueCategory_data.get(i-1);
				String issueSubCategory=issueSubCategory_data.get(i-1);
				String subject=subject_data.get(i-1);
				String description=description_data.get(i-1);
				String action=action_data.get(i-1);
				String dueDate=dueDate_data.get(i-1);
				String closeNotes=closeNotes_data.get(i-1);
				String changeOwner=changeOwner_data.get(i-1);
				String support_status=support_Status_data.get(i-1);
				String support_OutlinerReviewStatus=support_OutlinerReviewStatus_data.get(i-1);

				//				//Profile to operated Test data
				String ICLocalUser    =ICLocalUser_data.get(i-1);
				String ICGlobalUser=ICGlobalUser_data.get(i-1);
				String Run_Staus = Run_data.get(i-1);
				
				if(Run_Staus.equals("Yes"))
				{
					//Starting Scenario Execution
					commonLib.log(LogStatus.PASS,"***** Iteration -"+i + "(Starts) *****");
					commonLib.syso("***** Iteration -"+i + "(Starts)*****");
					
					commonLib.log(LogStatus.PASS, "*** Test Case 138968 :Verify that ICM Local is able to create a Support Case and change the owner");
					//Login with IC Local User
					sfdcLib.search_Profile_GXICM(ICLocalUser);					
					commonLib.log(LogStatus.PASS, "Step 1:Login to SFDC GX as ICM Local");	
					
					//Creating Support Case from IC Local owner
					String supportCaseNumber=sfdcLib.Create_New_Support_Case_From_SupportCases_Page(Record_Type,priority,issueCategory,issueSubCategory,subject, description,action,dueDate,closeNotes);
					commonLib.log(LogStatus.PASS, "Step 2,3,4:Created Support case with ID: " + supportCaseNumber);
					//Changing owner to IC Global
					sfdcLib.ChangeOwner_InSupportDetail(changeOwner);
					commonLib.log(LogStatus.PASS, "Step 5:Change Support Case Owner to " + changeOwner);            
					sfdcLib.logout();
					

					commonLib.log(LogStatus.PASS, "Step 6:Logout");            
					commonLib.syso("Logged out from " + ICLocalUser + "profile");
					commonLib.log(LogStatus.PASS, "*** Test Case 138968 : PASSED ***");					
					

					
					commonLib.log(LogStatus.PASS, "*** Test Case 138969 : Verify that ICM Global is able to Approve the Support Case created by ICM Local");
					commonLib.syso("Switch User profile to " + changeOwner);
					//Login with IC Global User
					sfdcLib.search_Profile_GXICM(ICGlobalUser);
					commonLib.log(LogStatus.PASS, "Step 1: Login to SFDC GX as " +changeOwner);
					sfdcLib.navigate_To_Existing_SupportCase_From_Global_Search(supportCaseNumber);
					commonLib.log(LogStatus.PASS, "Step 2:Go to Support Tab and search for the above created support case.");
					//Changing of Status of Support case
					sfdcLib.changeStatusOf_SupportCase(support_status, support_OutlinerReviewStatus);
					//Assertion for Support Case status
					if(commonLib.getText("SupportCase_Detail_Status_XPATH").equalsIgnoreCase(support_status))
					{
						commonLib.log(LogStatus.PASS, "Step 3.1 :Status is successfully changed to " + support_status);
					}
					else
					{
						commonLib.log(LogStatus.FAIL, "Step 3.1 :Status is not successfully changed to " + support_status);    
					}
					//Assertion for Support Outliner Review status
					if(commonLib.getText("SupportCase_Detail_Outliner_Status_XPATH").equalsIgnoreCase(support_OutlinerReviewStatus))
					{
						commonLib.performHoverandClick("SupportCase_Detail_Outliner_Status_XPATH");
						commonLib.getScreenshot();
						commonLib.log(LogStatus.PASS, "Step 3.2 :Outliner Status is successfully changed to " + support_OutlinerReviewStatus);

					}
					else
					{
						commonLib.log(LogStatus.FAIL, "Step 3.2 :Outliner Status is not successfully changed to " + support_OutlinerReviewStatus);    
					}
					//Assertion for Support Close Notes status
					if(commonLib.getText("SupportCase_Detail_ChangeNote_Status_XPATH").equalsIgnoreCase("Closing Notes for status as " + support_status + " OutlinerReview Status as: " + support_OutlinerReviewStatus))
					{
						commonLib.log(LogStatus.PASS, "Step 3.3 : Closed Notes are changed as" + "Closing Notes for status as " + support_status + " OutlinerReview Status as: " + support_OutlinerReviewStatus);

					}
					else
					{
						commonLib.log(LogStatus.FAIL, "Step 3.3: Closed Notes are not changed as" + "Closing Notes for status as " + support_status + " OutlinerReview Status as: " + support_OutlinerReviewStatus);    

					}  
													
					sfdcLib.logout();
					commonLib.log(LogStatus.PASS, "*** Test Case 138969 : PASSED ***");
				
					commonLib.log(LogStatus.PASS,"***** Iteration -"+i + "(Ends) *****");
					commonLib.syso("******************** Executed  test data for Iteration='"+i +"' ********************");
				}

			}

		}
		catch(Exception e){
			e.printStackTrace();
			commonLib.log(LogStatus.FAIL, "Test_Script_GXICM_E2E_Support_Case_Change_Owner_And_Approve_138966 is failing. See Stack trace: "+" "+e.getMessage());

		}
		finally {
			commonLib.endTest();
		}


	}
}



