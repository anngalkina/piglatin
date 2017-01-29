package eu.galkina.piglatin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Anna Galkina
 * 
 * Takes an English word as an input in translator and translates it to Pig Latin.
 * In case word contains hyphens, splits the word by hyphens and calls itself for each part recursively.
 *
 */
public class PigLatinWordTransformer {
	private static Pattern punctuationRegexPattern = Pattern.compile("[.,:;\"!?\'’]+");
	private static Pattern consonantRegexPattern = Pattern.compile("[bcdfghjklmnpqrstvwxyz].*");
	private static Pattern vowelRegexPattern = Pattern.compile("[aeoui].*");
	private static Pattern wayRegexPattern = Pattern.compile(".*way");
	private static Pattern capitalLetterRegexPattern = Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZ]");

	
	private String word;
	private Map<Integer, String> punctuation = new HashMap<Integer, String>();
	private List<Integer> capitalization = new ArrayList<Integer>();
	private boolean transformed;
	
	
	public PigLatinWordTransformer(String inputWord) {
		word = inputWord;
	}
	
	public String transform() {
		StringBuilder transformationResult = new StringBuilder();
		Arrays.stream(word.split("\\-")).forEach(t -> transformationResult.append(new PigLatinWordTransformer(t).transformWord()).append("-"));
		transformationResult.delete(transformationResult.length()-1, transformationResult.length());
		word = transformationResult.toString();
		return word;
	}
	
	
	private PigLatinWordTransformer cleanPunctuation() {
		Matcher m = punctuationRegexPattern.matcher(word);
		while (m.find()) {
			punctuation.put(word.length() - m.start(), word.substring(m.start(), m.end()));
		}
		punctuation.values().forEach(v -> word = word.replace(v, ""));
		return this;
	}

	private PigLatinWordTransformer cleanCapitalization() {
		Matcher m = capitalLetterRegexPattern.matcher(word);
		while (m.find()) {
			capitalization.add(m.start());
			word = new StringBuilder(word.substring(0, m.start()))
					.append(word.substring(m.start(), m.end()).toLowerCase())
					.append(word.substring(m.end(), word.length()))
					.toString();
		}
		return this;
	}
	
	private PigLatinWordTransformer checkEndingWay() {
		if (transformed) return this;
		Matcher m = wayRegexPattern.matcher(word);
		if (m.matches()) {
			transformed = true;
		}
		return this;
	}
	
	private PigLatinWordTransformer transformStartingConsonant() {
		if (transformed) return this;
		Matcher m = consonantRegexPattern.matcher(word);
		if (m.matches()) {
			word = new StringBuilder(word.substring(1, word.length()))
					.append(word.substring(0, 1))
					.append("ay")
					.toString();
			transformed = true;
		}
		return this;
	}
	
	private PigLatinWordTransformer transformStartingVowel() {
		if (transformed) return this;
		Matcher m = vowelRegexPattern.matcher(word);
		if (m.matches()) {
			word = new StringBuilder(word).append("way").toString();
			transformed = true;
		}
		return this;
	}
	
	private PigLatinWordTransformer restoreCapitalization() {
		capitalization.forEach(pos -> {word = new StringBuilder(word.substring(0, pos))
				.append(word.substring(pos, pos+1).toUpperCase())
				.append(word.substring(pos+1, word.length()))
				.toString();});
		return this;
	}
	
	private PigLatinWordTransformer restorePunctuation() {
		punctuation.forEach((pos, value) -> {word = new StringBuilder(word).insert(word.length() - pos + value.length(), value).toString();});
		return this;
	}
	
	private 	String transformWord() {
		 cleanPunctuation()
		.cleanCapitalization()
		.checkEndingWay()
		.transformStartingConsonant()
		.transformStartingVowel()
		.restoreCapitalization()
		.restorePunctuation();
		 return word;
	}
	
	
}
