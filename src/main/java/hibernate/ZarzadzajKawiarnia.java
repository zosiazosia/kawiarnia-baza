package hibernate;
import java.sql.Date;
import java.util.List;

import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ZarzadzajKawiarnia {
	private static SessionFactory factory;
	public static void main(String[] args) throws Exception {
		
		factory = new Configuration().configure().buildSessionFactory();
		
		
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		
	/*	
		Hurtownia h = new Hurtownia();
		h.setId(1);
		h.setNazwa("h1");
		h.setAdres("ul. Polna 24");
		h.setTyp("spozywcza");
		Integer hID = (Integer) s.save(h);
		
		Kawiarnia k = new Kawiarnia();
		k.setId(1);
		k.setNazwa("checz");
		k.setAdres("ul. Piekna 12");
		k.setIlosc_gwiazdek_michelin(6);
		Integer kID = (Integer) s.save(k);
		
		Zamowienie z = new Zamowienie();
		z.setId(1);
		z.setData("2017-12-01");
		z.setHurtownia_id(h);
		z.setKawiarnia_id(k);
		Integer zID = (Integer) s.save(z);
		
		Zamowienie z2 = new Zamowienie();	
		z2.setId(2);
		z2.setData("2017-12-14");
		z2.setHurtownia_id(h);
		z2.setKawiarnia_id(k);
		Integer z2ID = (Integer) s.save(z2);
		
		tx.commit();
		*/
		List<Zamowienie> zamowienia = s.createQuery("FROM Zamowienie").list();
		for (Zamowienie zam : zamowienia){
			System.out.println(zam.getData());
		}
		
	//	Query q = s.createQuery("from Job");

	/*	for (Job j : (List<Job>) q.list()) {
			System.out.println(j.getTitle());
		}
*/
		s.close();
		factory.close();
	}
}