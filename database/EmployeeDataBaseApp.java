package database;

import java.util.Scanner;

public class EmployeeDataBaseApp {
	
	Scanner in;
	private EmployeeCRUDClass employeeCRUDClass;
	private boolean working = true;
	
	
	
	
	public EmployeeDataBaseApp(Scanner in, EmployeeCRUDClass employeeCRUDClass) {
		super();
		this.in = in;
		this.employeeCRUDClass = employeeCRUDClass;
	}




	public void menu() {
		while(working) {
			System.out.println("Enter your option: "
					+ "\n1) Show every employee "
					+ "\n2) Add employee "
					+ "\n3) Find employee by id"
					+ "\n4) Find every employee in company"
					+ "\n5) Delete employee by id"
					+ "\n6) Close program");
			
			String answer = in.nextLine();
			
			try {
				Integer.parseInt(answer); 
			} catch(Exception exc) {
				System.out.println("Your answer is not a digit");
				continue;
			}
			
			if (answer.contentEquals("1")) {
				employeeCRUDClass.showEveryEmployeeInDB();
			} else if (answer.equals("2")) {
				employeeCRUDClass.createAndSaveEmployee();
			} else if (answer.equals("3")) {
				employeeCRUDClass.getEmployeeById();
			} else if (answer.equals("4")) {
				employeeCRUDClass.getEmployeesOfCompany();
			} else if (answer.equals("5")) {
				employeeCRUDClass.deleteEmployeeById();
			} else if (answer.equals("6")) {
				working = false;
			}
			
		}
		
	}
	
	

}
