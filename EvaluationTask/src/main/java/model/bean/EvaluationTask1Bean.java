package model.bean;

public class EvaluationTask1Bean {
	
	private String JANCD;
	private String ISBNCD;
	private String BOOKNM;
	private String BOOKKANA;
	private int PRICE;
	private String ISSUEDATE;
	private String CREATEDATE;
	private String UPDATEDATE;
	
	public EvaluationTask1Bean() {}

	public EvaluationTask1Bean(String jANCD, String iSBNCD, String bOOKNM, String bOOKKANA, int pRICE, String iSSUEDATE,
			String cREATEDATE, String uPDATEDATE) {
		super();
		JANCD = jANCD;
		ISBNCD = iSBNCD;
		BOOKNM = bOOKNM;
		BOOKKANA = bOOKKANA;
		PRICE = pRICE;
		ISSUEDATE = iSSUEDATE;
		CREATEDATE = cREATEDATE;
		UPDATEDATE = uPDATEDATE;
	}

	public String getJANCD() {
		return JANCD;
	}

	public void setJANCD(String jANCD) {
		JANCD = jANCD;
	}

	public String getISBNCD() {
		return ISBNCD;
	}

	public void setISBNCD(String iSBNCD) {
		ISBNCD = iSBNCD;
	}

	public String getBOOKNM() {
		return BOOKNM;
	}

	public void setBOOKNM(String bOOKNM) {
		BOOKNM = bOOKNM;
	}

	public String getBOOKKANA() {
		return BOOKKANA;
	}

	public void setBOOKKANA(String bOOKKANA) {
		BOOKKANA = bOOKKANA;
	}

	public int getPRICE() {
		return PRICE;
	}

	public void setPRICE(int pRICE) {
		PRICE = pRICE;
	}

	public String getISSUEDATE() {
		return ISSUEDATE;
	}

	public void setISSUEDATE(String iSSUEDATE) {
		ISSUEDATE = iSSUEDATE;
	}

	public String getCREATEDATE() {
		return CREATEDATE;
	}

	public void setCREATEDATE(String cREATEDATE) {
		CREATEDATE = cREATEDATE;
	}

	public String getUPDATEDATE() {
		return UPDATEDATE;
	}

	public void setUPDATEDATE(String uPDATEDATE) {
		UPDATEDATE = uPDATEDATE;
	}

}
