package hierarchy.sample2;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 'MENU'라는 객체를 계층구조화 하는 샘플
 * @author good
 *
 */
public class MenuService {
	
	/**
	 * 계층구조 오브젝트 생성
	 * @param menuList
	 * @return
	 */
	public static Menu createMenuHierarchy(List<Menu> menuList) {
		Menu dummyTop = new Menu();
		dummyTop.setMenuId(-1);
		dummyTop.setParentMenuId(-1);
		dummyTop.setMenuName("TOP");
		
		// 계층 구조를 찾아가기 위한 맵
		//- 위에서 아래로 찾아가는 top > down구조로 정의됨.
		//- stack데이터로 top > down으로 데이터를 넣고, top > down으로 데이터를 꺼낸다.
		Map<Integer, Stack<Integer>> hierarchyMap = new HashMap<>();
		
		// 최상위 메뉴 등록
		//- 더미 객체에 최상위 레벨의 메뉴를 자식으로 추가한다.
		for (int i = 0; i < menuList.size() ; i++) {
			Menu item = menuList.get(i);
			if (item.getParentMenuId() == null) {
				// 하위에 등록한다.
				dummyTop.addChildMenu(item);
				
				// 사용 된 데이터는 list에서 제거한다.
				menuList.remove(i);
				i--;
				
				// 계층 구조를 맵에 추가한다.
				//- 1레벨
				Stack<Integer> temp = new Stack<>();
				temp.push(item.getMenuId());
				hierarchyMap.put(item.getMenuId(), temp);
			}
		}
		
		// 하위 메뉴 등록
		//- 
		//- list의 값이 존재하면 반복해서 실행된다.
		while (menuList.size() > 0) {
			for (int i = 0; i < menuList.size() ; i++) {
				Menu item = menuList.get(i);
				
				// 계층 구조맵에 부모값이 있으면 찾아간다.
				//- 자기 자신의 계층을 계층 구조맵에 등록.
				//- 부모를 찾아가서 하위에 등록.
				//- list에서 자신을 제거.
				if (hierarchyMap.containsKey(item.getParentMenuId())) {
					Integer parentId = item.getParentMenuId();
					
					// 계층 구조를 맵에 추가한다.
					//- n레벨
					Stack<Integer> newHierarchyMap = (Stack<Integer>) hierarchyMap.get(parentId).clone();
					newHierarchyMap.push(item.getMenuId());
					hierarchyMap.put(item.getMenuId(), newHierarchyMap);
					
					// 하위에 등록한다.
					//- n레벨의 부모를 찾아가기 위해 재귀호출한다.
					Stack<Integer> parentHierarchyMap = (Stack<Integer>) hierarchyMap.get(parentId).clone();
					findObjectAndAdd(item, parentHierarchyMap, dummyTop.getChildMenu());
					
					//사용 된 데이터는 list에서 제거한다.
					menuList.remove(i);
					i--;
				}
			}
		}
		
		
		sortMenuHierarchy(dummyTop);
		return dummyTop;
	}
	
	/**
	 * 계층 구조를 찾는다
	 * @param targetItem - 대상 오브젝트
	 * @param hierarchyMap - 계층 구조맵
	 * @param list - 대상 목록
	 */
	private static void findObjectAndAdd(Menu targetItem, Stack<Integer> hierarchyMap, List<Menu> list) {
		// 계층 구조맵에서 맨 위의 값을 꺼낸다.
		Integer findMenuId = hierarchyMap.pop();
		Menu parentMenu = null;
		
		// 대상 목록에서 일치하는 대상을 찾는다.
		for (Menu menu : list) {
			if (menu.getMenuId().equals(findMenuId)) {
				parentMenu = menu;
			}
		}
		
		if (parentMenu != null) {
			if (hierarchyMap.empty()) {
				// 계층 구조맵이 없다면 대상 오브젝트를 하위에 추가한다.
				parentMenu.addChildMenu(targetItem);
			} else {
				// 계층 구조맵이 있다면 재귀호출.
				findObjectAndAdd(targetItem, hierarchyMap, parentMenu.getChildMenu());
			}
		}
	}
	
	/**
	 * 순서값을 사용한 정렬
	 * - 선택 정렬 알고리즘.
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
