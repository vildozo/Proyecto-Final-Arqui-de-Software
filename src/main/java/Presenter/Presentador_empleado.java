package Presenter;

import org.apache.velocity.app.VelocityEngine;

import payrollcasestudy.boundaries.PayrollDatabase;
import spark.template.velocity.VelocityTemplateEngine;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.paymentclassifications.HourlyPaymentClassification;
import payrollcasestudy.entities.paymentclassifications.SalariedClassification;

public class Presentador_empleado {
	public static String empleado() {
		
		VelocityTemplateEngine vte= new VelocityTemplateEngine();
		vte.asignarDireccionTemplate("resources/templates/register_employee.vt");
		vte.unirAlTemplate();
		return vte.show_template();
		
	}
	
	public static String responder_registro( String employee_CI,String nombre,String address,String payment,String amount){
		VelocityTemplateEngine vte= new VelocityTemplateEngine();
		int ci;
		PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
		System.out.println("----------RESPONDIENDO---------");
		ci= Integer.parseInt(employee_CI);
		Employee employee= new Employee(ci,nombre,address);
		//Double.parseDouble(aString)
		if (payment=="hourly")
			employee.setPaymentClassification(new HourlyPaymentClassification(Double.parseDouble(amount)));
		if (payment=="commissioned")
			employee.setPaymentClassification(null);
		if (payment=="salaried")
			employee.setPaymentClassification(new SalariedClassification(Double.parseDouble(amount)));
		database.addEmployee(ci, employee);
		vte.asignarDireccionTemplate("templates/empleado.vt");
		vte.agregarContext("ci", Integer.toString(ci));
		vte.agregarContext("nombre", nombre);
		vte.agregarContext("address", address);
		vte.agregarContext("payment", payment);
		vte.agregarContext("amount", amount);
		vte.unirAlTemplate();
		return vte.show_template();//"El nuevo empleado tiene ci= "+ ci + " y su nombre es "+nombre+" y su direccion es "+ address;
	}
	
	

}
