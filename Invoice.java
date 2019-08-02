/**
 * 
 */

/**
 * @author feuler
 *
 */
public class Invoice {


	public Invoice() {
		// TODO Auto-generated constructor stub
	}
	
	private Integer cid;
	private String fname;
	private String lname;
	private String pkg;
	private String price;
    private String number;
    private int uid;

	public Invoice(Integer cid, String fname, String lname, String pkg, String price, String number, int uid) {
		this.cid = cid;
		this.fname = fname;
		this.lname = lname;
		this.pkg = pkg;
		this.price = price;
		this.number = number;
		this.uid = uid;
	}
	
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
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

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
    
}
