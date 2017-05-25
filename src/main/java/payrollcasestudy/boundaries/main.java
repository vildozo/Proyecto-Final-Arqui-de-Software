package payrollcasestudy.boundaries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) {
		MyDataAccess connexion = new MyDataAccess();
		String query;
		
		ResultSet resultado;
		String names;
		
		resultado=connexion.getQuery("select * from employee");
		try {
			while(resultado.next()){
				names = resultado.getString("name");
				System.out.println("name:"+names);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("---------------------------------");
		query="INSERT INTO `payroll`.`employee` (`employeeId`, `name`, `address`) VALUES ('33', 'aaa', 'america');";
		connexion.setQuery(query);
		
	}
    

}
