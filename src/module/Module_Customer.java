package module;

import java.io.File;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import model.MyList;
import model.MyNode;
import physical.Customer;

public class Module_Customer {

	File f;
	public MyList<Customer> readFile() {
		MyList<Customer> kq = new MyList<>();
		f = Res.getDesktopFile("dsa2017-data/1e5/customers.json");
		try {
			kq = readList(f, Customer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
	}

	@SuppressWarnings("unchecked")
	protected static <T1> MyList<T1> readList(File f, Class<T1> cl) throws Exception {
		MyList<T1> items =  new MyList<>();

		for (Object s : (Iterable) new ObjectMapper().readValue(f, Object.class)) {
			Map<String, Object> sjj = (Map<String, Object>) s;
			T1 tjj = cl.newInstance();
			for (String k : sjj.keySet()) {
				Object vk = sjj.get(k);
				cl.getField(k).set(tjj, vk);
			}

			items.add(tjj);
		}

		return items;
	}

	private static <T1> void writeList(File f, MyList<T1> items) throws Exception {
		new ObjectMapper().writeValue(f, items.toList());
	}
	
	public void addNew(MyList<Customer> kq) {
		f = Res.getDesktopFile("dsa2017-data/1e5/customers.json");
		try {
			writeList(f, kq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MyNode<Customer> searchbyCcode(String ccode){
		MyList<Customer> c = readFile();
		MyNode<Customer> c1 = null;
		for (Customer customer : c) {
			if(customer.ccode.equals(ccode)){	
				c1 = new MyNode<Customer>(customer);
				c1.data.setCcode(customer.getCcode());
				c1.data.setCus_name(customer.getCus_name());
				c1.data.setPhone(customer.getPhone());
			}		
		}
		return c1;
	}

	public boolean deletebyCcode(String ccode) {
		MyList<Customer> c = readFile();		
		for (Customer customer : c) {
			if(customer.ccode.equals(ccode)){
				c.remove(customer);
				addNew(c);
			return true;
			}
		}
		return false;		
	}
}
