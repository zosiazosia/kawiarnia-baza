package hibernate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.hibernate.cfg.*;
import org.hibernate.*;

public class ZarzadzajKawiarnia {
	private static SessionFactory factory;
	private static WypelnijDanymi wypelnijDanymi;
	public static void main(String[] args) throws Exception {
		factory = new Configuration().configure().buildSessionFactory();
		wypelnijDanymi = new WypelnijDanymi(factory);
	//	wypelnijDanymi.dodajWszystko();
		
		srednieStawki();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in;
		while(!(in = br.readLine()).equals("exit")){
			Session s = null;
			Transaction t = null;
			if (in.equals("long")) {
				longTransaction();
			}
			else if (in.equals("show")){
				pokazPracownikow();
			}
			else {
				try {
					s = factory.openSession();
					t  = s.beginTransaction();
				
					List list = s.createQuery(in).list();
					
					for (Object i : list){
						System.out.println(i.toString());
					}
				}
				catch(Exception ex){
					System.err.println(ex.getMessage());
				}
				finally{
					t.commit();
					s.close();
				}
			}
		}
		factory.close();
	}
	
	public static void srednieStawki(){
		Session s = factory.openSession();
		Transaction t  = s.beginTransaction();
		
		List<Object[]> list = s.createQuery("SELECT avg(stawka_godzinowa), kawiarnia_id.id, kawiarnia_id.nazwa FROM Pracownik GROUP BY kawiarnia_id.id, kawiarnia_id.nazwa ORDER BY 2").list();
		for (Object[] i : list){
			System.out.println("Kawiarnia: "+  i[1] + " "+i[2]+";\t œrednia stawka: " + i[0]);
		}
				
		t.commit();
		s.close();
	}
		
	public static void dodajZamowienie(){
		Session s = factory.openSession();
		List<Kawiarnia> kawiarnie = s.createQuery("FROM Kawiarnia").list();
		List<Hurtownia> hurt = s.createQuery("FROM Hurtownia WHERE id=1").list();
		Zamowienie z1 = wypelnijDanymi.dodajZamowienie("2017-12-14", kawiarnie.get(0), hurt.get(0));
		
		List<Zamowienie> zamowienia = s.createQuery("FROM Zamowienie").list();
		for (Zamowienie zam : zamowienia){
			System.out.println(zam.getId() + " " + zam.getData());
		}
		s.close();
	}
	
	public static void pokazDaniaKawiarni(int k){
		Session s = factory.openSession();
		List<Kawiarnia> kaw = s.createQuery("FROM Kawiarnia WHERE id="+k).list();
		List<Oferuje> oferta = s.createQuery("FROM Oferuje WHERE kawiarnia_id=" + k).list();
		
		for (Oferuje o : oferta){
			List<Danie> dania = s.createQuery("FROM Danie WHERE id=" + o.getDanie_id().getId()).list();
			for (Danie d : dania){
				System.out.println("Kawiarnia: " + kaw.get(0).getNazwa() + " danie: " + d.getNazwa() + " cena: " + d.getCena());
			}
		}
		
	}
	
	public static void pokazPracownikow(){
		Session s = null;
		Transaction t  = null; 
		try{
			s = factory.openSession();
			
			t = s.beginTransaction();
			t.setTimeout(20000);
			int result = s.createQuery("update Pracownik "
					+ "SET imie='agnieszka' WHERE id=1").executeUpdate();
			
			List<Pracownik> pracownicy = s.createQuery("FROM Pracownik WHERE id=1").list();
			
			for (Pracownik p : pracownicy){
				System.out.println(p.getImie() + " " + p.getNazwisko());
			}
		}
		catch(Exception ex){
			t.rollback();
		}
		finally{
			t.commit();
			s.close();
		}
		
	}
	
	public static void longTransaction() throws InterruptedException{
		Session s=null;
		Transaction t = null;
		try {
			s = factory.openSession();		
			t = s.beginTransaction();
			t.setTimeout(20000); 
		/*	Kawiarnia k = (Kawiarnia) s.createQuery("FROM Kawiarnia WHERE id=1").list().get(0);
			Pracownik p = wypelnijDanymi.dodajPracownika("tomasz", "el","869487321", (float)15.0, 
					"sprzatacz", 10, k);
			Integer hID = (Integer) s.save(p);*/
			System.out.println("wait starts");
			int result = s.createQuery("update Pracownik "
					+ "SET imie='malgorzata' WHERE id=1").executeUpdate();
			
			Thread.sleep(15000);
			System.out.println("wait finished");
			
		} catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
			t.rollback();
		}
		finally{
			t.commit();
			s.close();
		}
	}
}