package hierarchy.concept;

public interface HierarchyObjectConcept {

	Object getKey();
	void setKey(Object object);
	Object getParentKey();
	void addChild(Object object);
	Object getChild();
	Integer getDisplayOrderVariable();
}
