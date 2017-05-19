package payrollcasestudy.transactions.add;

import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.TimeCard;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.PaymentClassification;
import payrollcasestudy.transactions.Transaction;

import java.util.Calendar;

public class AddTimeCardTransaction implements Transaction {

    private Calendar date;
    private double hours;
    private int employeeId;

    public AddTimeCardTransaction(Calendar date, double hours, int employeeId) {
        this.date = date;
        this.hours = hours;
        this.employeeId = employeeId;
    }

    public void execute() {
        Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(employeeId);
        if (employee != null){
            PaymentClassification paymentClassification = employee.getPaymentClassification();
            if (paymentClassification instanceof HourlyPaymentClassification){
                HourlyPaymentClassification classification = (HourlyPaymentClassification) paymentClassification;
                classification.addTimeCard(new TimeCard(date, hours));
            }
        }
    }
    
    public int getEmployeeId(){
    	return this.employeeId;
    }
    
    public double getHours(){
    	return this.hours;
    }
    
    public String getCalendar(){
    	int year = date.get(Calendar.YEAR);
    	int day = date.get(Calendar.DAY_OF_MONTH);
    	int month = date.get(Calendar.MONTH)+1;
    	return day + "-" + month + "-" + year;
    }
    
   
}
