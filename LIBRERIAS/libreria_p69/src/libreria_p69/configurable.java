package libreria_p69;

import java.util.ArrayList;
import java.util.List;

public interface configurable<T> {
	/**
	 * Método que permite crear List de tipo T
	 * @param elements
	 * @return List
	 */
	public default List createList(T...elements) {
		List<T>lst = new ArrayList<>();
		for(T e:elements) {
			lst.add(e);
		}
		
		return lst;
	}
	
	/**
	 * Método que permite imprimir en consola los parámetros
	 * @param elements
	 */
	public default void printElements(T...elements) {
		for(int i=0; i<elements.length; i++) {
			System.out.printf("(%d).-\t%s\n.", (i+1), elements[i]);
		}
	}
}
