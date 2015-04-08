package nl.jchmb.evolution.phenotype.cfg;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Grammar<Token> {
	private Set<RewriteRule<Token>> rewriteRules;
	
	public Grammar(Set<RewriteRule<Token>> rewriteRules) {
		this.rewriteRules = rewriteRules;
	}
	
	public Grammar() {
		this(new HashSet<RewriteRule<Token>>());
	}
	
	public Set<List<Token>> getPossibleRewrites(List<Token> sentence) {
		Set<List<Token>> rewrites = new HashSet<List<Token>>();
	}
}
