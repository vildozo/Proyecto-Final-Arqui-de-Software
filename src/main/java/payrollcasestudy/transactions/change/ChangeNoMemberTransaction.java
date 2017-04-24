package payrollcasestudy.transactions.change;

import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;

public class ChangeNoMemberTransaction extends ChangeEmployeeTransaction {

	public ChangeNoMemberTransaction(int employeeId) {
		super(employeeId);
	}

	@Override
	public void changeEmployee(Employee employee) {
		int memberId = employee.getUnionAffiliation().getMemberId();
		employee.setUnionAffiliation(UnionAffiliation.NO_AFFILIATION);
		database.deleteUnionMember(memberId);
	}
}
