package main.java.com.stryker.NV.scripts.module1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.relevantcodes.extentreports.LogStatus;
import com.stryker.star.CommonLibrary;

import main.java.com.stryker.NV.library.SfdcLibrary;
import main.java.com.stryker.NV.scripts.module1.PreIntake_Sample;

public class Finance_Form {
	private CommonLibrary commonLib;
	private SfdcLibrary sfdcLib;
	Date date = new Date();
	long ts = date.getTime();
	Timestamp timeStamp = new Timestamp(date.getTime());
	String Subject_Name = "Automation Task " + timeStamp.toString();
	public static String FinanceFormNumber;

	public Finance_Form(CommonLibrary commonLib) {
		this.commonLib = commonLib;
		this.sfdcLib = new SfdcLibrary(commonLib);
	}

	public void Test_Script_Finance_Form() {

		try {
			String worksheet = commonLib.getConfigValue("SfdcDataSheet");
			int rowcount = sfdcLib.getRowCount(worksheet, "FinanceForm");
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
			commonLib.setTest("Finance Form");

			List<String> Run_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 0, 0);
			List<String> ICSponsorUser_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 1, 1);
			List<String> BookKeeping_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 2, 2);
			List<String> TransactionRecorded_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 3, 3);
			List<String> PercentageofSuppDoc_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 4, 4);
			List<String> UseOfCash_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 5, 5);
			List<String> LevelOfDetail_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 6, 6);
			List<String> HealthcareProfessional_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 7, 7);
			List<String> FormStatus_data = sfdcLib.read_Test_Data(worksheet, "FinanceForm", 1, rowcount, 8, 8);
			
			for (int i = 1; i <= rowcount; i++) {
				String Run_Status = Run_data.get(i - 1);
				String ICSponsorUser = ICSponsorUser_data.get(i - 1);
				String BookKeeping = BookKeeping_data.get(i - 1);
				String TransactionRecorded = TransactionRecorded_data.get(i - 1);
				String PercentageofSuppDoc = PercentageofSuppDoc_data.get(i - 1);
				String UseOfCash = UseOfCash_data.get(i - 1);
				String LevelOfDetail = LevelOfDetail_data.get(i - 1);
				String HealthcareProfessional = HealthcareProfessional_data.get(i - 1);
				String FormStatus = FormStatus_data.get(i - 1);
				
				String FormNumber = PreIntake_Sample.Preintakeform;
				String Contact = PreIntake_Sample.ContactName;
				String AccountName = PreIntake_Sample.PFAccountName;
				String filepath = System.getProperty("user.dir")
						+ "\\src\\main\\java\\com\\stryker\\NV\\data\\images\\Test Doc.pdf";

				if (Run_Status.equalsIgnoreCase("Yes")) {
					commonLib.syso("***** Iteration: " + i + "(Starts)*****");
					commonLib.log(LogStatus.INFO, "*** Test Case for Finance Form. ***");
					Thread.sleep(2000);
					sfdcLib.App_Launcher_Search("IC Hub");
					Thread.sleep(3000);
					sfdcLib.globalSearch(FormNumber);
					commonLib.waitforElement("Intake_ContactName_XPATH");
					commonLib.click("Intake_ContactName_XPATH");
					Thread.sleep(3000);
					commonLib.waitforElement("Intake_Contact_More_DD_XPATH");
					commonLib.clickbyjavascript("Intake_Contact_More_DD_XPATH");
					commonLib.clickbyjavascript("Intake_Contact_Login_XPATH");
					commonLib.log(LogStatus.PASS, "Step 1: Login as Customer User (Contact)");
					commonLib.getScreenshot();
					commonLib.waitForPageToLoad();
					commonLib.waitforElement("Intake_Contact_Form_XPATH");
					commonLib.click("Intake_Contact_Form_XPATH");
					commonLib.waitForPageToLoad();
					commonLib.clickbyjavascript("Intake_List_View_XPATH");
					commonLib.clickbyjavascript("Intake_List_Active_Forms_XPATH");
					Thread.sleep(2000);
					String FinanceForm = commonLib.getText("Intake_Finance_Form_XPATH");
					FinanceFormNumber = FinanceForm;
					commonLib.clickWithDynamicValue("Intake_Form_List_XPATH", FinanceForm);
					commonLib.log(LogStatus.PASS, "Step 2: Navigated to Finance Form: "+FinanceFormNumber);
					commonLib.getScreenshot();
					commonLib.waitforElement("Intake_Form_Edit_XPATH");
					commonLib.click("Intake_Form_Edit_XPATH");

					commonLib.scrollDownToElement("Intake_External_Auditor_XPATH", "Button");
					commonLib.click("Intake_External_Auditor_XPATH");
					commonLib.click("Intake_External_Financial_Audit_XPATH");
					commonLib.clickWithDynamicValue("Intake_External_Financial_Audit_Data_XPATH", "No");
					commonLib.log(LogStatus.PASS,
							"Step 3: Completed the mandatory fields for 'External Auditor' Section.");

					commonLib.scrollDownToElement("Intake_Credit_References_XPATH", "Button");
					commonLib.click("Intake_Credit_References_XPATH");
					commonLib.click("Intake_Upload_Evidence_Business_Credit_XPATH");
					sfdcLib.UploadFile(filepath);
					commonLib.waitforElement("Contract_Upload_Done_XPATH");
					Thread.sleep(5000);
					commonLib.click("Contract_Upload_Done_XPATH");
					commonLib.log(LogStatus.PASS,
							"Step 4: Completed the mandatory fields for 'Credtit Reference' Section.");
					Thread.sleep(2000);

					commonLib.scrollDownToElement("Intake_BR_Questionnaire_XPATH", "Button");
					commonLib.click("Intake_BR_Questionnaire_XPATH");
					commonLib.click("Intake_Book_Keeping_XPATH");
					commonLib.clickWithDynamicValue("Intake_Book_Keeping_Data_XPATH", BookKeeping);
					commonLib.click("Intake_Transaction_Recorded_XPATH");
					commonLib.clickWithDynamicValue("Intake_Transaction_Recorded_Data_XPATH", TransactionRecorded);
					commonLib.click("Intake_Supporting_Documentation_XPATH");
					commonLib.clickWithDynamicValue("Intake_Supporting_Documentation_Data_XPATH", PercentageofSuppDoc);
					commonLib.scrollDownToElement("Intake_Use_Of_Cash_XPATH", "Input");
					commonLib.click("Intake_Use_Of_Cash_XPATH");
					commonLib.clickWithDynamicValue("Intake_Use_Of_Cash_Data_XPATH", UseOfCash);
					commonLib.scrollDownToElement("Intake_Level_Of_Detail_XPATH", "Input");
					commonLib.click("Intake_Level_Of_Detail_XPATH");
					commonLib.clickWithDynamicValue("Intake_Level_Of_Detail_Data_XPATH", LevelOfDetail);
					commonLib.scrollDownToElement("Intake_HealthCare_Professionals_XPATH", "Input");
					commonLib.click("Intake_HealthCare_Professionals_XPATH");
					commonLib.clickWithDynamicValue("Intake_HealthCare_Professionals_Data_XPATH",HealthcareProfessional);
					commonLib.click("Intake_Provide_Documentation_XPATH");
					commonLib.clickWithDynamicValue("Intake_Provide_Documentation_Data_XPATH", "No");
					commonLib.log(LogStatus.PASS,
							"Step 5: Completed the mandatory fields for 'B&R Questionnaire' Section.");

					commonLib.click("Intake_Save_For_Later_XPATH");
					Thread.sleep(3000);
					commonLib.log(LogStatus.PASS, "Step 6: Form filled and Saved for later.");
					commonLib.getScreenshot();
					Thread.sleep(3000);
					commonLib.refreshPage();
					commonLib.waitforElement("Intake_Form_Edit_XPATH");
					commonLib.click("Intake_Form_Edit_XPATH");
					commonLib.waitforElement("Intake_Review_Checkbox_XPATH");
					commonLib.click("Intake_Review_Checkbox_XPATH");
					commonLib.click("Submit_Review_XPATH");
					if(commonLib.waitForPresenceOfElementLocated("Intake_Error_submitting_records_XPATH")) {
						commonLib.click("Intake_Credit_References_XPATH");
						commonLib.click("Submit_Review_XPATH");
					}
					commonLib.waitforInvisibilityOfElement("Submit_Review_XPATH");
					commonLib.waitforElement("Intake_Form_Edit_XPATH");
					if (commonLib.getText("Form_Status_XPATH").equalsIgnoreCase(FormStatus)) {
						commonLib.log(LogStatus.PASS, "Step 7: Finance Form Submitted for Review");
						commonLib.getScreenshot();
					} else {
						commonLib.log(LogStatus.FAIL, "Step 7: Finance Form is not submitted for review");
						commonLib.getScreenshot();
					}
//					commonLib.log(LogStatus.PASS, "Step 6: Finance Form is Submitted for review");
//					commonLib.getScreenshot();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			commonLib.log(LogStatus.FAIL,
					"PreIntake_Approval test case is failing. See Stack trace: " + " " + e.getMessage());
		} finally {
			commonLib.endTest();
		}
	}
}
