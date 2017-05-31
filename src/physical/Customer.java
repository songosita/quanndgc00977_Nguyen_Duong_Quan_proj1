package physical;


public class Customer{
	public String ccode;
	public String cus_name;
	public String phone;

	public Customer(String ccode, String cus_name, String phone) {
		this.ccode = ccode;
		this.cus_name = cus_name;
		this.phone = phone;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return  ccode;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
