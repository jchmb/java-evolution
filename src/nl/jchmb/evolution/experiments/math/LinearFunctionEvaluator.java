package nl.jchmb.evolution.experiments.math;

public class LinearFunctionEvaluator extends FormulaEvaluator {
	private float a, b;
	
	public LinearFunctionEvaluator(float a, float b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	protected float evaluate(float x) {
		return a * x + b;
	}
}
