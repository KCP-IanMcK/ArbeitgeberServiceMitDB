package ch.m295;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {

	public static void main(String[] args) {
		
		Jdbc demo = new Jdbc();
		try {
			demo.run2();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static void run() throws Exception {
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
		
		try (Statement stmt = con.createStatement()) {
			String tableSql = "INSERT INTO arbeitgeber (name) VALUES ('UBS')";
					stmt.execute(tableSql);
					System.out.println("Table 'employees' created or already exists.");} catch (Exception e) {
			            System.err.println("An error occurred: " + e.getMessage());
			            throw e; // Re-throw the exception
			        }
		}
		
	}
	
	public static List<Arbeitgeber> run2() throws Exception {
		List<Arbeitgeber> arbeitgeber = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
			
			try (Statement stmt = con.createStatement()) {
				String tableSql = "SELECT * from arbeitgeber";
						 try (ResultSet resultSet = stmt.executeQuery(tableSql)) {
							 System.out.println("SELECT * from arbeitgeber");
							 
							 while(resultSet.next()) {
								 Arbeitgeber a = new Arbeitgeber();
							
								a.setName(resultSet.getString("name"));
								arbeitgeber.add(a);
								
							 }	
						 }
						} catch (Exception e) {
				            System.err.println("An error occurred: " + e.getMessage());
				            throw e; // Re-throw the exception
				        }	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(arbeitgeber.get(0).getName());
		return arbeitgeber;
	}
}
