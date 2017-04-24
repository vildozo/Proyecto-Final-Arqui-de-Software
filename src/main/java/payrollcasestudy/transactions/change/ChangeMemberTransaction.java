package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.Transaction;

public class ChangeMemberTransaction extends ChangeEmployeeTransaction{
	int employeeId;
	int memberId; 
	double weeklyUnionDues;
	
	
	public ChangeMemberTransaction(int employeeId, int memberId, double weeklyUnionDues) {
		super(employeeId);
		this.memberId = memberId;
		this.weeklyUnionDues = weeklyUnionDues;
	}

	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
	}
	

}
