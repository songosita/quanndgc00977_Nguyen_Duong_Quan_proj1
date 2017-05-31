package module;

import java.io.File;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import model.MyList;
import physical.Order;

public class Module_Order {
	File f;

	public MyList<Order> readFile() {
		MyList<Order> kq = new MyList<>();
		f = Res.getDesktopFile("dsa2017-data/1e5/orders.json");
		try {
			kq = readList(f, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kq;
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

}
