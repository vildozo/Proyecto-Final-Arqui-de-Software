import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;

import Presenter.EmployeePresenter;
import Presenter.PayrollPresenter;
import payrollcasestudy.boundaries.PayrollDatabase;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.add.AddTimeCardTransaction;
import payrollcasestudy.transactions.add.AddSalesReceiptTransaction;
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
			String classificacionEmpleado = employee.getPaymentClassification().tipoDeClassificacion();
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

		
		get("/add_sales_receipt", (request, response) -> {
			String employeeId = (request.queryParams("employeeId"));
			model.put("employeeId", employeeId);
			model.put("template", "templates/payment/add_sales_receipt.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/create_sales_receipts", (request, response) -> {
			PayrollPresenter.createSalesReceipt(request.queryParams("date"),
					request.queryParams("amount"), request.queryParams("employeeId"));
			response.redirect("/show_all_employees");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/show_sales_receipt", (request, response) -> {
			int employeeId = Integer.parseInt(request.queryParams("employeeId"));
			ArrayList <AddSalesReceiptTransaction> employeeSalesReceiptList = new ArrayList<AddSalesReceiptTransaction> (); 
			
			employeeSalesReceiptList = PayrollPresenter.showSalesReceipt(employeeId);
			model.put("employeeSalesReceiptList", employeeSalesReceiptList);
		
			model.put("template", "templates/payment/show_sales_receipts.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/add_time_card", (request, response) -> {
			String employeeId = (request.queryParams("employeeId"));
			model.put("employeeId", employeeId);
			model.put("template", "templates/payment/add_time_card.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		post("/create_time_card", (request, response) -> {
			PayrollPresenter.createTimeCard(request.queryParams("date"), 
					request.queryParams("hours"), request.queryParams("employeeId"));
			response.redirect("/show_all_employees");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/show_time_cards", (request, response) -> {
			int employeeId = Integer.parseInt(request.queryParams("employeeId"));
			ArrayList <AddTimeCardTransaction> employeeTimeCardList = new ArrayList<AddTimeCardTransaction> (); 
			
			employeeTimeCardList = PayrollPresenter.showTimeCard(employeeId);
			model.put("employeeTimeCardList", employeeTimeCardList);
		
			model.put("template", "templates/payment/show_time_cards.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		
		get("/employee_Affiliation", (request, response) -> {
			model.put("template", "templates/payment/union_affiliation.vtl");
			return new ModelAndView(model, layout);
		}, new VelocityTemplateEngine());
		

	}
}
