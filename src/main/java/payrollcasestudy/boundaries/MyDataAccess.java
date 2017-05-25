package payrollcasestudy.boundaries;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDataAccess {

	private String _usuario="root";
	private String _pwd= "root";
	private static String _bd="payroll";
	static String _url = "jdbc:mysql://localhost/"+_bd;
	private Connection conn = null;
	
	//Stablish connection
	public MyDataAccess() {
		
		try{
			Class.forName("com.mysql.jdbc.Connection");
			conn = (Connection)DriverManager.getConnection(_url, _usuario, _pwd);
			if(conn != null)
			{
				System.out.println("Conexi-n a base de datos "+_url+" . . . Ok");
			}
		}
		catch(SQLException ex)
		{
			System.out.println("Hubo un problema al intentar conecarse a la base de datos"+_url);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println(ex);
		}		
	}
	
	//SELECT
	public ResultSet getQuery(String _query)
	{
		Statement state = null;
		ResultSet resultado = null;
		try{
			state = (Statement) conn.createStatement();
			resultado = state.executeQuery(_query);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		
		}
		return resultado;
	}
	
	//INSERT,UPDATE, DELETE
	public void setQuery(String _query){
		System.out.println("ME ENCUENTRO EN SETQUERY");
		Statement state = null;
		
		try{			
			state=(Statement) conn.createStatement();
			System.out.println("ME ENCUENTRO EN TRY");
			state.execute(_query);

         } catch (SQLException e){
            e.printStackTrace();
            System.out.println("3333333333333333333333333333333333333333333333333333333333333333333333333");
       }
	}
	
	
}