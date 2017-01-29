package eu.galkina.piglatin;

/**
 * 
 * @author Anna Galkina
 * 
 * Creates an instance of PigLatinTextTransformer with the given input text
 * and prints the result of the transformation to Pig Latin to the console.
 *
 */
public class PigLatinRunner {
	public static void main(String[] args) {
		PigLatinTextTransformer t = new PigLatinTextTransformer("One language, is never enough; My hovercraft: is full of \"eels\"...").transform();
		System.out.println(t.getTransformedText());
	}
}
