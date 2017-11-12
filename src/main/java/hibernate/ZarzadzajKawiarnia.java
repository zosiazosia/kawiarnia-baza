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
	//	pokazDaniaKawiarni(17);
	//	pokazPracownikow(1);
	//	wyliczSredniaStawkeWKawiarni(1);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = br.readLine();
		if (in.equals(new String("wait"))){
			longTransaction();
		}
		else if (in.equals(new String("show"))){
			pokazPracownikow(1);
		}
		factory.close();
	}
	
	public static void wyliczSredniaStawkeWKawiarni(int kawiarnia_id){
		Session s = factory.openSession();
		//	Transaction t  = s.beginTransaction();
			
			List<Pracownik> pracownicy = s.createQuery("FROM Pracownik WHERE kawiarnia_id='" + kawiarnia_id+"'").list();
			
			for (Pracownik p : pracownicy){
				System.out.println(p.getImie() + " " + p.getNazwisko());
			}
			
		//	t.commit();
			s.close();
	}
	
	public static void dodajDane() throws IOException{
		System.out.println("Zarzadzanie kawiarnia.");
		System.out.println("Wybierz tabele: "
				+ "\n1. Danie"
				+ "\n2. Hurtownia"
				+ "\n3. Kawiarnia"
				+ "\n4. Oferta dania"
				+ "\n5. Pracownik"
				+ "\n6. Zamowienie");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String choice = null;
		try {
			choice = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (choice){
			case "1": //danie
				
				break;
			case "2": //hurtownia
				break;
			case "3": //kawiarnia
				break;
			case "4": //oferuje
				break;
			case "5": //pracownik
				break;
			case "6": //zamowienie
				break;
			default:
				System.out.println("Niepoprawny wybor");
				choice = br.readLine();
					
		}
		
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
	
	public static void pokazPracownikow(Integer kawiarnia_id){
		Session s = null;
		Transaction t  = null; 
		try{
			s = factory.openSession();
			
			t = s.beginTransaction();
			t.setTimeout(20000);
			int result = s.createQuery("update Pracownik "
					+ "SET imie='agnieszka' WHERE id=2").executeUpdate();
			
			List<Pracownik> pracownicy = s.createQuery("FROM Pracownik WHERE kawiarnia_id='" + kawiarnia_id+"'").list();
			
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
		/*	t.setTimeout(20000); 
			Kawiarnia k = (Kawiarnia) s.createQuery("FROM Kawiarnia WHERE id=1").list().get(0);
			Pracownik p = wypelnijDanymi.dodajPracownika("tomasz", "el","869487321", (float)15.0, 
					"sprzatacz", 10, k);*/
	//		Integer hID = (Integer) s.save(p);
			System.out.println("wait starts");
			int result = s.createQuery("update Pracownik "
					+ "SET imie='lolita' WHERE id=4").executeUpdate();
			
			Thread.sleep(15000);
			System.out.println("wait finished");
		//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//	String in = br.readLine();
			
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