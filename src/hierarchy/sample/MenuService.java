package hierarchy.sample;

import java.util.Collections;
import java.util.List;

/**
 * 'MENU'라는 객체를 계층구조화 하는 샘플
 * @author good
 *
 */
public class MenuService {

	public static Menu createMenuHierarchy(List<Menu> menuList) {
		Menu dummyTop = new Menu();
		dummyTop.setMenuId(-1);
		dummyTop.setParentMenuId(-1);
		dummyTop.setMenuName("TOP");
		
		for (int i = menuList.size() - 1; i > -1; i--) {
			Menu temporary = menuList.get(i);
			if (temporary.getParentMenuId() != null) {
				for (int j = menuList.size() - 2; j > -1; j--) {
					Menu target = menuList.get(j);
					if (target.getMenuId().equals(temporary.getParentMenuId())) {
						target.addChildMenu(temporary);
						break;
					}
				}
			} else {
				dummyTop.getChildMenu().add(temporary);
			}
			menuList.remove(i);
		}
		sortMenuHierarchy(dummyTop);
		return dummyTop;
	}
	
	private static void sortMenuHierarchy(Menu menu) {	
		if (menu.hasChildMenu()) {
			List<Menu> menuList = menu.getChildMenu();
			for (int i = 0; i < menuList.size(); i++) {
				// recursive
				sortMenuHierarchy(menuList.get(i));
				
				int lowestValueIndex = i;
				for (int j = i + 1; j < menuList.size(); j++) {
					if (menuList.get(j).getDisplayOrder() < menuList.get(lowestValueIndex).getDisplayOrder()) {
						lowestValueIndex = j;
					}
				}
				// swap
				if (i != lowestValueIndex) {
					Collections.swap(menuList, i, lowestValueIndex);
				}
			}
		}
	}
	
}
