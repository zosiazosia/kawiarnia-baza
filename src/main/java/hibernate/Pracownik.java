package hibernate;

public class Pracownik {
	private Integer id, pozostala_ilosc_dni_urlopu, kawiarnia_id;
	private String imie, nazwisko, telefon, stanowisko;
	private Float stawka_godzinowa;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPozostala_ilosc_dni_urlopu() {
		return pozostala_ilosc_dni_urlopu;
	}
	public void setPozostala_ilosc_dni_urlopu(Integer pozostala_ilosc_dni_urlopu) {
		this.pozostala_ilosc_dni_urlopu = pozostala_ilosc_dni_urlopu;
	}
	public Integer getKawiarnia_id() {
		return kawiarnia_id;
	}
	public void setKawiarnia_id(Integer kawiarnia_id) {
		this.kawiarnia_id = kawiarnia_id;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getStanowisko() {
		return stanowisko;
	}
	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}
	public Float getStawka_godzinowa() {
		return stawka_godzinowa;
	}
	public void setStawka_godzinowa(Float stawka_godzinowa) {
		this.stawka_godzinowa = stawka_godzinowa;
	}
	
}
