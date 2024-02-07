package com.ranjit.bookProj;

import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {

		Scanner sc=new Scanner(System.in);
		boolean flag=true;
		do {
			System.out.println("1.Add Books\n2.Display Books\n3.Delete Book\n4.Get Maximum\n5.Get Top 3\n6.Update\n7.Exit");
			int ch=sc.nextInt();
			switch(ch) {
				case 1:{
					System.out.print("Enter the book id:");
					int bid=sc.nextInt();
					System.out.print("Enter the book name:");
					sc.nextLine();
					String bname=sc.nextLine();
					System.out.print("Enter the author name:");
					String aname=sc.nextLine();
					System.out.print("Enter the price of the book:");
					double price=sc.nextDouble();
					System.out.print("Enter the rating of the book:");
					double rating=sc.nextDouble();
					BookOp.addBook(bid, bname, aname, price, rating);
					break;
				}
				case 2:{
					if(BookOp.display()) {
					}else {
						System.out.println("No Books in DataBase to Display!!");
					}
					break;
				}
				case 3:{
					System.out.println("1.Delete by ID\n2.Delete by Name");
					int n=sc.nextInt();
					if(n==1) {
						System.out.println("Enter the Book ID: ");
						int id=sc.nextInt();
						BookOp.deletData(id);
					}else if(n==2) {
						BookOp.deleteByName();
					}else {
						System.out.println("Invalid Choice");
					}
					break;
				}
				case 4:{
					System.out.println("1.Price\n2.Rating:");
					int n=sc.nextInt();
					if(n==1) {
						BookOp.maxPrice();
					}else if(n==2) {
						BookOp.maxRating();
					}else {
						System.out.println("Invalid Choice");
					}
					break;
				}
				case 5:{
					System.out.println("1.Price\n2.Rating:");
					int n=sc.nextInt();
					BookOp.top3Books(n);
					break;
				}
				case 6:{
					System.out.println("Enter the Book ID");
					int id=sc.nextInt();
					BookOp.updateBook(id);
					break;
				}
				case 7:{
					flag=false;
					BookOp.closeConnection();
					break;
				}
			}
		}while(flag);
		System.out.println("You have Exited the Program!!!");
	}

}
