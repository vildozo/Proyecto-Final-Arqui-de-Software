package Presenter;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.transactions.PaydayTransaction;
import payrollcasestudy.transactions.Transaction;

public class PaydayPresenter {
	
	private static Transaction payDay;
	private static Repository repository = new MemoryRepository();
	
	public static void createPayday(String date) throws ParseException {
		Date dateParsed = FormatDate.stringToDate(date);
		Calendar calendar = FormatDate.dateToCalendar(dateParsed);
		payDay = new PaydayTransaction(calendar);
		payDay.execute(repository);
	}
	
			
			
			
}
