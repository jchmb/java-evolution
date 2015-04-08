package nl.jchmb.evolution.experiments.math;

public class PolynomialFunctionEvaluator extends FormulaEvaluator {
	private float a;
	private float b;
	private float c;
	private float d;
	
	public PolynomialFunctionEvaluator(
			float a,
			float b,
			float c,
			float d
	) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	@Override
	protected float evaluate(float x) {
		return a * x * x * x + b * x * x + c * x + d;
	}
}
