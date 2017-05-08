import static spark.Spark.*;

import java.util.HashMap;

import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddSalariedEmployeeTransaction;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class App {
	public static void main(String[] args) {
		get("/", (request, response) -> hola());
		
		
		get("/register_employee", (request, response) -> {
			HashMap model = new HashMap();
			return new ModelAndView(model, "templates/register_employee.vtl");
		}, new VelocityTemplateEngine());
		
		
		get("/registered_employee", (request, response) ->{
			
			HashMap model = new HashMap();
			String employee_CI = request.queryParams("employee_CI");
			String employee_Name = request.queryParams("employee_Name");
			String address = request.queryParams("address");
			String payment = request.queryParams("payment");
			String hourly_Rate= request.queryParams("hourly_Rate");
			String Salary= request.queryParams("Salary");
			String Salary_Commissiones = request.queryParams("Salary_Commissiones");
			String comision_percent=request.queryParams("comision_percent");
			
		
			model.put("employee_CI",employee_CI);
			model.put("employee_Name", employee_Name);
			model.put("address", address);
			model.put("payment", payment);
			model.put("hourly_Rate", hourly_Rate);
			model.put("Salary", Salary);
			model.put("Salary_Commissiones", Salary_Commissiones);
			model.put("comision_percent", comision_percent);
	

			return new ModelAndView(model, "templates/registered_employee.vtl");
		}, new VelocityTemplateEngine());
			

		
		
		
	
		

	}

	
	
	
	
	private static String hola() {
		return "<html>"
				+ "<body>"
				+ "<form method='post' action='/hola'>" 
				+ "<label>Nombre:</label>"
				+ "<input type='text' name='nombre_saludo'>"
				+ "<input type='submit' value='Saluda'"
				+ "</body>"
				+ "</html>";
	}
	
	private static String MostrarEmpleados(){
		//queryParams("employee_CI"),request.queryParams("employee_Name"),request.queryParams("address")
		return "<html>"
				+ "<body>"
				+ "</body>"
				+ "</html>";
	}
}
