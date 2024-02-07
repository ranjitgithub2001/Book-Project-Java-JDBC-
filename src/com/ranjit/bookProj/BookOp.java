package com.ranjit.bookProj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BookOp {
	static Connection con=ConnectionClass.createConnection();

	static Scanner sc=new Scanner(System.in);
	public static void addBook(int bId, String bookName, String authorName, double price, double rating) {
		try {
			PreparedStatement pstmt=con.prepareStatement("Insert into books values(?,?,?,?,?);");
			pstmt.setInt(1, bId);
			pstmt.setString(2, bookName);
			pstmt.setString(3, authorName);
			pstmt.setDouble(4, price);
			pstmt.setDouble(5, rating);
			
			pstmt.executeUpdate();
			System.out.println("Book with ID: "+bId+" Added to DataBase");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean display() {
//		Connection con=ConnectionClass.createConnection();
		boolean flag= false;
		try {
			Statement stmt=con.createStatement();
			ResultSet rSet=stmt.executeQuery("select * from books;");
			
			while(rSet.next()) {
				System.out.println("****************************************");
				System.out.println("Book ID: "+rSet.getInt(1));
				System.out.println("Book Name: "+rSet.getString(2));
				System.out.println("Author Name: "+rSet.getString(3));
				System.out.println("Price: "+rSet.getDouble(4));
				System.out.println("Rating: "+rSet.getDouble(5));
				System.out.println("****************************************");
				flag=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void deletData(int id) {	
//		Connection con=ConnectionClass.createConnection();
		try {
			PreparedStatement pstmt=con.prepareStatement("delete from books where bid=?");
			pstmt.setInt(1, id);
			int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Record with ID " + id + " deleted successfully.");
	        } else {
	            System.out.println("No record found with ID " + id + ". Nothing deleted.");
	        }
	        pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void maxRating() {
		
//		Connection con=ConnectionClass.createConnection();
		try {
			Statement stmt=con.createStatement();
			ResultSet rSet=stmt.executeQuery("select * from books order by rating desc limit 1;");
			while(rSet.next()) {
				System.out.println("****************************************");
				System.out.println("Book ID: "+rSet.getInt(1));
				System.out.println("Book Name: "+rSet.getString(2));
				System.out.println("Author Name: "+rSet.getString(3));
				System.out.println("Price: "+rSet.getDouble(4));
				System.out.println("Rating: "+rSet.getDouble(5));
				System.out.println("****************************************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void maxPrice() {
//		Connection con=ConnectionClass.createConnection();
		try {
			Statement stmt=con.createStatement();
			ResultSet rSet=stmt.executeQuery("select * from books order by price desc limit 1;");
			while(rSet.next()) {
				System.out.println("****************************************");
				System.out.println("Book ID: "+rSet.getInt(1));
				System.out.println("Book Name: "+rSet.getString(2));
				System.out.println("Author Name: "+rSet.getString(3));
				System.out.println("Price: "+rSet.getDouble(4));
				System.out.println("Rating: "+rSet.getDouble(5));
				System.out.println("****************************************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void top3Books(int n) {
		
//			Connection con=ConnectionClass.createConnection();
			try {
				Statement stmt=con.createStatement();
				ResultSet rSet = null;
				if(n==1) {
					rSet=stmt.executeQuery("select * from books order by price desc limit 3;");
				}else if(n==2){
					rSet=stmt.executeQuery("select * from books order by rating desc limit 3;");
				}else {
					System.out.println("Invalid Choice");
					return;
				}
				while(rSet.next()) {
					System.out.println("****************************************");
					System.out.println("Book ID: "+rSet.getInt(1));
					System.out.println("Book Name: "+rSet.getString(2));
					System.out.println("Author Name: "+rSet.getString(3));
					System.out.println("Price: "+rSet.getDouble(4));
					System.out.println("Rating: "+rSet.getDouble(5));
					System.out.println("****************************************");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static int searchBook(int id) {
//		Connection con=ConnectionClass.createConnection();
		int flag=0;
		try {
			PreparedStatement pstmt = con.prepareStatement("select * from books where bid=?;");
			pstmt.setInt(1, id);
			ResultSet rSet=pstmt.executeQuery();
			
			while(rSet.next()) {
				System.out.println("****************************************");
				System.out.println("Book ID: "+rSet.getInt(1));
				System.out.println("Book Name: "+rSet.getString(2));
				System.out.println("Author Name: "+rSet.getString(3));
				System.out.println("Price: "+rSet.getDouble(4));
				System.out.println("Rating: "+rSet.getDouble(5));
				System.out.println("****************************************");
				flag=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag==0) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public static void updateBook(int id) {
//		Connection con=ConnectionClass.createConnection();
		int rowsAffected=0;
		System.out.println("What do you want to update: 1.Price 2.Rating: ");
		int n=sc.nextInt();
		if(n==1) {
			System.out.println("Enter new Price: ");
			double p=sc.nextDouble();
			try {
				PreparedStatement pstmt=con.prepareStatement("update books set price=? where bid=?;");
				pstmt.setDouble(1, p);
				pstmt.setInt(2, id);
				rowsAffected=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(n==2){
			System.out.println("Enter new Rating: ");
			double r=sc.nextDouble();
			try {
				PreparedStatement pstmt= con.prepareStatement("update books set rating=? where bid=?;");
				pstmt.setDouble(1, r);
				pstmt.setInt(2, id);
				rowsAffected=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Invalid Choise");
		}
		System.out.println("Rows Affected: "+rowsAffected);
	}
	
	public static void deleteByName() {
//		Connection con=ConnectionClass.createConnection();
		
		System.out.println("Enter name of Book: ");
		String bn=sc.next();
		try {
			PreparedStatement pstmt=con.prepareStatement("Select bookname from books where bookName=?");
			pstmt.setString(1, bn);
			ResultSet rSet=pstmt.executeQuery();
			while(rSet.next()) {
				PreparedStatement dpstmt=con.prepareStatement("delete from books where bookName=?");
				dpstmt.setString(1, bn);
				int deleteCount=dpstmt.executeUpdate();
				if(deleteCount==0) {
					System.out.println("Book with name: "+bn+" not found!!");
				}else {
					System.out.println("Book with name: "+bn+" deleted successfully!!");
					dpstmt.close();
				}
			}
			pstmt.close();
			rSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}