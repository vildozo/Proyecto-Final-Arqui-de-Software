package payrollcasestudy.boundaries;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import payrollcasestudy.entities.Employee;

public class MemoryRepository implements Repository{

	public static MemoryRepository globalPayrollDatabase = new MemoryRepository();
	public Map<Integer, Employee> unionMembers = new HashMap<Integer, Employee>();
	private Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
	
	@Override
	public Employee getEmployee(int employeeId) {
		return employees.get(employeeId);
	}

	@Override
	public void addEmployee(int employeeId, Employee employee) {
		employees.put(employeeId, employee);
	}

	@Override
	public void clear() {
		employees.clear();
        unionMembers.clear();
	}

	@Override
	public void deleteEmployee(int employeeId) {
		employees.put(employeeId, null);
	}

	@Override
	public Employee getUnionMember(int memberId) {
		return unionMembers.get(memberId);
	}

	@Override
	public void addUnionMember(int memberId, Employee employee) {
		unionMembers.put(memberId, employee);
	}

	@Override
	public void deleteUnionMember(int memberId) {
		unionMembers.remove(memberId);
	}

	@Override
	public Set<Integer> getAllEmployeeIds() {
		return employees.keySet();
	}

}
