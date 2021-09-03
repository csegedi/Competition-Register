package Projects.Competitions_Register.db;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import Projects.Competitions_Register.model.Competition;
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
	
	public void insertNewCompetition(String firm, Date date, String technologies, String language, String round, String round_type, String status, int feedback, String description) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("INSERT INTO all_competitions (firm, date, technologies, language, round, round_type, status, feedback, description)"
				+ "VALUES (:newFirm, :newDate, :newTechnologies, :newLanguage, :newRound, :newRound_Type, :newStatus, :newFeedback, :newDescription)");
		
		query.setParameter("newFirm", firm);
		query.setParameter("newDate", date);
		query.setParameter("newTechnologies", technologies);
		query.setParameter("newLanguage", language);
		query.setParameter("newRound", round);
		query.setParameter("newRound_Type", round_type);
		query.setParameter("newStatus", status);
		query.setParameter("newFeedback", feedback);
		query.setParameter("newDescription", description);

		query.executeUpdate();

		session.getTransaction().commit();
		session.close();
	}
	
	public void updateRound(int id, String round) {
	
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("UPDATE all_competitions SET round=:newRound " + "WHERE id=:incomingId");
		
		query.setParameter("incomingId", id); 
		query.setParameter("newRound", round);
	
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();

	}
	
	public void updateRound_type(int id, String round_type) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("UPDATE all_competitions SET round_type=:newRound_type " + "WHERE id=:incomingId");
		
		query.setParameter("incomingId", id); 
		query.setParameter("newRound_type", round_type);
	
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();

	}
	
	public void updateStatus(int id, String status) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("UPDATE all_competitions SET status=:newStatus " + "WHERE id=:incomingId");
		
		query.setParameter("incomingId", id); 
		query.setParameter("newStatus", status);
	
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();

	}
	
	public void updateFeedback(int id, int feedback) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("UPDATE all_competitions SET feedback=:newFeedback " + "WHERE id=:incomingId");
		
		query.setParameter("incomingId", id); 
		query.setParameter("newFeedback", feedback);
	
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();

	}
	

	public void updateDescription (int id, String description) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("UPDATE all_competitions SET description=:newDescription " + "WHERE id=:incomingId");
		
		query.setParameter("incomingId", id); 
		query.setParameter("newDescription", description);
	
		query.executeUpdate();

		session.getTransaction().commit();
		session.close();

	}
	
	public Competition getCompetitionById(int id) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Competition comp=session.get(Competition.class, id); 
		
		session.getTransaction().commit();
		session.close();
		
		return comp;
	}
	
	public void close() {
		sessionFactory.close();
	}

}
