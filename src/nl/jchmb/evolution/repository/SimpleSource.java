package nl.jchmb.evolution.repository;

public class SimpleSource<T> extends Source<T> {
	private T object;
	
	public SimpleSource(T object, String label) {
		super(label);
		this.object = object;
	}
	
	public T getObject() {
		return object;
	}
}
