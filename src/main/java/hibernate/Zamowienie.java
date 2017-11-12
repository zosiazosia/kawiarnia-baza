package hibernate;

public class Zamowienie {

	private Integer id;
	
	private Kawiarnia kawiarnia_id;
	
	private Hurtownia hurtownia_id;
	
	private String data;
	
	public Zamowienie(){}
	public Zamowienie(Integer id, Kawiarnia kId, Hurtownia hId, String data){
		this.id=id;
		this.kawiarnia_id=kId;
		this.hurtownia_id=hId;
		this.data=data;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Kawiarnia getKawiarnia_id() {
		return kawiarnia_id;
	}
	public Kawiarnia getKawiarnia(){return kawiarnia_id;}
	public void setKawiarnia(Kawiarnia id){this.kawiarnia_id = id;}
	public Hurtownia getHurtownia(){return hurtownia_id;}
	public void setHurtownia(Hurtownia id){this.hurtownia_id = id;}
	public void setKawiarnia_id(Kawiarnia kawiarnia_id) {
		this.kawiarnia_id = kawiarnia_id;
	}
	public Hurtownia getHurtownia_id() {
		return hurtownia_id;
	}
	public void setHurtownia_id(Hurtownia hurtownia_id) {
		this.hurtownia_id = hurtownia_id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}