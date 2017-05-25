package Presenter;


import java.util.ArrayList;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.boundaries.jdbcRepository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class EmployeePresenter {
	
	private static Repository repository = new jdbcRepository();
	private static Transaction addEmployeeTransaction;
	public static ArrayList <Employee> employeeList = repository.loadEmployees();
//	public static ArrayList <Employee> employeeList = new ArrayList<Employee> ();
	
	
	public static void createEmployee(String employeeId, String name, String address, String paymentClassification, String amount, String commission) {
		if(paymentClassification.equals("hourly"))
			addEmployeeTransaction = new AddHourlyEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount));
		if(paymentClassification.equals("commissioned"))
			addEmployeeTransaction = new AddCommissionedEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount), Double.parseDouble(commission));
		if(paymentClassification.equals("salaried"))
			addEmployeeTransaction = new AddSalariedEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount));
		addEmployeeTransaction.execute(repository);
		employeeList.add(new Employee(Integer.parseInt(employeeId), name, address));
	}
	
	public static ArrayList<Employee> getListOfEmployees(){
		return employeeList;
	}
	
	public static Employee getEmployeeFromMemory(int employeeId) {
		return repository.getEmployee(employeeId);
	}
}