package nl.jchmb.evolution.experiments.math;

import java.util.HashMap;
import java.util.Map;

import nl.jchmb.evolution.core.Evaluator;
import nl.jchmb.math.tree.Tree;

public abstract class FormulaEvaluator implements Evaluator<Tree<Float>> {
	@Override
	public float evaluate(Tree<Float> phenotype) {
		float realY, predictedY;
		float error = 0.0f;
		float score;
		Map<String, Float> model = new HashMap<String, Float>();
		
		for (float x = -10.0f; x <= 10.0f; x += 0.5f) {
			realY = evaluate(x);
			model.put("x", x);
			predictedY = phenotype.evaluate(model);
			error += (realY - predictedY) * (realY - predictedY);
		}
		
		score = 1 / (error + 1);
		return 1 - score;
	}
	
	protected abstract float evaluate(float x);
}
