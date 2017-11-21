package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class WypelnijDanymi {
	private static SessionFactory factory;
	
	public WypelnijDanymi(SessionFactory f){
		this.factory = f;
	}
	
	public static void dodajWszystko(){
		dodajHurtownie();
		dodajKawiarnie();
		dodajZamowienia();
		dodajPracownikow();
		dodajDania();
		dodajOferuje();
	}
	public static void dodajHurtownie(){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		for (int i=0; i<20; i++){
			Hurtownia h = new Hurtownia();
			h.setNazwa(new String("hurtownia" + i));
			h.setAdres(new String("ul. Lesna " + i*125%36));
			h.setTyp("spozywcza");
			Integer hID = (Integer) s.save(h);
		}		
		t.commit();
		s.close();
	}
	
	public static Hurtownia dodajHurtownie(String nazwa, String adres, String typ){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		Hurtownia h = new Hurtownia();
		h.setNazwa(nazwa);
		h.setAdres(adres);
		h.setTyp(typ);
		Integer hID = (Integer) s.save(h);
		t.commit();
		s.close();
		return h;
	}
	public static Kawiarnia dodajKawiarnie(String nazwa, String adres, Integer il_gwiazdek){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		Kawiarnia k = new Kawiarnia();
		k.setAdres(adres);
		k.setIlosc_gwiazdek_michelin(il_gwiazdek);
		k.setNazwa(nazwa);
		Integer hID = (Integer) s.save(k);
		t.commit();
		s.close();
		return k;
	}
	public static void dodajKawiarnie(){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		for (int i=14; i<100; i+=5){
			Kawiarnia k = new Kawiarnia();
			k.setAdres(new String("ul. Dluga " + i*124%126));
			k.setIlosc_gwiazdek_michelin(i%6);
			k.setNazwa(new String("kawiarnia" + i));
			Integer hID = (Integer) s.save(k);
		}
		
		t.commit();
		s.close();
	}
	
	public static Zamowienie dodajZamowienie(String data, Kawiarnia k_id, Hurtownia h_id){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		Zamowienie z = new Zamowienie();
		z.setKawiarnia(k_id);
		z.setHurtownia(h_id);
		z.setData(data);
		Integer hID = (Integer) s.save(z);
		t.commit();
		s.close();
		return z;
	}
	public static void dodajZamowienia(){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		for(int i=153; i<180; i++){
			Zamowienie z = new Zamowienie();
			Kawiarnia k = (Kawiarnia) s.createQuery("FROM Kawiarnia "
					+ "WHERE id=" + (i%20+1)).list().get(0);
			Hurtownia h = (Hurtownia) s.createQuery("FROM Hurtownia "
					+ "WHERE id=" + (i%22+1)).list().get(0);			
			z.setKawiarnia(k);
			z.setHurtownia(h);
			if ((i%12+1) > 9) {
				if ((i%30+1) > 9) z.setData(new String("2017-" +(i%12+1) + "-" + (i%30+1) ));
				else z.setData(new String("2017-" +(i%12+1) + "-0" + (i%30+1) ));
			}
			else {
				if ((i%30+1) > 9) z.setData(new String("2017-0" +(i%12+1) + "-" + (i%30+1) ));
				else z.setData(new String("2017-0" +(i%12+1) + "-0" + (i%30+1) ));
			}
			Integer hID = (Integer) s.save(z);
		}
		
		t.commit();
		s.close();
	}
	public Pracownik dodajPracownika(String imie, String nazwisko, String telefon,
			Float stawka, String stanowisko, Integer urlop, Kawiarnia k_id) throws InterruptedException{
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		System.out.println("wait started");
		Thread.sleep(10000);
		Pracownik p = new Pracownik();
		p.setImie(imie);
		p.setKawiarnia(k_id);
		p.setNazwisko(nazwisko);
		p.setPozostala_ilosc_dni_urlopu(urlop);
		p.setStanowisko(stanowisko);
		p.setStawka_godzinowa(stawka);
		p.setTelefon(telefon);
		Integer hID = (Integer) s.save(p);
		System.out.println("wait finished");
		t.commit();
		s.close();
		return p;
	}
	public Pracownik dodajPracownikaBezTransakcji(String imie, String nazwisko, String telefon,
			Float stawka, String stanowisko, Integer urlop, Kawiarnia k_id){
		
		Pracownik p = new Pracownik();
		p.setImie(imie);
		p.setKawiarnia(k_id);
		p.setNazwisko(nazwisko);
		p.setPozostala_ilosc_dni_urlopu(urlop);
		p.setStanowisko(stanowisko);
		p.setStawka_godzinowa(stawka);
		p.setTelefon(telefon);
		return p;
	}
	
	public static void dodajPracownikow(){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		for (int i=28; i<90; i+=3){
			Pracownik p = new Pracownik();
			Kawiarnia k = (Kawiarnia) s.createQuery("FROM Kawiarnia "
					+ "WHERE id=" + (i%20+1)).list().get(0);
			p.setImie(new String("ola"));
			p.setKawiarnia(k);
			p.setNazwisko("makota");
			p.setPozostala_ilosc_dni_urlopu(i%26);
			p.setStanowisko("kelnerka");
			p.setStawka_godzinowa((float)i*293/259);
			p.setTelefon(new String("5" + i%9 + i%5 + i%3 + "345" + i%6 + i%9));
			Integer hID = (Integer) s.save(p);
		}
		
		t.commit();
		s.close();
	}
	public static Danie dodajDanie(String nazwa, Float cena){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		Danie d = new Danie();
		d.setNazwa(nazwa);
		d.setCena(cena);
		Integer hID = (Integer) s.save(d);
		t.commit();
		s.close();
		return d;
	}
	public static void dodajDania(){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		for (int i=1658; i<1699; i+=2){
			Danie d = new Danie();
			d.setNazwa("ciasto");
			d.setCena((float) i/383);
			Integer hID = (Integer) s.save(d);
		}
		
		t.commit();
		s.close();
	}

	public static Oferuje dodajOferuje(Kawiarnia k_id, Danie d_id){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		Oferuje o = new Oferuje();
		o.setDanie_id(d_id);
		o.setKawiarnia_id(k_id);
		s.save(o);
		t.commit();
		s.close();
		return o;
	}
	public static void dodajOferuje(){
		Session s = factory.openSession();
		Transaction t = s.beginTransaction();
		for (int i=29; i<60; i++){
			Kawiarnia k = (Kawiarnia) s.createQuery("FROM Kawiarnia "
					+ "WHERE id=" + (((int)i*74)%38+1)).list().get(0);
			Danie d = (Danie) s.createQuery("FROM Danie "
					+ "WHERE id=" + (((int)i*93)%46+1)).list().get(0);
			Oferuje o = new Oferuje();
			o.setDanie_id(d);
			o.setKawiarnia_id(k);
			s.saveOrUpdate(o);
		}
		
		t.commit();
		s.close();
	}
}
