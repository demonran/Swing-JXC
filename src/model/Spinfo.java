package model;

public class Spinfo {
	
	private String id;
	private String spname;
	private String jc;
	private String cd;
	private String dw;
	private String gg;
	private String bz;
	private String ph;
	private String pzwh;
	private String memo;
	private String gysname;
	
	public Spinfo(){}
	public Spinfo(String id,String spname){
		this.id = id;
		this.spname = spname;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public String getJc() {
		return jc;
	}
	public void setJc(String jc) {
		this.jc = jc;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public String getGg() {
		return gg;
	}
	public void setGg(String gg) {
		this.gg = gg;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getPzwh() {
		return pzwh;
	}
	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getGysname() {
		return gysname;
	}
	public void setGysname(String gysname) {
		this.gysname = gysname;
	}
	
	
	public String toString(){
		return spname;
	}
}
