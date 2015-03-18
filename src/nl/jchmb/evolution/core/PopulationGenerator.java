package nl.jchmb.evolution.core;

public class PopulationGenerator<T> implements Generator<Population<T>> {
	private ListGenerator<T> generator;
	
	public PopulationGenerator(Generator<T> generator, int size) {
		this.generator = new ListGenerator<T>(generator, size);
	}
	
	@Override
	public Population<T> generate() {
		return new Population<T>(generator.generate());
	}
}
