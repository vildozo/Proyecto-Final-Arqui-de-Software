package Presenter;

import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.change.ChangeMemberTransaction;

public class AffiliationPresenter {
	
	private static Repository repository = new MemoryRepository();
	
	public static void createUnionAffiliation(String employeeId, String memberId, String amount) {
		Transaction newMember = new ChangeMemberTransaction(Integer.parseInt(employeeId), Integer.parseInt(memberId), Double.parseDouble(amount));
		newMember.execute(repository);
	}
	
}
