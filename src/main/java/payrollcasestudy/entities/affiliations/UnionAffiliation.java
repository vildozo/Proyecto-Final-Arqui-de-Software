package payrollcasestudy.entities.affiliations;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import payrollcasestudy.entities.ServiceCharge;

public class UnionAffiliation {
	
	private int memberId;
	private double dues;
	Map<Calendar, ServiceCharge> serviceCharges = new HashMap<Calendar, ServiceCharge>();
	public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(0, 0);

	public UnionAffiliation(int memberId, double dues) {
		this.memberId = memberId;
		this.dues = dues;
	}

	public ServiceCharge getServiceCharge(Calendar date) {
		return serviceCharges.get(date);
	}

	public void addServiceCharge(Calendar date, double amount) {
		this.serviceCharges.put(date, new ServiceCharge(date, amount));
	}
}
