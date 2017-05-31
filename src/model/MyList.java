package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import physical.Customer;

public class MyList<E> implements Iterable<E> {
	public MyNode<E> head;
	public MyNode<E> tail;
	public int size = 0;

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			public MyNode<E> temp = head;

			@Override
			public boolean hasNext() {
				return temp != null;
			}

			@Override
			public E next() {
				E e = temp.data;
				temp = temp.next;
				return e;
			}
		};
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean contain(String toString) {
		for (MyNode<E> temp = head; temp != null; temp = temp.next) {
			if (temp.data.toString().equals(toString)) {
				return true;
			}
		}
		return false;
	}

	public void add(E o) {
		MyNode<E> myNode = new MyNode<E>(o);
		if (isEmpty()) {
			myNode.next = tail;
			head = tail = myNode;
		} else {
			tail.next = myNode;
			tail = myNode;
		}
		size++;

	}

	public static void main(String[] args) {
		MyList<Customer> list = new MyList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new Customer(String.valueOf(i), String.valueOf(i), String.valueOf(i)));
		}
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}

	public Object toList() {
		List<E> list = new ArrayList<>();
		for (MyNode<E> temp = head; temp != null; temp = temp.next) {
			list.add(temp.data);
		}
		return list;
	}

	public void remove(E e) {
		for (MyNode<E> mynode = head; mynode != null; mynode = mynode.next) {
			if (mynode.equals(e)) {
				head = head.next;
				size--;
			} else if (mynode.next.data.equals(e)) {
				mynode.next = mynode.next.next;
				size--;
			}
		}
	}
}
