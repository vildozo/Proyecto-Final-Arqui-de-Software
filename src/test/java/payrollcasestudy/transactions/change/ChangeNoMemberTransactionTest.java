package payrollcasestudy.transactions.change;

import org.junit.Test;
import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.boundaries.Repository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.entities.affiliations.UnionAffiliation;
import payrollcasestudy.transactions.Transaction;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ChangeNoMemberTransactionTest {
	
	private static final Repository repository = new MemoryRepository();

    @Test
    public void testChangeMemberTransaction() throws Exception {

        int employeeId = 2;
        int memberId = 7734;
        Transaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        UnionAffiliation unionAffiliation = new UnionAffiliation(memberId,92.1);
        employee.setUnionAffiliation(unionAffiliation);
        assertThat(employee.getUnionAffiliation(), is(unionAffiliation));

        repository.addUnionMember(memberId, employee);
        assertThat(repository.getUnionMember(memberId), is(employee));

        Transaction noMemberTransaction = new ChangeNoMemberTransaction(employeeId);
        noMemberTransaction.execute(repository);

        employee = repository.getEmployee(employeeId);
        assertThat(employee.getUnionAffiliation(), is(UnionAffiliation.NO_AFFILIATION));

        assertThat(repository.getUnionMember(memberId), is(nullValue()));
    }
}
