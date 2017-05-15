import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import Presenter.EmployeePresenter;
import Presenter.PayrollPresenter;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;
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
			model.put("template", "templates/register_employee.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/create_employee", (request, response) -> {
			EmployeePresenter.createEmployee(request.queryParams("employeeId"),
					request.queryParams("name"),request.queryParams("address"),
					request.queryParams("paymentClassification"),request.queryParams("amount"),
					request.queryParams("commission"));
			response.redirect("/show_all_employees");
			return new ModelAndView(model, "templates/show_all_employees.vtl");
		}, new VelocityTemplateEngine());
		
		get("/search_employee", (request, response) -> {
			model.put("template", "templates/search_employee.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/show_employee", (request, response) -> {
			Employee employee = PayrollDatabase.globalPayrollDatabase.getEmployee(Integer.parseInt(request.queryParams("employeeId")));
			String classificacionEmpleado;
			classificacionEmpleado=employee.getPaymentClassification().tipoDeClassificacion();
			model.put("employee", employee);
			
			model.put("template", "templates/show_employee"+classificacionEmpleado+".vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/show_all_employees", (request, response) -> {

			ArrayList <Employee> employees =  EmployeePresenter.employeeList;
			model.put("employees", employees);

			model.put("employees", EmployeePresenter.showAllEmployees());
			model.put("template", "templates/show_all_employees.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		
		get("/add_sales_receipt", (request, response) -> {
			String employee_Id=(request.queryParams("employee_Id"));
			model.put("employee_Id", employee_Id);
			model.put("template", "templates/add_sales_receipt.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
//		private Calendar date;
//	    private double amount;
//	    private int employeeId;
		
		post("/create_sales_receipts", (request, response) -> {
			
			PayrollPresenter.createReceiptTransaction(request.queryParams("year"), request.queryParams("month"), request.queryParams("day"), request.queryParams("amount"), request.queryParams("employee_Id"));
			response.redirect("/show_all_employees");
			return new ModelAndView(model, "templates/show_all_employees.vtl");
		}, new VelocityTemplateEngine());
		
		
		get("/add_time_card", (request, response) -> {
			String employee_Id=(request.queryParams("employee_Id"));
			model.put("employee_Id", employee_Id);
			model.put("template", "templates/add_time_card.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/create_time_card", (request, response) -> {
			PayrollPresenter.createHourlyReceipt(request.queryParams("year"), request.queryParams("month"), request.queryParams("day"), request.queryParams("amount"), request.queryParams("employee_Id"));
			response.redirect("/show_all_employees");
			return new ModelAndView(model, "templates/create_time_card.vtl");
		}, new VelocityTemplateEngine());
		
		get("/show_time_cards", (request, response) -> {
			int employee_Id=Integer.parseInt(request.queryParams("employee_Id"));
			ArrayList <AddTimeCardTransaction> employeeTimeCardList = new ArrayList<AddTimeCardTransaction> (); 
			
			employeeTimeCardList =PayrollPresenter.showTimeCard(employee_Id);
			model.put("employeeTimeCardList", employeeTimeCardList);
		
			model.put("template", "templates/show_time_cards.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		
		
	}
}
