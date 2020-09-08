package main.java.com.stryker.NV.scripts.module1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.relevantcodes.extentreports.LogStatus;
import com.stryker.star.CommonLibrary;

import main.java.com.stryker.NV.library.SfdcLibrary;

public class GXIC_E2E_DueDiligence_RejectDD_139075 {
	
	private CommonLibrary commonLib;
	private SfdcLibrary sfdcLib;
	Date date= new Date();
	long ts = date.getTime();
	Timestamp timeStamp = new Timestamp(date.getTime());
	String Subject_Name = "Automation Task " +timeStamp.toString();

	
    public GXIC_E2E_DueDiligence_RejectDD_139075(CommonLibrary commonLib) {
    	this.commonLib=commonLib;
		this.sfdcLib = new SfdcLibrary(commonLib);
	}
    
    public void Test_Script_GXIC_E2E_DueDiligence_RejectDD_139075()
    {
    	
    	try {
    		String worksheet = commonLib.getConfigValue("SfdcDataSheet");
    		
    		int rowcount=sfdcLib.getRowCount(worksheet, "DueDiligence_RejectDD_139075");
			commonLib.syso("Total number of test data rows are: " + rowcount);
			//Code for Login with System user
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
			commonLib.setTest("Due_Diligence_Reject_139075");
			
			
			
			
			List<String> Run_data=sfdcLib.read_Test_Data(worksheet, "Off_Level_Risk_138962", 1, rowcount, 0, 0);
			//Getting data in List for Due diligence. 
			List<String> AccountName_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,1,1);
			List<String> ICFinanceUser_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,2,2);			
			List<String> ICMFinanceRecommendation_Status_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,3,3);
			List<String> ICFinance_Rationale_for_Recommendation_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,4,4);
			List<String> ICLocalUser_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,5,5);			
			List<String> ICMLocalRecommendation_Status_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,6,6);
			List<String> ICLocal_Rationale_for_Recommendation_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,7,7);
			List<String> ICComplianceUser_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,8,8);			
			List<String> ICMComplianceRecommendation_Status_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,9,9);
			List<String> ICCompliance_Rationale_for_Recommendation_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,10,10);
			List<String> ICCommercialUser_data=sfdcLib.read_Test_Data(worksheet, "DueDiligence_RejectDD_139075", 1, rowcount,11,11);
			
			
			//Executing Test case for all the iterations as per Test data
			for(int i=1;i<=rowcount;i++)
			{
				
				String Run_Staus = Run_data.get(i-1);
				String AccountName=AccountName_data.get(i-1);
				String ICFinanceUser=ICFinanceUser_data.get(i-1);
				String ICMFinanceRecommendation_Status=ICMFinanceRecommendation_Status_data.get(i-1);
				String ICFinance_Rationale_for_Recommendation=ICFinance_Rationale_for_Recommendation_data.get(i-1);
				String ICLocalUser=ICLocalUser_data.get(i-1);
				String ICMLocalRecommendation_Status=ICMLocalRecommendation_Status_data.get(i-1);
				String ICLocal_Rationale_for_Recommendation=ICLocal_Rationale_for_Recommendation_data.get(i-1);
				String ICComplianceUser=ICComplianceUser_data.get(i-1);
				String ICMComplianceRecommendation_Status=ICMComplianceRecommendation_Status_data.get(i-1);
				String ICCompliance_Rationale_for_Recommendation=ICCompliance_Rationale_for_Recommendation_data.get(i-1);
				String ICCommercialUser=ICCommercialUser_data.get(i-1);
				
				if(Run_Staus.equalsIgnoreCase("Yes"))
				{
					commonLib.log(LogStatus.INFO,"***** Iteration: "+i + "(Starts) *****");
					commonLib.syso("***** Iteration: "+i + "(Starts)*****");
					commonLib.log(LogStatus.INFO, "*** Verify that ICM Finance is able to Reject the DD");
					sfdcLib.search_Profile_GXICM(ICFinanceUser);
					commonLib.log(LogStatus.PASS, "Step 1:Login to IC-HUB as ICM Finance");

					sfdcLib.Navigate_To_Existing_Account(AccountName);
					commonLib.log(LogStatus.PASS, "Step 2:Search Existing Account in SFDCCapture the Screen Shot " + AccountName);
					commonLib.syso("Search Existing Account in SFDCCapture the Screen Shot: " + AccountName );
					commonLib.getScreenshot();
				}
				
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    }

}
