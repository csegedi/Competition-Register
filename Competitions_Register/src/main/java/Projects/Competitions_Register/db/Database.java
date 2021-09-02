package Projects.Competitions_Register.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import Projects.Competitions_Register.model.Competition;
import Projects.Competitions_Register.model.Status;
import Projects.Competitions_Register.model.User;

public class Database {
	
	private SessionFactory sessionFactory;

	public Database() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public List <User> getUsers() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<User> userList=null; 
		
		Query query = session.createNativeQuery(
				"SELECT * FROM user", User.class);

		userList = query.getResultList();

		session.getTransaction().commit();
		session.close();
		
		return userList;
		
	}
	
	
	public List<Competition> getAllCompetition(){
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		List<Competition> allCompetitions=null; 
		
		Query query=session.createNativeQuery("SELECT * FROM all_competitions", Competition.class); {
		allCompetitions=query.getResultList(); 
		
		session.getTransaction().commit();
		session.close();
			
		}
		
		return allCompetitions;
	}
	

	public void close() {
		sessionFactory.close();
	}

	

	

}
