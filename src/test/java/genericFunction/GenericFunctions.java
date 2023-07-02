package genericFunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



import freemarker.core.ParseException;



public class GenericFunctions{

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	private FileInputStream fis;
	private FileOutputStream fileOut;
	private WorkbookFactory workbookfactory;
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	private Cell cell;
	static JavascriptExecutor js;
	static Actions act;
	Utility util;
	public String path;
	static Properties prop=new Properties();
	public WebDriver driver;
	Boolean value;
	
	public GenericFunctions(WebDriver driver) {
		this.driver = driver;	
		PageFactory.initElements(driver, this);	

	}

	public void getUrl(String url)  {

		driver.get(url);

	}
	
	
	// *********************************HANDLE_IFRAME**********************************************
	public  void switchToElement(WebElement element) {
		driver.switchTo().frame(element);

	}
 
	public void switchToName(String Name) {
		driver.switchTo().frame(Name);

	}
	public  void switchToId(String Id) {
		driver.switchTo().frame(Id);

	}

	public  void switchToIndex(int Index) {
		driver.switchTo().frame(Index);

	}

	public  void switchToDefault() {
		driver.switchTo().defaultContent();

	}
	public String getCurrentDate(String format) {
		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat(format);

		//get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1= dateFormat.format(date);

		return date1;

	}
	public String addDateToCurrentDate(String format,int days) throws java.text.ParseException {
		// Create object of SimpleDateFormat class and decide the format
				DateFormat dateFormat = new SimpleDateFormat(format);

				//get current date time with Date()
				Date date = new Date();
				
				String date1= dateFormat.format(date);

				Calendar cal = Calendar.getInstance();  
		        cal.setTime(dateFormat.parse(date1));  
		             
		        // use add() method to add the days to the given date  
		        cal.add(Calendar.DAY_OF_MONTH, days);  
		        String dateAfter = dateFormat.format(cal.getTime());  
		        return dateAfter;
	}
	
	
	// *********************************HIGHLIGHT_ELEMENT**********************************************
	public  void highlightElement(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);  

	}



	// *********************************DRAG_AND_DROP**********************************************
	public  void dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).perform();

	}
	// *********************************DRAG_AND_DROP**********************************************
	public  void scrollUpPage() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_UP).build().perform();

	}

	// *********************************GET_ELEMENT_BY_LOCATOR**********************************************
	public WebElement getElement(By locater) {
		WebElement element = driver.findElement(locater);
		return element;
	}

	// ***************************************CLICK********************************************************

	public  void Click(WebElement element) {
		element.click();
	}
	// ********************************SENDKEYS*******************************************

	public  void Sendkeys(WebElement element, String input) {
		element.sendKeys(input);
	}


	//***************************************RIGHT_CLICK************************************************************
	public  void RightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	// ***************************************DOUBLE_CLICK************************************************************
	public  void DoubleClick(WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}
	// ********************************CLEAR***********************************************

	public  void Clear(WebElement element) {
		element.clear();
	}
	// ***************************************SENDKEYS_WITH_ACTION************************************************************
	public void doSendKeysWithAction(WebElement element, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(element, value).perform();
	}
	// ***************************************CLICKANDHOLD_WITH_ACTION************************************************************
	public void clickAndHoldWithAction() {
		Actions act = new Actions(driver);
		act.clickAndHold();
	}

	// ***************************************CLICK_LOCATOR_WITH_ACTION************************************************************
	public void doClickWithAction(WebElement element) {
		Actions act = new Actions(driver);
		act.click(element).perform();
	}

	// ***************************************GET_TEXT************************************************************
	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}

	// ***************************************ELEMENT_IS_DISPLAYED************************************************************
	public boolean isDisplayed(WebElement element) {
		boolean value=element.isDisplayed();
		return value;

	}

	// ***************************************ELEMENT_IS_SELECTED*****************************************************
	public boolean isSelect(WebElement element) {
		boolean value=element.isSelected();
		return value;
	}

	//// ***************************************ELEMENT_IS_ENABLED*****************************************************
	public boolean isEnable(WebElement element) {
		return element.isEnabled();
	}
	// ***********************************SELECT_FROM_DROPDOWN***********************************************
	// select by visible text
	public  void SelectByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

	// select by index
	public  void SelectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	// select by value
	public  void SelectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	// ***********************************DESELECT_FROM_DROPDOWN***********************************************
	// deselect by index
	public void deselectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);

	}

	// deselect by value
	public void deselectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.deselectByValue(value);

	}

	// deselectAll drop down
	public void deSelectAll(WebElement element) {
		Select select = new Select(element);
		select.deselectAll();
	}

	// deselectByVisibleText
	public void deselectByVisibleText(WebElement element, String value) {
		Select select = new Select(element);
		select.deselectByVisibleText(value);
	}
	// ***********************************CHECK_IF_SUPPORT_MULTISELECT_DROPDOWN***********************************************
	public boolean isSelectMultiple(WebElement element) {
		Select select = new Select(element);
		return select.isMultiple();

	}
	// ***********************************CHECK_DEFAULT_VALUE_DROPDOWN***********************************************
	public String getDefaultSelectedValue(WebElement element) {
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();

	}

	// ************SWITCH_TO_OEN_WINDOW_TAB***************
			public  void switchToOpenWindow(int id) {
				ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());
				//switch to active tab
				driver.switchTo().window(wid.get(id));
			}
	
	
	// ***********************************SWITCH_WINDOW***********************************************
	public void WindowHandle(WebDriver driver) {
		String parent = driver.getWindowHandle();
		Set<String> list_of_windows = driver.getWindowHandles();

		Iterator<String> iterator = list_of_windows.iterator();

		while (iterator.hasNext()) {

			String child_window = iterator.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());

				driver.close();
			}
		}
		// again switching parent window
		driver.switchTo().window(parent);
	}
	// *********************************SWITCH_TO_ACTIVE_WINDOW_TAB**********************************************
	public  void switchToActiveWindow() {
		ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());
		//switch to active tab
		driver.switchTo().window(wid.get(1));
	}
	// *********************************MOUSE_HOVER**********************************************
	public  void MouseHover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();

	}
	
	

	// get Title
	public String getTitle() {
		return driver.getTitle();
	}

	public String doGetText(WebElement element) {
		return element.getText();
	}
	
	
	/*********************************************** This will do your forcefully click operation *******************************************/
	public void forcedClick(WebElement button) {
		try {
			
		WebElement chkBx = button;	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();", button);
	js.executeScript("arguments[0].click();", chkBx);
		}catch(Exception e) {
			System.out.println("not clicked");
		}
	}


	// *************************************ALERT************************************************
	// To click on the ‘Cancel’ button of the alert.
	public  void DismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// To click on the ‘OK’ button of the alert.
	public  void AcceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// To capture the alert message.
	public  void getAlertMessage() {
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
	}

	// To send some data to alert box.
	public  void SendDataToAlertBox(String Text) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(Text);
	}

	// **********************************CHECKBOX*************************************************
	// single checkbox
	public static void SelectCheckbox(WebElement element) {
		element.click();
	}

	// multiple checkboxes
	public  void SelectMultipleCheckboxes(List<WebElement> listOfItems, int noOfCheckboxesToBeSelected) {
		for (int i = 0; i <= noOfCheckboxesToBeSelected - 1; i++) {
			listOfItems.get(i).click();
			System.out.println("Checkbox_" + i + " selected");
		}
	}
	// ****************************************UPLOAD_FILE**********************************

	public  void UploadFile(WebElement element, String filePath) {
		Sendkeys(element, filePath);
	}
	// **********************************TAB***********************************************

	public  void TAB(WebElement element) {
		element.sendKeys(Keys.TAB);
	}
	// ************************************VERTICAL_SCROLL************************************
	public  void WindowVerticalScroll(int pixel) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		j.executeScript("window.scrollBy(0," + pixel + ")");
	}
	// ************************************Horizontal_SCROLL************************************
		public  void WindowHorizontalScroll() {
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("window.scrollBy(2000,0)");
		}

	// scroll it at the top and then by a 1/4th of the height of the view
	public  void WindowVerticalScroll(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true); window.scrollBy(0, -window.innerHeight / 4);", element);

	}

	// scroll it at the bottom
	public  void WindowScrollToBottom(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);

	}

	// scroll to the element
	public  void WindowScrollToTheElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// scroll just below an element , eg fixed header
	public  void WindowScrollToBelowTheElement(WebElement element, WebElement header) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true); window.scrollBy(0, -arguments[1].offsetHeight);", element, header);

	}

	// *********************WAIT_TILL_ELEMENT_VISIBLE*************************************

	public  void waitTillElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// ******************************WAIT_SOME_TIME*******************************************

	public  void waitForPageLoad(long mil) throws InterruptedException{
		Thread.sleep(mil);
	}

	// *************************************ASSERT***********************************************

	public boolean Assert(String actualtitle, String Expectedtitle) {

		if (actualtitle.equalsIgnoreCase(Expectedtitle))
			return true;
		else
			return false;

	}







	// ******************************JAVASCRIPT_FUNCTIONS*******************************************
	public String getTitleByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		return js.executeScript("return document.title").toString();
	}

	// to refresh webPage 
	public Object refreshWebPageByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		return js.executeScript("history.go(0");

	}
	// to send the value By JS
	public void sendKeysByJS(String id, String value, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
	// to click On element By Js
	public void clickByJS(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click()", element);
	}
	// to scroll the bottom of the page
	public void scrollDownPage(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	// this method  is to perform  scrollUp page
	public void scrollUpPage(String high) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,'"+high+"')");
	}

	public void flash(WebElement element, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String bgColor = element.getCssValue("backgroundcolor");
		for (int i = 0; i <= 10; i++) {
			changeColor("rgb(0,200,0)", element, driver);
			changeColor(bgColor, element, driver);
		}

	}

	private void changeColor(String color, WebElement element, WebDriver driver2) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("argumnts[0].style.backgroundcolor='" + color + "'", element);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// to draw broder using js
	public void drawbroder(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].style.broder='3px solid green'", element);

	}

	// alert message
	public void generateAlertMessage(WebDriver driver, String message) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("alert('" + message + "')");

	}

	//	Js click
	public Object clickOnElementByJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		return js.executeScript("argument[0].click();", element);
	}

	//	to refresh the page using Js
	public void refreshBrowserByJS(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("history.go(0)");

	}
	//  to getTitle BY Js
	public String toGetTitleByJs(WebDriver driver, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title;").toString();
		return title;

	}

	//this will return the page text
	public String getPageInnerText(WebDriver driver) {
		JavascriptExecutor js= ((JavascriptExecutor)driver);
		String pageText=js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;

	}
	//if data is get successfully then return row and column

	public String getCellStringValue(String rowName, int colNum, String sheetName) {
		try {
			if (colNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(rowName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(colNum - 1);
			if (row == null)
				return "";

			cell = row.getCell(col_Num);
			if (cell == null)
				return "";

			// System.out.println(cell.getCellType());
			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {

					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					// System.out.println(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowName + " or column " + colNum + " does not exist in xls";
		}
	}

	// it returns the row count in a sheet

	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

	// if data is set successful it returns true or else false
	public boolean setCellValue(int rowNum, int colName, String sheetName, String data) {
		try {
			fis = new FileInputStream(path);
			workbook = workbookfactory.create(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	








	// presenceOfAllElementsLocated
	public List<WebElement> waitForListOfElementPresent(By locater, Duration time) {

		WebDriverWait wait = new WebDriverWait(driver, time);

		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locater));

	}





	public Wait<WebDriver> fluentWait(WebElement element, int time,int pollingTime) {

		Wait<WebDriver>wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(StaleElementReferenceException.class);
		return wait;
	}
	
		
		

	// *********************WAIT_TILL_ELEMENT_INVISIBLE*************************************

			public  void waitTillElementInVisible(WebElement element) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				wait.until(ExpectedConditions.invisibilityOf(element));
			}
		// 
			
}





