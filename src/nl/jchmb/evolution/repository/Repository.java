package nl.jchmb.evolution.repository;

import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
	private List<Source<T>> sources;
	
	public Repository() {
		sources = new ArrayList<Source<T>>();
	}
	
	public void add(Source<T> source) {
		sources.add(source);
	}
	
	public T getObject(int index) {
		return sources.get(index).getObject();
	}
	
	public String[] getLabels() {
		String[] labels = new String[sources.size()];
		for (int i = 0; i < labels.length; i++) {
			labels[i] = sources.get(i).getLabel();
		}
		return labels;
	}
}
