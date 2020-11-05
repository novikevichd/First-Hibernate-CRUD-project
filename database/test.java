package database;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Employee;

public class test {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		EmployeeCRUDClass crudObject = new EmployeeCRUDClass(in);
		
		EmployeeDataBaseApp app = new EmployeeDataBaseApp(in, crudObject);
		
		app.menu();
		

	}

}
