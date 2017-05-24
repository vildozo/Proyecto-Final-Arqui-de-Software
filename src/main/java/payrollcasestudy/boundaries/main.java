package payrollcasestudy.boundaries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class main {

	public static void main(String[] args) {
		MyDataAccess connexion = new MyDataAccess();
		
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

	}

}
