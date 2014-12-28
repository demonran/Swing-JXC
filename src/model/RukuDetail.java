package model;
public class RukuDetail {
	private int id;
	private String rkId;
	private String spId;
	private Double dj;
	private Integer sl;
	public RukuDetail() {
	}
	public RukuDetail(String spId, String rkId, Double dj,
			Integer sl) {
		this.spId = spId;
		this.rkId = rkId;
		this.dj = dj;
		this.sl = sl;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpId() {
		return this.spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getRkId() {
		return this.rkId;
	}

	public void setRkId(String rkId) {
		this.rkId = rkId;
	}

	public Double getDj() {
		return this.dj;
	}

	public void setDj(Double dj) {
		this.dj = dj;
	}

	public Integer getSl() {
		return this.sl;
	}

	public void setSl(Integer sl) {
		this.sl = sl;
	}

}