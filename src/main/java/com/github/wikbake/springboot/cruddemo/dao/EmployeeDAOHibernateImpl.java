package com.github.wikbake.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.wikbake.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define fields dor entitymaganer 
	private EntityManager entityManager;
	
	// set up contructor injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		// get the current hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		
		// create a query ----- here we are using native Hibernate API
		Query<Employee> theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		// execute query and get the result list 
		List<Employee> employees = theQuery.getResultList();
		
		// return the results 
		return employees;
	}

	@Override
	public Employee findById(int theId) {

		// get the current Hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		
		// get the employee 
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		// return the employee 
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {

		// get the current Hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		
		// save employee 
		currentSession.saveOrUpdate(theEmployee);
	}

	@Override
	public void deleteById(int theId) {

		// get the current Hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete object with primary key 
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
	}

}
