package es.us.lsi.dp.controllers.tables;

public class DatatableJson {

	long iTotalRecords;
	long iTotalDisplayRecords;
	String[][] aaData;
	String sEcho;

	public DatatableJson(final String sEcho, final long iTotalRecords, final long iTotalDisplayRecords, final String[][] aaData) {
		this.sEcho = sEcho;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.aaData = aaData;
	}

	public long getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(final long iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(final int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String[][] getAaData() {
		return aaData;
	}

	public void setAaData(final String[][] aaData) {
		this.aaData = aaData;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(final String sEcho) {
		this.sEcho = sEcho;
	}

}
