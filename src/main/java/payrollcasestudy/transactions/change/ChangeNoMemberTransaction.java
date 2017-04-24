package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;

public class ChangeNoMemberTransaction extends ChangeEmployeeTransaction {
	
	public ChangeNoMemberTransaction(int employeeId) {
		super(employeeId);
		// TODO Auto-generated constructor stub
	}

	int employeId;
	
//	public ChangeNoMemberTransaction(int employeeId) {
//		this.employeId = employeeId;
//	}

	

	@Override
	public void changeEmployee(Employee employee) {
		        int memberId = employee.getUnionAffiliation().getMemberId();
		        employee.setUnionAffiliation(null);
		        database.deleteUnionMember(memberId);
		   
	}
	
	
	

	

}
