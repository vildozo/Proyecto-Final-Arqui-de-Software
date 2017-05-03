package Presenter;

import org.apache.velocity.app.VelocityEngine;

import payrollcasestudy.boundaries.PayrollDatabase;
import spark.template.velocity.VelocityTemplateEngine;
import payrollcasestudy.entities.Employee;

public class Presentador_empleado {
	public static String empleado() {
		
		VelocityTemplateEngine vte= new VelocityTemplateEngine();
		vte.asignarDireccionTemplate("src/main/java/Presenter/register_employee.vt");
		vte.unirAlTemplate();
		return vte.show_template();
		
	}
	
	public static String responder_registro( String employee_CI,String nombre,String address){
		VelocityTemplateEngine vte= new VelocityTemplateEngine();
		int ci;
		PayrollDatabase database = PayrollDatabase.globalPayrollDatabase;
		System.out.println("----------RESPONDIENDO---------");
		ci= Integer.parseInt(employee_CI);
		Employee employee= new Employee(ci,nombre,address);
		database.addEmployee(ci, employee);
		vte.asignarDireccionTemplate("src/main/java/Presenter/empleado.vt");
		vte.agregarContext("ci", Integer.toString(ci));
		vte.agregarContext("nombre", nombre);
		vte.agregarContext("address", address);
		vte.unirAlTemplate();
		return vte.show_template();//"El nuevo empleado tiene ci= "+ ci + " y su nombre es "+nombre+" y su direccion es "+ address;
	}
	
	

}
