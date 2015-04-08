package nl.jchmb.evolution.repository;

public abstract class Source<T> {
	private String label;
	
	public Source(String label) {
		this.label = label;
	}
	
	public abstract T getObject();
	
	public String getLabel() {
		return label;
	}
}
