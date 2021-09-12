package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)");  // '?' � o placeholder, o lugar que ser� preenchido
			
			st.setString(1, "Carl Purple");   // vai setar um novo valor para o '?' da posi��o 1, no caso uma string com o nome ap�s a virgula
			st.setString(2, "carl@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime())); // instancia uma data para o JDBC para colocar no objeto PreparedStatement
			st.setDouble(4, 3000.0);
			st.setInt(5, 4);
			
			// execute update ap�s alguma mudan�a no banco, o m�todo retorna um n�mero inteiro indicando quantas linhas foram alteradas
			int rowsAffected = st.executeUpdate();  
			
			System.out.println("Done! Rows Affected: " + rowsAffected);
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.CloseStatement(st);
			DB.closeConnection();
		}

	}

}
