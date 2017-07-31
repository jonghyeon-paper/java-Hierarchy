package hierarchy.concept;

import java.util.List;

public interface HierarchyConcept<T extends HierarchyObjectConcept> {

	Object createHierarchyObject(List<T> list);
	void sortInternalObject(T item);
}
