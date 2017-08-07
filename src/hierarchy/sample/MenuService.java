package hierarchy.sample;

import java.util.Collections;
import java.util.List;

/**
 * 'MENU'라는 객체를 계층구조화 하는 샘플
 * @author good
 *
 */
public class MenuService {
	
	/**
	 * 계층화
	 * - 부모데이터가 앞에, 자식데이터가 뒤에 정렬되어 있는 상태에서 동작.
	 * - 맨 마지막 데이터(가장 낮은 레벨)부터 위로 이동하면서 부모를 찾아서 하위에 등록.
	 * @param menuList
	 * @return
	 */
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
	
	/**
	 * 순서값을 사용한 정렬
	 * - 순서값이 없는 데이터는 아무동작 안함.
	 * @param menu
	 */
	private static void sortMenuHierarchy(Menu menu) {	
		if (menu.hasChildMenu()) {
			List<Menu> menuList = menu.getChildMenu();
			for (int i = 0; i < menuList.size(); i++) {
				if (menuList.get(i).getDisplayOrder() == null) {
					continue;
				}
				int lowestValueIndex = i;
				for (int j = i + 1; j < menuList.size(); j++) {
					if (menuList.get(j).getDisplayOrder() == null) {
						continue;
					}
					if (menuList.get(j).getDisplayOrder() < menuList.get(lowestValueIndex).getDisplayOrder()) {
						lowestValueIndex = j;
					}
				}
				// swap
				if (i != lowestValueIndex) {
					Collections.swap(menuList, i, lowestValueIndex);
				}
				
				// recursive
				//- 현제 위치(i)에서 정렬이 된 이후에(가장 낮은 값이 위치) 자기 자신(i)을 재귀 호출한다.
				//- 재귀호출이 먼저 이루어지고 정렬이 되면 위치가 바뀌면서 정렬이 안 되는 경우가 발생한다.
				sortMenuHierarchy(menuList.get(i));
			}
		}
	}
	
}
