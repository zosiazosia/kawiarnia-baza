package hibernate;

public class Hurtownia {

	private Integer id;	
	private String adres;
	private String nazwa;
	private String typ;
	
	public Hurtownia(){}
	public Hurtownia(Integer id, String nazwa, String adres, String typ){
		this.id = id;
		this.adres=adres;
		this.nazwa=nazwa;
		this.typ=typ;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
}
