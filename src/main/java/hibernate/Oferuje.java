package hibernate;

import java.io.Serializable;

public class Oferuje implements Serializable{
	private Kawiarnia kawiarnia_id;
	private Danie danie_id;

	public Oferuje(){}
	public Oferuje(Kawiarnia kId, Danie dId){
		this.kawiarnia_id=kId;
		this.danie_id=dId;
	}
	public Kawiarnia getKawiarnia_id() {
		return kawiarnia_id;
	}

	public void setKawiarnia_id(Kawiarnia kawiarnia_id) {
		this.kawiarnia_id = kawiarnia_id;
	}

	public Danie getDanie_id() {
		return danie_id;
	}

	public void setDanie_id(Danie danie_id) {
		this.danie_id = danie_id;
	}
	
}
