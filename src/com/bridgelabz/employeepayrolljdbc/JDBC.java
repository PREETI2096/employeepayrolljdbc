package com.bridgelabz.employeepayrolljdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

public class JDBC {
	static Connection con = null;
public static void main(String[] args) {
	con = connected();
}	
	public static Connection connected() {
		String url = "jdbc:mysql://localhost:3306/employeepayrolljdbc?useSSL=false";
		String username = "root";
		String password = "Preeti@96";
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		listDrivers();
		try {
			System.out.println("Connecting to database: " + url);
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection is Successful - " + connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	public static void listDrivers() {
		Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
		while (driverList.hasMoreElements()) {
			Driver driverClass = (Driver) driverList.nextElement();
			System.out.println(" " + driverClass.getClass().getName());
		}
	}
	public static void retrieveData(Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("select * from employee_payroll where id=?");
		preparedStatement.setInt(1, 1);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("id"));
			System.out.println(resultSet.getString("name"));
		}
	}

	public static void updateData(Connection connection) throws SQLException {
		// Scanner sc = new Scanner(System.in);
		PreparedStatement preparedStatement = connection
				.prepareStatement("update employee_payroll set salary = ? where id =?;");
		System.out.println("Enter salary to be updated: ");
		// double salary = sc.nextDouble();
		System.out.println("Enter at which id you want to update salary: ");
		// int id = sc.nextInt();
		preparedStatement.setDouble(1, 300000.00);
		preparedStatement.setInt(2, 2);
		preparedStatement.executeUpdate();
		System.out.println("Updated Successfully.....!!!");
	}

}
