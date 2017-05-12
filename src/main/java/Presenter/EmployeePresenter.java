package Presenter;


import java.util.ArrayList;


import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeePresenter {
	
	private static Transaction addEmployeeTransaction;
	public static ArrayList <Employee> employeeList = new ArrayList<Employee> ();    
	
	public static void createEmployee(String employeeId, String name, String address, String paymentClassification, String amount, String commission) {
		if(paymentClassification.equals("hourly"))
			addEmployeeTransaction = new AddHourlyEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount));
		if(paymentClassification.equals("commissioned"))
			addEmployeeTransaction = new AddCommissionedEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount), Double.parseDouble(commission));
		if(paymentClassification.equals("salaried"))
			addEmployeeTransaction = new AddSalariedEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount));
		addEmployeeTransaction.execute();
		employeeList.add(new Employee(Integer.parseInt(employeeId), name, address));
	}
	
	public static ArrayList<Employee> getListOfEmployees(){
		return employeeList;
	}
}