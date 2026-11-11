package libreria_p69;

public class generic<T,S> {
	private T attributeT1, attributeT2;
	private S attributeS1, attributeS2;
	
	public generic() {
		super();
		// TODO Auto-generated constructor stub
		this.attributeT1 = null;
		this.attributeT2 = null;
		this.attributeS1 = null;
		this.attributeS2 = null;	
	}
	
	public generic(T attributeT1) {
		super();
		this.attributeT1 = attributeT1;
	}
	public generic(T attributeT1, S attributeS1) {
		super();
		this.attributeT1 = attributeT1;
		this.attributeS1 = attributeS1;
	}
	public generic(T attributeT1, T attributeT2, S attributeS1) {
		super();
		this.attributeT1 = attributeT1;
		this.attributeT2 = attributeT2;
		this.attributeS1 = attributeS1;
	}
	public generic(T attributeT1, T attributeT2, S attributeS1, S attributeS2) {
		super();
		this.attributeT1 = attributeT1;
		this.attributeT2 = attributeT2;
		this.attributeS1 = attributeS1;
		this.attributeS2 = attributeS2;
	}
	
	
	public T getAttributeT1() {
		return attributeT1;
	}
	public void setAttributeT1(T attributeT1) {
		this.attributeT1 = attributeT1;
	}
	public T getAttributeT2() {
		return attributeT2;
	}
	public void setAttributeT2(T attributeT2) {
		this.attributeT2 = attributeT2;
	}
	public S getAttributeS1() {
		return attributeS1;
	}
	public void setAttributeS1(S attributeS1) {
		this.attributeS1 = attributeS1;
	}
	public S getAttributeS2() {
		return attributeS2;
	}
	public void setAttributeS2(S attributeS2) {
		this.attributeS2 = attributeS2;
	}

	@Override
	public String toString() {
		return "generic [attributeT1=" + attributeT1 + ", attributeT2=" + attributeT2 + ", attributeS1=" + attributeS1
				+ ", attributeS2=" + attributeS2 + "]";
	}
	
	
	
	
	
	
	
	
	
}



