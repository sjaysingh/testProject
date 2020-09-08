package main.java.com.stryker.NV.scripts.module1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.com.stryker.NV.scripts.module1.PreIntake_Sample;
import main.java.com.stryker.NV.scripts.module1.Compliance_Form;
import main.java.com.stryker.NV.scripts.module1.RAQAForm;
import main.java.com.stryker.NV.scripts.module1.Finance_Form;

import com.relevantcodes.extentreports.LogStatus;
import com.stryker.star.CommonLibrary;

import main.java.com.stryker.NV.library.SfdcLibrary;

public class Form_Approvals {
	private CommonLibrary commonLib;
	private SfdcLibrary sfdcLib;
	Date date = new Date();
	long ts = date.getTime();
	Timestamp timeStamp = new Timestamp(date.getTime());
	String Subject_Name = "Automation Task " + timeStamp.toString();

	public Form_Approvals(CommonLibrary commonLib) {
    	this.commonLib=commonLib;
		this.sfdcLib = new SfdcLibrary(commonLib);
	}

	public void Test_Script_Form_Approvals() {

		try {
			String worksheet = commonLib.getConfigValue("SfdcDataSheet");
			int rowcount = sfdcLib.getRowCount(worksheet, "FormsApproval");
			commonLib.syso("Total number of test data rows are: " + rowcount);
			List<String> UserData1 = new ArrayList<String>();
			UserData1 = sfdcLib.read_Test_Data(worksheet, "Users", 7, 7, 2, 3);

			sfdcLib.login(UserData1.get(0), UserData1.get(1));// Calling Login method to login into the Application.
			commonLib.waitForDynamicTitle("Salesforce", 120);
			commonLib.waitForPageToLoad();
			commonLib.waitForPresenceOfElementLocated("GlobalSearch_Homepage_XPATH");
			Thread.sleep(5 * 1000);
			commonLib.waitForPresenceOfElementLocated("User_settings_Button_XPATH");
			// Calling method to click on Setup button from Homepage after login.
			String currentHandle = sfdcLib.click_Setup_FromHomePage_AfterLogin();
			commonLib.syso("************** Running test data for Iteration");
			commonLib.setTest("Form Approval");

			List<String> Run_data = sfdcLib.read_Test_Data(worksheet, "FormsApproval", 1, rowcount, 0, 0);
			List<String> ICComplianceUser_data = sfdcLib.read_Test_Data(worksheet, "FormsApproval", 1, rowcount, 1, 1);
			List<String> ICRAQAUser_data = sfdcLib.read_Test_Data(worksheet, "FormsApproval", 1, rowcount, 2, 2);
			List<String> ICFinanceUser_data = sfdcLib.read_Test_Data(worksheet, "FormsApproval", 1, rowcount, 3, 3);
			List<String> ApproveOption_data = sfdcLib.read_Test_Data(worksheet, "FormsApproval", 1, rowcount, 4, 4);
			List<String> ApprovalComment_data = sfdcLib.read_Test_Data(worksheet, "FormsApproval", 1, rowcount, 5, 5);
			List<String> InterviewMode_data = sfdcLib.read_Test_Data(worksheet, "FormsApproval", 1, rowcount, 6, 6);
			
			for (int i = 1; i <= rowcount; i++) {
				String Run_Status = Run_data.get(i - 1);
				String ICComplianceUser = ICComplianceUser_data.get(i - 1);
				String ICRAQAUser = ICRAQAUser_data.get(i - 1);
				String ICFinanceUser = ICFinanceUser_data.get(i - 1);
				String ApprovalComment=ApprovalComment_data.get(i - 1);
				String InterviewMode = InterviewMode_data.get(i-1);
				String ApproveOption = ApproveOption_data.get(i-1);
				String ComplianceForm = Compliance_Form.ComplianceFormNumber;;
				String FinanceForm = Finance_Form.FinanceFormNumber;
				String RAQA_Form = RAQAForm.RAQAFormNumber;
				String Account = Compliance_Form.AccountName;

				if (Run_Status.equalsIgnoreCase("Yes")) {
					commonLib.syso("***** Iteration: " + i + "(Starts)*****");
					commonLib.log(LogStatus.INFO, "*** Test Case for Form Approval. ***");
					commonLib.log(LogStatus.INFO, "*** Test Case: Approval of All forms (Compliance, RAQA and Finance) ***");
					
					sfdcLib.search_Profile_GXICM(ICComplianceUser);
					commonLib.log(LogStatus.PASS, "Step 1: Login to IC-HUB as IC Compliance");
					sfdcLib.globalSearch(ComplianceForm);
					commonLib.log(LogStatus.PASS, "Step 2: Navigated to Form: "+ComplianceForm);
					commonLib.waitforElement("Intake_Edit_Btn_XPATH");
					commonLib.click("Intake_Edit_Btn_XPATH");
					Thread.sleep(2000);
					commonLib.click("Intake_Approver_Action_XPATH");
					commonLib.clickWithDynamicValue("Intake_Approver_Option_XPATH", ApproveOption);
					commonLib.sendKeys("Intake_Approve_Comments_Internal_XPATH", ApprovalComment);
					Thread.sleep(5000);
					commonLib.waitforElement("Intake_Complete_Review_XPATH");
					commonLib.click("Intake_Complete_Review_XPATH");
					Thread.sleep(3000);
					commonLib.refreshPage();
					commonLib.waitforElement("Intake_Form_Status_XPATH");
					if(commonLib.getText("Intake_Form_Status_XPATH").equalsIgnoreCase("Approved")) {
						commonLib.log(LogStatus.PASS, "Step 3: Compliance Form is Approved");
						commonLib.getScreenshot();
					}
					else {
						commonLib.log(LogStatus.FAIL, "Step 3: Compliance Form cannot be Approved");
						commonLib.getScreenshot();
					}
					sfdcLib.logout();
					
					Thread.sleep(3000);					
					sfdcLib.search_Profile_GXICM(ICRAQAUser);
					commonLib.log(LogStatus.PASS, "Step 4: Login to IC-HUB as RAQA User");
					sfdcLib.globalSearch(RAQA_Form);
					commonLib.log(LogStatus.PASS, "Step 5: Navigated to Form: "+RAQA_Form);
					commonLib.waitforElement("Intake_Edit_Btn_XPATH");
					commonLib.click("Intake_Edit_Btn_XPATH");
					commonLib.waitForPageToLoad();
					commonLib.refreshPage();
					commonLib.waitforElement("Intake_Approver_Action_XPATH");
					commonLib.click("Intake_Approver_Action_XPATH");
					commonLib.clickWithDynamicValue("Intake_Approver_Option_XPATH", ApproveOption);
					commonLib.sendKeys("Intake_Approve_Comments_Internal_XPATH", ApprovalComment);
					Thread.sleep(2000);
					commonLib.waitforElement("Intake_Complete_Review_XPATH");
					commonLib.click("Intake_Complete_Review_XPATH");
					Thread.sleep(3000);
					sfdcLib.globalSearch(RAQA_Form);
					commonLib.waitforElement("Intake_Form_Status_XPATH");
					commonLib.waitforElement("Intake_Form_Status_XPATH");
					if(commonLib.getText("Intake_Form_Status_XPATH").equalsIgnoreCase("Approved")) {
						commonLib.log(LogStatus.PASS, "Step 6: RAQA Form is Approved");
						commonLib.getScreenshot();
					}
					else {
						commonLib.log(LogStatus.FAIL, "Step 6: RAQA Form cannot be Approved");
						commonLib.getScreenshot();
					}
					sfdcLib.logout();
					
					Thread.sleep(3000);
					sfdcLib.search_Profile_GXICM(ICFinanceUser);
					commonLib.log(LogStatus.PASS, "Step 7: Login to IC-HUB as IC Finance");
					Thread.sleep(2000);
					sfdcLib.globalSearch(FinanceForm);
					commonLib.waitforElement("Intake_Parent_Form_XPATH");
					commonLib.click("Intake_Parent_Form_XPATH");
					commonLib.waitforElement("Intake_Account_Name_XPATH");
					commonLib.click("Intake_Account_Name_XPATH");
					commonLib.log(LogStatus.PASS, "Step 8: Navigated to the account");
					commonLib.waitforElement("Intake_Acc_Default_ARR_Data_XPATH");
					commonLib.scrollDownToElement("Intake_Acc_Default_ARR_Data_XPATH","Field");
					if(commonLib.getText("Intake_Acc_Default_DRR_Data_XPATH").equalsIgnoreCase("L4")) {
						commonLib.log(LogStatus.PASS, "Step 9.1: Default Risk Rating is visible on the Account.");
					}
					else {
						commonLib.log(LogStatus.FAIL, "Step 9.1: Default Risk Rating is not visible on the Account.");
					}
					if(commonLib.getText("Intake_Acc_Default_ARR_Data_XPATH").equalsIgnoreCase("L4")) {
						commonLib.log(LogStatus.PASS, "Step 9.2: Active Risk Rating is visible on the Account.");
						commonLib.getScreenshot();
					}
					else {
						commonLib.log(LogStatus.FAIL, "Step 9.2: Active Risk Rating is not visible on the Account.");
						commonLib.getScreenshot();
					}
					sfdcLib.globalSearch(FinanceForm);
					commonLib.log(LogStatus.PASS, "Step 10: Navigated to Form: "+FinanceForm);
					commonLib.waitforElement("Intake_Edit_Btn_XPATH");
					if(commonLib.waitForPresenceOfElementLocated("Intake_Finance_BRIndicator_XPATH")) {
						commonLib.log(LogStatus.PASS, "Step 11.1: Books and Records Indicator is present.");
						commonLib.getScreenshot();
					}
					else {
						commonLib.log(LogStatus.FAIL, "Step 11.2: Books and Records Indicator is not present.");
						commonLib.getScreenshot();
					}
					String Score = commonLib.getText("Intake_Finance_BRScore_XPATH");
					int checkScore=Integer. parseInt(Score);
					if(checkScore>=0 && checkScore<=100) {
						commonLib.log(LogStatus.PASS, "Step 12.1: Books and Records Score is between 0 and 100.");
						commonLib.getScreenshot();
					}
					else {
						commonLib.log(LogStatus.FAIL, "Step 12.2: Books and Records Score is not between 0 and 100.");
						commonLib.getScreenshot();
					}
					commonLib.click("Intake_Finance_BRReview_XPATH");
					commonLib.scrollDownToElement("Intake_Financial_Interview_Mode_XPATH", "Input");
					commonLib.click("Intake_Financial_Interview_Mode_XPATH");
					commonLib.clickWithDynamicValue("Intake_Financial_Interview_Mode_Data_XPATH", InterviewMode);
					Thread.sleep(2000);
					commonLib.click("Intake_Save_For_Later_XPATH");
					Thread.sleep(3000);
					commonLib.log(LogStatus.PASS, "Step 13: Performed B&R Review.");
					commonLib.getScreenshot();
					Thread.sleep(3000);
					commonLib.waitforElement("Intake_Edit_Btn2_XPATH");
					commonLib.click("Intake_Edit_Btn2_XPATH");
					commonLib.click("Intake_Approver_Action_XPATH");
					Thread.sleep(2000);
					commonLib.clickWithDynamicValue("Intake_Approver_Option_XPATH", ApproveOption);
					commonLib.sendKeys("Intake_Approve_Comments_Internal_XPATH", ApprovalComment);
					Thread.sleep(3000);
					commonLib.waitforElement("Intake_Complete_Review_XPATH");
					commonLib.click("Intake_Complete_Review_XPATH");
					Thread.sleep(3000);
					commonLib.refreshPage();
					sfdcLib.globalSearch(FinanceForm);
					commonLib.waitforElement("Intake_Form_Status_XPATH");
					if(commonLib.getText("Intake_Form_Status_XPATH").equalsIgnoreCase("Approved")) {
						commonLib.log(LogStatus.PASS, "Step 14: Finance Form is Approved");
						commonLib.getScreenshot();
					}
					else {
						commonLib.log(LogStatus.FAIL, "Step 14: Finance Form cannot be Approved");
						commonLib.getScreenshot();
					}
					sfdcLib.logout();	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonLib.log(LogStatus.FAIL,"Form Approval test case is failing. See Stack trace: " + " " + e.getMessage());
		} finally {
			commonLib.endTest();
		}
	}
}
