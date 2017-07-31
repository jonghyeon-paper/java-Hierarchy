package hierarchy.sample;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	
	private Integer menuId;
	private Integer parentMenuId;
	private String menuName;
	private Integer displayOrder;
	
	private List<Menu> childMenu;
	
	public Menu() {
	}
	
	public Menu(Integer menuId, Integer parentMenuId, String menuName, Integer displayOrder) {
		this.menuId = menuId;
		this.parentMenuId = parentMenuId;
		this.menuName = menuName;
		this.displayOrder = displayOrder;
	}
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public List<Menu> getChildMenu() {
		if (this.childMenu == null) {
			this.childMenu = new ArrayList<>();
		}
		return childMenu;
	}
	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}
	public void addChildMenu(Menu menu) {
		if (this.childMenu == null) {
			this.childMenu = new ArrayList<>();
		}
		this.childMenu.add(menu);
	}
	public boolean hasChildMenu() {
		return this.childMenu != null && this.childMenu.size() > 0;
	}
}
