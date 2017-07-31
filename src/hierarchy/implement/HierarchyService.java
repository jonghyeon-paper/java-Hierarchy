package hierarchy.implement;

import java.util.Collections;
import java.util.List;

import hierarchy.concept.HierarchyConcept;

public class HierarchyService implements HierarchyConcept<HierarchyObject> {

	@Override
	public Object createHierarchyObject(List<HierarchyObject> list) {
		HierarchyObject dummyTop = new HierarchyObject();
		dummyTop.setMenuId(-1);
		dummyTop.setParentMenuId(-1);
		dummyTop.setMenuName("TOP");
		
		for (int i = list.size() - 1; i > -1; i--) {
			HierarchyObject temporary = list.get(i);
			if (temporary.getParentMenuId() != null) {
				for (int j = list.size() - 2; j > -1; j--) {
					HierarchyObject target = list.get(j);
					if (target.getMenuId().equals(temporary.getParentMenuId())) {
						target.addChild(temporary);
						break;
					}
				}
			} else {
				dummyTop.addChild(temporary);
			}
			list.remove(i);
		}
		sortInternalObject(dummyTop);
		return dummyTop;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sortInternalObject(HierarchyObject item) {
		if (item.hasChildMenu()) {
			List<HierarchyObject> itemList = (List<HierarchyObject>) item.getChild();
			for (int i = 0; i < itemList.size(); i++) {
				// recursive
				sortInternalObject(itemList.get(i));
				
				int lowestValueIndex = i;
				for (int j = i + 1; j < itemList.size(); j++) {
					if (itemList.get(j).getDisplayOrder() < itemList.get(lowestValueIndex).getDisplayOrder()) {
						lowestValueIndex = j;
					}
				}
				// swap
				if (i != lowestValueIndex) {
					Collections.swap(itemList, i, lowestValueIndex);
				}
			}
		}
	}

}
