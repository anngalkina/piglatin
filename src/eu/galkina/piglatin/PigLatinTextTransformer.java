package eu.galkina.piglatin;

import java.util.Arrays;

/**
 * 
 * @author Anna Galkina
 * 
 * Takes an English text as an input in constructor, splits it by spaces 
 * and translates each word to Pig Latin
 *
 */

public class PigLatinTextTransformer {
	
	private String text;
	
	
	public PigLatinTextTransformer(String inputText) {
		this.text = inputText;
	}
	
	public String getTransformedText() {
		return text;
	}
	
	public PigLatinTextTransformer transform() {
		if (text == null || text.trim().isEmpty()) {
			text = "";
			return this;
		}
		StringBuilder transformationResult = new StringBuilder();
		Arrays.stream(text.split(" ")).forEach(w -> transformationResult.append(new PigLatinWordTransformer(w).transform()).append(" "));
		text = transformationResult.toString().trim();
		return this;
	}
	
}
