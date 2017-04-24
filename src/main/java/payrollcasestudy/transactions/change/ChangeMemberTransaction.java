package payrollcasestudy.transactions.change;

import payrollcasestudy.transactions.Transaction;

public class ChangeMemberTransaction{
	int employeeId;
	int memberId; 
	double weeklyUnionDues;
	
	
	public ChangeMemberTransaction(int employeeId, int memberId, double weeklyUnionDues) {
		this.employeeId = employeeId;
		this.memberId = memberId;
		this.weeklyUnionDues = weeklyUnionDues;
	}

	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
	

}
