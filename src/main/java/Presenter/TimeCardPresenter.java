package Presenter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;

public class TimeCardPresenter {
	private static Transaction timeCardTransaction;
	private static ArrayList <AddTimeCardTransaction> timeCardList = new ArrayList<AddTimeCardTransaction> (); 

	
	public static void createTimeCard(String date, String hours, String employeeId) throws ParseException{
		Date dateParsed = FormatDate.stringToDate(date);
		Calendar calendar = FormatDate.dateToCalendar(dateParsed);
		
		timeCardTransaction = new AddTimeCardTransaction(calendar, Double.parseDouble(hours), Integer.parseInt(employeeId));
		timeCardTransaction.execute();
		timeCardList.add(new AddTimeCardTransaction(calendar, Double.parseDouble(hours), Integer.parseInt(employeeId)));
	}
	
	public static ArrayList <AddTimeCardTransaction> showTimeCard(int employeeId){
		ArrayList <AddTimeCardTransaction> selectedTimeCardList = new ArrayList<AddTimeCardTransaction> (); 
		for (AddTimeCardTransaction timeCardTransaction : timeCardList) {
			if (timeCardTransaction.getEmployeeId() == employeeId){
				selectedTimeCardList.add(timeCardTransaction);
			}
		}
		return selectedTimeCardList;
	}
}


	