package ch.m295;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.validation.Valid;

public class ArbeitgeberDAO implements IArbeitgeberDAO {
	
	private Logger logger = LogManager.getLogger(ArbeitgeberService.class);

	@Override
	public List<Arbeitgeber> select() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {}
			List<Arbeitgeber> arbeitgeber = new ArrayList<>();
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
				
				try (Statement stmt = con.createStatement()) {
					String tableSql = "SELECT * from arbeitgeber";
							 try (ResultSet resultSet = stmt.executeQuery(tableSql)) {
								 System.out.println("SELECT * from arbeitgeber");
								 
								 while(resultSet.next()) {
									Arbeitgeber a = new Arbeitgeber();
									a.setName(resultSet.getString("name"));
									if(resultSet.getDate("datum") != null) {
										a.setDate(resultSet.getDate("datum").toLocalDate());
									}
									a.setAktiv(resultSet.getBoolean("aktiv"));
									
									arbeitgeber.add(a);
								 }
								 stmt.close();
								 con.close();
							 }
							} catch (Exception e) {
					            logger.log(Level.ERROR, e.getMessage());
					            con.close();
					        }	
			} catch (Exception e) {
				logger.log(Level.ERROR, e.getMessage());
				return null;
			}
			return arbeitgeber;
	}
	
	@Override
	public Arbeitgeber select(@Valid Arbeitgeber a) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {}
			try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
				String tableSql = "SELECT * from arbeitgeber WHERE name = ?;";
				try (PreparedStatement pstmt = con.prepareStatement(tableSql)) {
					
							pstmt.setString(1, a.getName());
								
							try (ResultSet resultSet = pstmt.executeQuery()) {
								Arbeitgeber b = new Arbeitgeber();
								while(resultSet.next()) {
								b.setName(resultSet.getString("name"));
								if(resultSet.getDate("datum") != null) {
									a.setDate(resultSet.getDate("datum").toLocalDate());
								}
								a.setAktiv(resultSet.getBoolean("aktiv"));
								}
								pstmt.close();
								con.close();
								return b;
							} catch (Exception e) {
								logger.log(Level.ERROR, e.getMessage());
								pstmt.close();
								con.close();
								return null;
					        }	
					} catch (Exception e) {
						logger.log(Level.ERROR, e.getMessage());
						con.close();
						return null;
					}
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
			return null;
		}
	}

	@Override
	public int insert(@Valid Arbeitgeber a) {
		int updateCount;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {}
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
			String tableSql = "INSERT INTO arbeitgeber (name, datum, aktiv) VALUES (?, ?, ?);";
			try (PreparedStatement pstmt = con.prepareStatement(tableSql)) {
				
			if(a.getName() != null) {
					 pstmt.setString(1, a.getName());
					 if(a.getDate() != null) {
					 pstmt.setDate(2, java.sql.Date.valueOf(a.getDate()));
					 } else {
						 pstmt.setDate(2, null);
					 }
					 pstmt.setBoolean(3, a.isAktiv());
						 
						 pstmt.execute();
						 updateCount = pstmt.getUpdateCount();
						 pstmt.close();
						 con.close();
						 return updateCount;
				}
				else {
					throw new IllegalArgumentException("Name is null");
				}
						} catch (Exception e) {
							logger.log(Level.ERROR, e.getMessage());
				            con.close();
				            return 0;
				        }	
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
			return 0;
		}
	}

	@Override
	public int update(@Valid Arbeitgeber a,@Valid Arbeitgeber b) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {}
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
			int count;
			String tableSql = "UPDATE arbeitgeber SET name = ?, datum = ?, aktiv = ? WHERE name like ?;";
			try (PreparedStatement pstmt = con.prepareStatement(tableSql)) {
				pstmt.setString(1, b.getName());
				if(a.getDate() != null) {
					 pstmt.setDate(2, java.sql.Date.valueOf(b.getDate()));
					 } else {
						 pstmt.setDate(2, null);
					 }
					 pstmt.setBoolean(3, b.isAktiv());
			
				pstmt.setString(4, a.getName());
				
				pstmt.execute();
				count = pstmt.getUpdateCount();
				pstmt.close();
				con.close();
				return count;
			} catch (Exception e) {
				logger.log(Level.ERROR, e.getMessage());
				con.close();
				return 0;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
			return 0;
		}
	}

	@Override
	public int delete(String name) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {}
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
			int count;
			String tableSql = "DELETE from arbeitgeber WHERE name like ?;";
			try (PreparedStatement pstmt = con.prepareStatement(tableSql)) {
				pstmt.setString(1, name);
				pstmt.execute();
				count = pstmt.getUpdateCount();
				pstmt.close();
				con.close();
				return count;
			} catch (Exception e) {
				logger.log(Level.ERROR, e.getMessage());
				con.close();
				return 0;
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, e.getMessage());
			return 0;
		}
	}
}
