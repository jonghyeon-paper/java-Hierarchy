package hierarchy.sample;

import java.util.ArrayList;
import java.util.List;

import hierarchy.JsonPrint;

public class Test {

	public static void main(String[] args) {
		
		List<Menu> list = new ArrayList<>();
		list.add(new Menu(1, null, "menu1", 1));
		list.add(new Menu(2, null, "menu2", 3));
		list.add(new Menu(3, 1, "menu3", 2));
		list.add(new Menu(4, 1, "menu4", 1));
		list.add(new Menu(5, 2, "menu5", 1));
		list.add(new Menu(6, 2, "menu6", 3));
		list.add(new Menu(7, 2, "menu7", 2));
		list.add(new Menu(8, null, "menu8", 2));
		
		Menu menu = MenuService.createMenuHierarchy(list);
		JsonPrint.print(menu);
		
		/*
		 * expect result
		 * TOP
		 * ├ menu1
		 * │ ├ menu4
		 * │ └ menu3
		 * ├ menu8
		 * └ menu2
		 *   ├ menu5
		 *   ├ menu7
		 *   └ menu6
		 */
	}
}
