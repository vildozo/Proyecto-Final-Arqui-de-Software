package payrollcasestudy.boundaries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.CommissionedPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;

public class jdbcRepository implements Repository{
	MyDataAccess connection = new MyDataAccess();
	public Map<Integer, Employee> unionMembers = new HashMap<Integer, Employee>();
	private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
	public static ArrayList <Employee> employeeList = new ArrayList<Employee> ();
	
	ResultSet resultado;
	int employee_Id;
	String name;
	String address;
	
	String query;
	
	public jdbcRepository(){
		Employee employee;
		query="SELECT * FROM employee ;";
		resultado=connection.getQuery(query);
		try {
			while(resultado.next()){
				employee_Id= Integer.parseInt(resultado.getString("employeeId"));
				name = resultado.getString("name");
				address= resultado.getString("address");
				employee = new Employee( employee_Id, name, address);
				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public Employee getEmployee(int employeeId) {
		return employees.get(employeeId);
	}

	@Override
	public void addEmployee(int employeeId, Employee employee) {
		String name = employee.getName();
		String address = employee.getAddress();
		double amount=0;
		double commision=0;
		String query;
		employees.put(employeeId, employee);
		String tipo = employee.getPaymentClassification().tipoDeClassificacion();
		if (tipo == "hourly"){
			HourlyPaymentClassification hourlyClassification =  (HourlyPaymentClassification) employee.getPaymentClassification();
			amount=hourlyClassification.getHourlyRate();
		}
		if (tipo == "salaried"){
			SalariedClassification classification =  (SalariedClassification) employee.getPaymentClassification();
			amount=classification.getSalary();
		}
		if (tipo == "commissioned"){
			CommissionedPaymentClassification classification =  (CommissionedPaymentClassification) employee.getPaymentClassification();
			amount=classification.getMonthlySalary();
			commision=classification.getCommissionRate();
		}
		query=employeeId+",'"+name+"','"+address+"','"+tipo+"','"+amount+"','"+commision;
		
		query="INSERT INTO `payroll`.`employee` (`employeeId`, `name`, `address`, `paymentClassification`,`amount`, `commission`) VALUES (" + query + "');";
		//    "INSERT INTO `payroll`.`employee` (`employeeId`, `name`, `address`, `paymentClassification`, `amount`, `commission`) VALUES ('78', 'Carlos', 'Mesa', 'hourly', '88', '0');";
		
		System.out.println("***************************************************");
		System.out.println(query);
		connection.setQuery(query);
		
		
	}

	@Override
	public void clear() {
		employees.clear();
        unionMembers.clear();
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employees.put(employeeId, null);
		query="DELETE FROM `payroll`.`employee` WHERE employeeId='"+employeeId+"');";
		connection.setQuery(query);
	}

	@Override
	public Employee getUnionMember(int memberId) {
	return null;	
	}

	@Override
	public void addUnionMember(int memberId, Employee employee) {
		
	}

	@Override
	public void deleteUnionMember(int memberId) {
		
	}

	@Override
	public Set<Integer> getAllEmployeeIds() {
		return employees.keySet();
	}
	
	public ArrayList <Employee> loadEmployees(){
		Employee employee;
		query="SELECT * FROM employee ;";
		resultado=connection.getQuery(query);
		try {
			while(resultado.next()){
				employee_Id= Integer.parseInt(resultado.getString("employeeId"));
				name = resultado.getString("name");
				address= resultado.getString("address");
				employee = new Employee( employee_Id, name, address);
				employees.put(employee_Id, employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;
	}
}
