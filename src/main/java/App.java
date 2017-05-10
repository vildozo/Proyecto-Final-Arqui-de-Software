import static spark.Spark.*;

import java.util.HashMap;

import Presenter.EmployeePresenter;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


public class App {
	public static void main(String[] args) {
		HashMap<String, Object> model = new HashMap<String, Object>();
		
		get("/", (request, response) -> "Index");
		
		get("/register_employee", (request, response) -> {
			return new ModelAndView(model, "templates/register_employee.vtl");
		}, new VelocityTemplateEngine());
		
		post("/create_employee", (request, response) -> {
			EmployeePresenter.createEmployee(request.queryParams("employeeId"),
					request.queryParams("name"),request.queryParams("address"),
					request.queryParams("paymentClassification"),request.queryParams("amount"),
					request.queryParams("commission"));
			response.redirect("/");
			return null;
		});
		
		get("/search_employee", (request, response) -> {
			return new ModelAndView(model, "templates/search_employee.vtl");
		}, new VelocityTemplateEngine());
		
		post("/show_employee", (request, response) -> {
			Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(Integer.parseInt(request.queryParams("employeeId")));
			model.put("employee", employee);
			return new ModelAndView(model, "templates/show_employee.vtl");
		}, new VelocityTemplateEngine());
	}
}
