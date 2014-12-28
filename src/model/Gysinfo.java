package model;

public class Gysinfo {
	
	private String name; //����
	private String jc;//���
	private String id;//���
	private String address;//��ַ
	private String tel;//�绰
	private String email;//e-mail
	private String bianma;//����
	private String fax;//����
	private String lian;//��ϵ��
	private String ltel;//��ϵ�˵绰
	private String yh;//���п�
	
	public Gysinfo(){}
	
	public Gysinfo(String id){
		this.id = id;
	}
	
	 public Gysinfo(String id,String name ) {
		super();
		this.name = name;
		this.id = id;
	}

	/** full constructor */
    public Gysinfo(String id, String name, String jc, String address, String bianma, String tel, String fax, String lian, String ltel, String yh, String email) {
        this.id = id;
        this.name = name;
        this.jc = jc;
        this.address = address;
        this.bianma = bianma;
        this.tel = tel;
        this.fax = fax;
        this.lian = lian;
        this.ltel = ltel;
        this.yh = yh;
        this.email = email;
    }

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJc() {
		return jc;
	}
	public void setJc(String jc) {
		this.jc = jc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBianma() {
		return bianma;
	}
	public void setBianma(String bianma) {
		this.bianma = bianma;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLian() {
		return lian;
	}
	public void setLian(String lian) {
		this.lian = lian;
	}
	public String getLtel() {
		return ltel;
	}
	public void setLtel(String ltel) {
		this.ltel = ltel;
	}
	public String getYh() {
		return yh;
	}
	public void setYh(String yh) {
		this.yh = yh;
	}
	
	
	
}
