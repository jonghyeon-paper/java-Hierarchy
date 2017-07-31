package hierarchy.implement;

import java.util.ArrayList;
import java.util.List;

import hierarchy.concept.HierarchyObjectConcept;

public class HierarchyObject implements HierarchyObjectConcept {
	
	private Integer menuId;
	private Integer parentMenuId;
	private String menuName;
	private Integer displayOrder;
	
	private List<HierarchyObject> childMenu;
	
	public HierarchyObject() {
	}
	
	public HierarchyObject(Integer menuId, Integer parentMenuId, String menuName, Integer displayOrder) {
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
	public List<HierarchyObject> getChildMenu() {
		if (this.childMenu == null) {
			this.childMenu = new ArrayList<>();
		}
		return childMenu;
	}
	public void setChildMenu(List<HierarchyObject> childMenu) {
		this.childMenu = childMenu;
	}
	public void addChildMenu(HierarchyObject menu) {
		if (this.childMenu == null) {
			this.childMenu = new ArrayList<>();
		}
		this.childMenu.add(menu);
	}
	public boolean hasChildMenu() {
		return this.childMenu != null && this.childMenu.size() > 0;
	}

	@Override
	public Object getKey() {
		return getMenuId();
	}

	@Override
	public void setKey(Object object) {
		setMenuId(Integer.parseInt(String.valueOf(object)));
	}

	@Override
	public Object getParentKey() {
		return getParentMenuId();
	}

	@Override
	public void addChild(Object object) {
		addChildMenu((HierarchyObject) object);
	}

	@Override
	public Object getChild() {
		return getChildMenu();
	}
	
	@Override
	public Integer getDisplayOrderVariable() {
		return getDisplayOrder();
	}

}
