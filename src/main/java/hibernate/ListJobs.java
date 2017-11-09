package hibernate;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ListJobs {
	public static void main(String[] args) throws Exception {
		Configuration c = new Configuration();
		c.configure(); // loads hibernate.cfg.xml

		SessionFactory sf = c.buildSessionFactory();
		Session s = sf.openSession();
	//	Query query = s.createSQLQuery("INSERT INTO sys.jobs (job_id, job_title) VALUES (2, 'zosia')");
		
	//	query.executeUpdate();
		
		Query q = s.createQuery("from Job");

		for (Job j : (List<Job>) q.list()) {
			System.out.println(j.getTitle());
		}

		s.close();
		sf.close();
	}
}