package main.java.com.stryker.NV.scripts.module1;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.relevantcodes.extentreports.LogStatus;
import com.stryker.star.CommonLibrary;

import main.java.com.stryker.NV.library.SfdcLibrary;
import main.java.com.stryker.NV.scripts.module1.PreIntake_Sample;

public class Compliance_Form {
	private CommonLibrary commonLib;
	private SfdcLibrary sfdcLib;
	Date date = new Date();
	long ts = date.getTime();
	Timestamp timeStamp = new Timestamp(date.getTime());
	String Subject_Name = "Automation Task " + timeStamp.toString();
	public static String ComplianceFormNumber;
	public static String AccountName;

	public Compliance_Form(CommonLibrary commonLib) {
		this.commonLib = commonLib;
		this.sfdcLib = new SfdcLibrary(commonLib);
	}

	public void Test_Script_Compliance_Form() {

		try {
			String worksheet = commonLib.getConfigValue("SfdcDataSheet");
			int rowcount = sfdcLib.getRowCount(worksheet, "ComplianceForm");
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
			commonLib.setTest("Compliance Form");

			List<String> Run_data = sfdcLib.read_Test_Data(worksheet, "ComplianceForm", 1, rowcount, 0, 0);
			List<String> ICSponsorUser_data = sfdcLib.read_Test_Data(worksheet, "ComplianceForm", 1, rowcount, 1, 1);
			List<String> FormStatus_data = sfdcLib.read_Test_Data(worksheet, "ComplianceForm", 1, rowcount, 2, 2);
			
			for (int i = 1; i <= rowcount; i++) {
				String Run_Status = Run_data.get(i - 1);
				String ICSponsorUser = ICSponsorUser_data.get(i - 1);
				String FormStatus = FormStatus_data.get(i - 1);
				String FormNumber = PreIntake_Sample.Preintakeform;
				String Contact = PreIntake_Sample.ContactName;
				AccountName = PreIntake_Sample.PFAccountName;
				String filepath = System.getProperty("user.dir")
						+ "\\src\\main\\java\\com\\stryker\\NV\\data\\images\\Test Doc.pdf";

				if (Run_Status.equalsIgnoreCase("Yes")) {
					commonLib.syso("***** Iteration: " + i + "(Starts)*****");
					commonLib.log(LogStatus.INFO, "*** Test Case for Compliance Form ***");
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
					String ComplianceForm = commonLib.getText("Intake_Compliance_Form_XPATH");
					ComplianceFormNumber = ComplianceForm;
					commonLib.clickWithDynamicValue("Intake_Form_List_XPATH", ComplianceForm);
					commonLib.log(LogStatus.PASS, "Step 2: Navigated to Compliance Form: "+ComplianceFormNumber);
					commonLib.getScreenshot();

					commonLib.waitforElement("Intake_Form_Edit_XPATH");
					commonLib.click("Intake_Form_Edit_XPATH");

					commonLib.scrollDownToElement("Intake_Indirect_Channel_Company_Information_XPATH", "Button");
					commonLib.click("Intake_Indirect_Channel_Company_Information_XPATH");
					commonLib.click("Intake_Denied_Official_License_XPATH");
					commonLib.clickWithDynamicValue("Intake_Denied_Official_License_Data_XPATH", "No");
					commonLib.click("Intake_Convicted_For_Offence_XPATH");
					commonLib.clickWithDynamicValue("Intake_Convicted_For_Offence_Data_XPATH", "No");
					commonLib.click("Intake_Company_Litigation_XPATH");
					commonLib.clickWithDynamicValue("Intake_Company_Litigation_Data_XPATH", "No");
					commonLib.click("Intake_Company_Banned_XPATH");
					commonLib.clickWithDynamicValue("Intake_Company_Banned_Data_XPATH", "No");
					commonLib.scrollDownToElement("Intake_Compliance_Program_XPATH", "Input");
					commonLib.click("Intake_Compliance_Program_XPATH");
					commonLib.clickWithDynamicValue("Intake_Compliance_Program_Data_XPATH", "No");
					commonLib.click("Intake_Conduct_Ethics_XPATH");
					commonLib.clickWithDynamicValue("Intake_Conduct_Ethics_Data_XPATH", "No");
					commonLib.click("Intake_Anti_Corruption_XPATH");
					commonLib.clickWithDynamicValue("Intake_Anti_Corruption_Data_XPATH", "No");
					commonLib.click("Intake_Publicly_Funded_Healthcare_XPATH");
					commonLib.clickWithDynamicValue("Intake_Publicly_Funded_Healthcare_Data_XPATH", "No");
					commonLib.scrollDownToElement("Intake_Directly_To_Patients_XPATH", "Input");
					commonLib.click("Intake_Directly_To_Patients_XPATH");
					commonLib.clickWithDynamicValue("Intake_Directly_To_Patients_Data_XPATH", "No");
					commonLib.click("Intake_Government_Relations_XPATH");
					commonLib.clickWithDynamicValue("Intake_Government_Relations_Data_XPATH", "No");
					commonLib.log(LogStatus.PASS,
							"Step 3: Completed Mandatory fields for 'Indirect Channel Company Information' Section");

					commonLib.scrollDownToElement("Intake_Business_Engage_Activities_XPATH", "Button");
					commonLib.click("Intake_Business_Engage_Activities_XPATH");
					commonLib.click("Intake_Meal_With_HealtCareProfessional_XPATH");
					commonLib.clickWithDynamicValue("Intake_Meal_With_HealtCareProfessional_Data_XPATH", "No");
					commonLib.click("Intake_Gift_To_HealtCareProfessional_XPATH");
					commonLib.clickWithDynamicValue("Intake_Gift_To_HealtCareProfessional_Data_XPATH", "No");
					commonLib.scrollDownToElement("Intake_Training_And_Education_XPATH", "Input");
					commonLib.click("Intake_Training_And_Education_XPATH");
					commonLib.clickWithDynamicValue("Intake_Training_And_Education_Data_XPATH", "No");
					commonLib.click("Intake_Sponsorship_XPATH");
					commonLib.clickWithDynamicValue("Intake_Sponsorship_Data_XPATH", "No");
					commonLib.log(LogStatus.PASS,
							"Step 4: Completed Mandatory fields for 'Do you and your business engage in the following activities' Section");

					commonLib.scrollDownToElement("Intake_Certification_XPATH", "Button");
					commonLib.click("Intake_Certification_XPATH");
					commonLib.click("Intake_Certification_Checkbox_XPATH");
					commonLib.log(LogStatus.PASS, "Step 5: Completed Mandatory fields for 'Certification' Section");

					commonLib.scrollDownToElement("Intake_Certify_XPATH", "Button");
					commonLib.click("Intake_Certify_XPATH");
					commonLib.click("Intake_Employ_Govt_Official_XPATH");
					commonLib.clickWithDynamicValue("Intake_Employ_Govt_Official_Data_XPATH", "I Agree");
					commonLib.scrollDownToElement("Intake_Training_And_Education_XPATH", "Input");
					commonLib.click("Intake_Duplicate_Payment_XPATH");
					commonLib.clickWithDynamicValue("Intake_Duplicate_Payment_Data_XPATH", "I Agree");
					commonLib.click("Intake_Violation_Of_Laws_XPATH");
					commonLib.clickWithDynamicValue("Intake_Violation_Of_Laws_Data_XPATH", "I Agree");
					commonLib.scrollDownToElement("Intake_Law_And_Regulation_XPATH", "Input");
					commonLib.click("Intake_Law_And_Regulation_XPATH");
					commonLib.clickWithDynamicValue("Intake_Law_And_Regulation_Data_XPATH", "I Agree");
					commonLib.click("Intake_Employee_Compliance_Training_XPATH");
					commonLib.clickWithDynamicValue("Intake_Employee_Compliance_Training_Data_XPATH", "I Agree");
					commonLib.log(LogStatus.PASS,
							"Step 6: Completed Mandatory fields for 'In addition, we certify the following' Section");

					commonLib.click("Intake_Save_For_Later_XPATH");
					Thread.sleep(3000);
					commonLib.log(LogStatus.PASS, "Step 7: Form filled and Saved for later");
					commonLib.getScreenshot();
					Thread.sleep(2000);
					commonLib.refreshPage();
					commonLib.waitforElement("Intake_Form_Edit_XPATH");
					commonLib.click("Intake_Form_Edit_XPATH");
					commonLib.waitforElement("Intake_Review_Checkbox_XPATH");
					commonLib.click("Intake_Review_Checkbox_XPATH");
					commonLib.click("Submit_Review_XPATH");
					Thread.sleep(3000);
					commonLib.waitforElement("Intake_Form_Edit_XPATH");
					commonLib.refreshPage();
					Thread.sleep(2000);
					commonLib.waitforElement("Form_Status_XPATH");
					if (commonLib.getText("Form_Status_XPATH").equalsIgnoreCase(FormStatus)) {
						commonLib.log(LogStatus.PASS, "Step 8: Compliance Form Submitted for Review");
						commonLib.getScreenshot();
					} else {
						commonLib.log(LogStatus.FAIL, "Step 8: Compliance Form is not submitted for review");
						commonLib.getScreenshot();
					}
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
