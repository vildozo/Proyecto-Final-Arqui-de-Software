package Presenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class PayrollPresenter {
	private static Transaction transaction;
	private static PaydayTransaction payDayTransaction;
	
	
	public static ArrayList <AddSalesReceiptTransaction> salesReceiptList = new ArrayList<AddSalesReceiptTransaction> (); 
	public static ArrayList <AddTimeCardTransaction> timeCardList = new ArrayList<AddTimeCardTransaction> (); 

	
	public static void createReceiptTransaction(String year, String month, String day, String amount, String employeeId){
		Calendar date = new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		//transaction =  new AddSalesReceiptTransaction(date, Double.parseDouble(amount), Integer.parseInt(employeeId));
		//transaction.execute();
		//salesReceiptList.add(new AddSalesReceiptTransaction(date, Double.parseDouble(amount), Integer.parseInt(employeeId)));
	}
	
	public static void createHourlyReceipt(String date, String hours, String employee_Id) throws ParseException{
		Date dateParsed = stringToDate(date);
		Calendar calendar = dateToCalendar(dateParsed);
		
		Transaction timeCardTransaction = new AddTimeCardTransaction(calendar, Double.parseDouble(hours), Integer.parseInt(employee_Id));
		timeCardTransaction.execute();
		timeCardList.add(new AddTimeCardTransaction(calendar, Double.parseDouble(hours), Integer.parseInt(employee_Id)));
	}
	
	public static ArrayList <AddTimeCardTransaction> showTimeCard(int employee_Id){
		ArrayList <AddTimeCardTransaction> selectedTimeCardList = new ArrayList<AddTimeCardTransaction> (); 
		for (AddTimeCardTransaction timeCardTransaction : timeCardList) {
			if (timeCardTransaction.getEmployeeId()==employee_Id){
				selectedTimeCardList.add(timeCardTransaction);
				System.out.println(selectedTimeCardList.size());
			}
		}
		return selectedTimeCardList;
	}
	
	private static Date stringToDate(String date) throws ParseException {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		Date dateParsed = formatDate.parse(date);
		return dateParsed;
	}

	private static Calendar dateToCalendar(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, +1);
		return calendar;
	}
	

}


	