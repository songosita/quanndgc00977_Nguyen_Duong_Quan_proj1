package module;

import java.io.File;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import model.MyList;
import model.MyNode;
import physical.Product;

public class Module_Product {

	File f;

	public MyList<Product> readFile() {
		MyList<Product> lp = new MyList<>();
		f = Res.getDesktopFile("dsa2017-data/1e5/products.json");
		try {
			lp = readList(f, Product.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lp;
	}

	@SuppressWarnings("unchecked")
	protected static <T1> MyList<T1> readList(File f, Class<T1> cl) throws Exception {
		MyList<T1> items = new MyList<>();

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
		ObjectMapper m = new ObjectMapper();
		m.writeValue(f, items.toList());
	}

	@SuppressWarnings("unchecked")
	private static MyList<Object> readList(File f) throws Exception {
		ObjectMapper m = new ObjectMapper();
		return (MyList<Object>) m.readValue(f, Object.class);
	}

	public void addNew(MyList<Product> lp) {
		f = Res.getDesktopFile("dsa2017-data/1e5/products.json");
		try {
			writeList(f, lp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MyNode<Product> searchbyPcode(String pcode) {
		MyList<Product> p = readFile();
		MyNode<Product> p1 = null;
		for (Product product : p) {
			if (product.pcode.equals(pcode)) {
				p1 = new MyNode<Product>(product);
				p1.data.setPcode(product.getPcode());
				p1.data.setPro_name(product.getPro_name());
				p1.data.setQuantity(product.getQuantity());
				p1.data.setSale(product.getSale());
				p1.data.setPrice(product.getPrice());
				p1.data.setPro_image_url(product.getPro_image_url());
			}
		}
		return p1;
	}

	public boolean deletebyPcode(String pcode) {
		MyList<Product> p = readFile();
		for (Product product : p) {
			if (product.pcode.equals(pcode)) {
				p.remove(product);
				addNew(p);
			return true;
			}
		}
		return false;

	}
}
