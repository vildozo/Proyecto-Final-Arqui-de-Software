package Presenter;

import org.eclipse.jetty.server.Request;

import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddCommissionedEmployeeTransaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;

public class Presentador_empleado {
	
	private static Transaction addEmployeeTransaction;
	
	public static void createEmployee(String employeeId, String name, String address, String paymentClassification, String amount, String commission) {
		if(paymentClassification.equals("hourly"))
			addEmployeeTransaction = new AddHourlyEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount));
		if(paymentClassification.equals("commissioned"))
			addEmployeeTransaction = new AddCommissionedEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount), Double.parseDouble(commission));
		if(paymentClassification.equals("salaried"))
			addEmployeeTransaction = new AddSalariedEmployeeTransaction(Integer.parseInt(employeeId), name, address, Double.parseDouble(amount));
		addEmployeeTransaction.execute();
	}
}
		
		
//	public static String responder_registro( String employee_CI,String nombre,String address,String payment,String amount){
//		VelocityTemplateEngine vte= new VelocityTemplateEngine();
//		int ci;
//		PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
//		System.out.println("----------RESPONDIENDO---------");
//		ci= Integer.parseInt(employee_CI);
//		Employee employee= new Employee(ci,nombre,address);
//		//Double.parseDouble(aString)
//		if (payment=="hourly")
//			employee.setPaymentClassification(new HourlyPaymentClassification(Double.parseDouble(amount)));
//		if (payment=="commissioned")
//			employee.setPaymentClassification(null);
//		if (payment=="salaried")
//			employee.setPaymentClassification(new SalariedClassification(Double.parseDouble(amount)));
//		database.addEmployee(ci, employee);
//		vte.asignarDireccionTemplate("templates/empleado.vt");
//		vte.agregarContext("ci", Integer.toString(ci));
//		vte.agregarContext("nombre", nombre);
//		vte.agregarContext("address", address);
//		vte.agregarContext("payment", payment);
//		vte.agregarContext("amount", amount);
//		vte.unirAlTemplate();
//		return vte.show_template();//"El nuevo empleado tiene ci= "+ ci + " y su nombre es "+nombre+" y su direccion es "+ address;
//	}
