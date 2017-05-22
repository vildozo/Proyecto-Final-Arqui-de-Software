package payrollcasestudy.transactions.change;

import org.junit.Rule;
import org.junit.Test;
import payrollcasestudy.DatabaseResource;
import payrollcasestudy.boundaries.MemoryRepository;
import payrollcasestudy.entities.Employee;
import payrollcasestudy.transactions.add.AddHourlyEmployeeTransaction;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ChangeNameTransactionTest {

    @Rule
    public DatabaseResource databaseResource = new DatabaseResource();
    private static final MemoryRepository repository = new MemoryRepository();

    @Test
    public void testChangeNameTransaction() throws Exception {
        int employeeId = 2;
        AddHourlyEmployeeTransaction addEmployeeTransaction =
                new AddHourlyEmployeeTransaction(employeeId, "Bill", "Home", 15.25);
        addEmployeeTransaction.execute(repository);

        ChangeNameTransaction changeNameTransaction = new ChangeNameTransaction(employeeId, "Bob");
        changeNameTransaction.execute(repository);

        Employee employee = repository.getEmployee(employeeId);
        assertThat(employee.getName(), is("Bob"));
    }
}
