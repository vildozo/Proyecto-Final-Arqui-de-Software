import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;

import Presenter.EmployeePresenter;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class App {
	public static void main(String[] args) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		String layout = "templates/layout.vtl";
		staticFileLocation("/public");
		
		get("/", (request, response) -> {
			model.put("template", "templates/main_page.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/register_employee", (request, response) -> {
			model.put("template", "templates/employee/register_employee.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/create_employee", (request, response) -> {
			EmployeePresenter.createEmployee(request.queryParams("employeeId"),
					request.queryParams("name"),request.queryParams("address"),
					request.queryParams("paymentClassification"),request.queryParams("amount"),
					request.queryParams("commission"));
			response.redirect("/show_all_employees");
			return new ModelAndView(model, "templates/employee/show_all_employees.vtl");
		}, new VelocityTemplateEngine());
		
		get("/search_employee", (request, response) -> {
			model.put("template", "templates/employee/search_employee.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/show_employee", (request, response) -> {
			Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(Integer.parseInt(request.queryParams("employeeId")));
			String classificacionEmpleado;
			classificacionEmpleado=employee.getPaymentClassification().tipoDeClassificacion();
			model.put("employee", employee);
			
			model.put("template", "templates/employee/show_employee"+classificacionEmpleado+".vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/show_all_employees", (request, response) -> {
			ArrayList <Employee> employees =  EmployeePresenter.employeeList;
			model.put("employees", employees);

			model.put("employees", EmployeePresenter.getListOfEmployees());
			model.put("template", "templates/employee/show_all_employees.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
	}
}
