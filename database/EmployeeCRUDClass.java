package database;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Employee;

public class EmployeeCRUDClass {
	
	private Scanner in;
	
	
	
	
	public EmployeeCRUDClass(Scanner in) {
		this.in = in;
	}



	SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Employee.class)
								.buildSessionFactory();
	
	
	
	public void createAndSaveEmployee() {

		System.out.println("Enter employee's name");
		String firstName = in.nextLine();
		
		System.out.println("Enter employee's last name");
		String lastName = in.nextLine();
		
		System.out.println("Enter employee's company");
		String company = in.nextLine();
		
		Employee tempEmployee = new Employee(firstName, lastName, company);
		
		
		
		try (Session session = factory.getCurrentSession()) {
			
			session.beginTransaction();
			
			System.out.println("Saving employee " + tempEmployee + " to dataBase");
			session.save(tempEmployee);
			
			session.getTransaction().commit();
			
			System.out.println("DONE! Employee " + tempEmployee + " saved!" );
		}

	}

	
	
	public void getEmployeeById() {
		System.out.println("Enter the Id of employee that you want to find");
		String id = in.nextLine();
		
		// check that id is digit
		try {
			Integer.parseInt(id);
		} catch (Exception exc) {
			System.out.println("Id is not a digit ");
			return;
		}
		
		List<Employee> tempEmployee;
		
		try (Session session = factory.getCurrentSession()) {
			
			session.beginTransaction();
			
			tempEmployee = session.createQuery("from Employee e where e.id=" + id).getResultList();
			
			if(tempEmployee.isEmpty()) {
				System.out.println("Employee not found");
				return;
			}
			
			System.out.println("Display employee with id=" + id);
			System.out.println(tempEmployee.get(0));
			
			session.getTransaction().commit();
			System.out.println("DONE!");
		}
		
		
		
	}

	
	public void getEmployeesOfCompany() {
		System.out.println("Enter a company name");
		String company = in.nextLine();
		
		List<Employee> tempEmployees;
		
		try (Session session = factory.getCurrentSession()) {
			
			session.beginTransaction();
			
			tempEmployees = session.createQuery("from Employee e where e.company='" + company + "'").getResultList();
			
			System.out.println("Display employees of company " + company);
			for(Employee empy: tempEmployees) {
				System.out.println(empy);
			}
			
			session.getTransaction().commit();			
			System.out.println("DONE!");
		}
	}
	
	public void deleteEmployeeById() {
		System.out.println("Enter Id of employee that you want to delete");
		String id = in.nextLine();
		
		// check that id is digit
		try {
			Integer.parseInt(id);
		} catch (Exception exc) {
			System.out.println("Id is not a digit ");
			return;
		}
		
		try(Session session = factory.getCurrentSession()) {
			session.beginTransaction();
			
			System.out.println("Deleting employee with id=" + id);
			session.createQuery("delete from Employee e where e.id=" + id).executeUpdate();
			
			
			session.getTransaction().commit();
			System.out.println("DONE!");
		}
		
	}
	
	
	public void showEveryEmployeeInDB() {
		
		try(Session session = factory.getCurrentSession()) {
			
			session.beginTransaction();
			System.out.println("Employee Data Base contains:");
			List<Employee> tempEmployees = session.createQuery("from Employee").getResultList();
			
			for(Employee empy : tempEmployees) {
				System.out.println(empy);
			}
			
			session.getTransaction().commit();
			System.out.println("DONE");
			
		}
	}
	
	
	
}