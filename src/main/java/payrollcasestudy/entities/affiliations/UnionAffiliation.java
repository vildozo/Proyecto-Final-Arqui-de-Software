package payrollcasestudy.entities.affiliations;

public class UnionAffiliation {
	
	private int memberId;
	private double dues;
	public static final UnionAffiliation NO_AFFILIATION = new UnionAffiliation(-1, 0);

	public UnionAffiliation(int memberId, double dues) {
		this.memberId = memberId;
		this.dues = dues;
	}


}
