package hibernate;

public class Kawiarnia {

	private Integer id;
	
	private Integer ilosc_gwiazdek_michelin;
	
	private String adres;
	
	private String nazwa;

	public Kawiarnia(){}
	public Kawiarnia(Integer id, String nazwa, String adres, Integer il_gwiazdek){
		this.id=id;
		this.ilosc_gwiazdek_michelin=il_gwiazdek;
		this.adres=adres;
		this.nazwa=nazwa;
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
	public Integer getIlosc_gwiazdek_michelin() {
		return ilosc_gwiazdek_michelin;
	}
	public void setIlosc_gwiazdek_michelin(Integer ilosc_gwiazdek_michelin) {
		this.ilosc_gwiazdek_michelin = ilosc_gwiazdek_michelin;
	}
}
