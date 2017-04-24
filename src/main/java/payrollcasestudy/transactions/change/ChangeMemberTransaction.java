package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

public class ChangeMemberTransaction extends ChangeEmployeeTransaction{
	int employeeId;
	int memberId; 
	double weeklyUnionDues;
	
	public ChangeMemberTransaction(int employeeId, int memberId, double weeklyUnionDues) {
		super(employeeId);
		this.memberId = memberId;
		this.weeklyUnionDues = weeklyUnionDues;
	}

	@Override
	public void changeEmployee(Employee employee) {
		employee.setUnionAffiliation(new UnionAffiliation(memberId, weeklyUnionDues));
	    database.addUnionMember(memberId, employee);
	}
}
