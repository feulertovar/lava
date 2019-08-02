
public class Contact {

	public Contact() {
		// TODO Auto-generated constructor stub
	}

	private Integer cid;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private String address;
	private String fburl;
	private String igurl;
	private String twurl;
    private int uid;

	public Contact(Integer cid, String fname, String lname, String phone, String email, String address,
			String fburl, String igurl, String twurl, int uid) {
		this.cid = cid;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.fburl = fburl;
		this.igurl = igurl;
		this.twurl = twurl;
		this.uid = uid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFburl() {
		return fburl;
	}

	public void setFburl(String url) {
		this.fburl = url;
	}

	public String getIgurl() {
		return igurl;
	}

	public void seIgurl(String url) {
		this.igurl = url;
	}

	public String getTwurl() {
		return twurl;
	}

	public void setTwurl(String url) {
		this.twurl = url;
	}
	
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    


}
