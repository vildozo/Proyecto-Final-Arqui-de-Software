package Presenter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;

public class SalesReceiptPresenter {
	
	private static Repository repository = new MemoryRepository();
	private static Transaction salesReceiptTransaction;
	private static ArrayList <AddSalesReceiptTransaction> salesReceiptList = new ArrayList<AddSalesReceiptTransaction> (); 

	public static void createSalesReceipt(String date, String amount, String employeeId) throws ParseException{
		Date dateParsed = FormatDate.stringToDate(date);
		Calendar calendar = FormatDate.dateToCalendar(dateParsed);
	
		salesReceiptTransaction =  new AddSalesReceiptTransaction(calendar, Double.parseDouble(amount), Integer.parseInt(employeeId));
		salesReceiptTransaction.execute(repository);
		salesReceiptList.add(new AddSalesReceiptTransaction(calendar, Double.parseDouble(amount), Integer.parseInt(employeeId)));
	}

	public static ArrayList <AddSalesReceiptTransaction> showSalesReceipt(int employeeId){
		ArrayList <AddSalesReceiptTransaction> selectedSalesReceiptList = new ArrayList<AddSalesReceiptTransaction> (); 
		for (AddSalesReceiptTransaction salesReceiptTransaction : salesReceiptList) {
			if (salesReceiptTransaction.getEmployeeId() == employeeId){
				selectedSalesReceiptList.add(salesReceiptTransaction);
			}
		}
		return selectedSalesReceiptList;
	}

}
