package Presenter;

import static java.util.Calendar.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
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
	
	
	public static void createHourlyReceipt(String year, String month, String day, String ammount , String employee_Id){
		Calendar date = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
		Transaction timeCardTransaction = new AddTimeCardTransaction(date, Double.parseDouble(ammount), Integer.parseInt(employee_Id));
		timeCardTransaction.execute();
		timeCardList.add(new AddTimeCardTransaction(date, Double.parseDouble(ammount), Integer.parseInt(employee_Id)));
	}
	
	public static ArrayList <AddTimeCardTransaction> showTimeCard(int employee_Id){
		ArrayList <AddTimeCardTransaction> selectedTimeCardList = new ArrayList<AddTimeCardTransaction> (); 
		selectedTimeCardList.clear();
		int idOfEmployee;
		for (AddTimeCardTransaction timeCardTransaction : timeCardList) {
			//idOfEmployee=timeCardTransaction.getEmployeeId();
			if (timeCardTransaction.getEmployeeId()==employee_Id){
				selectedTimeCardList.add(timeCardTransaction);
				System.out.println(selectedTimeCardList.size());
			}
		}
		return selectedTimeCardList;
	}

}


	