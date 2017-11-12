package hibernate;

public class Danie {
	private Integer id;
	private Float cena;
	private String nazwa;
	
	public Danie(){}
	public Danie(Integer id, String n, Float c){
		this.id=id;
		this.cena=c;
		this.nazwa=n;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getCena() {
		return cena;
	}
	public void setCena(Float cena) {
		this.cena = cena;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
}
