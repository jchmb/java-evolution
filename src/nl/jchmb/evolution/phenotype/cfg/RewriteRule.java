package nl.jchmb.evolution.phenotype.cfg;

import java.util.List;

public interface RewriteRule<Token> {
	public List<Token> rewrite(List<Token> tokens);
}
