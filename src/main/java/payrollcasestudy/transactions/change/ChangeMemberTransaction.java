package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

public class ChangeMemberTransaction extends ChangeEmployeeTransaction{

	private int memberId;
	private double amount;

	public ChangeMemberTransaction(int employeeId, int memberId, double amount) {
		super(employeeId);
		this.memberId = memberId;
		this.amount = amount;
	}

	@Override
	public void changeEmployee(Employee employee) {
		employee.setUnionAffiliation(new UnionAffiliation(memberId, amount));
		database.addUnionMember(memberId, employee);
	}

}
