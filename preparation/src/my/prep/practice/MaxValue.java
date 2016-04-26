package my.prep.practice;

import java.util.Arrays;

public class MaxValue {
	int maxValue;
	Integer choices[];
	public MaxValue(int maxValue, Integer[] choices) {
		this.maxValue = maxValue;
		this.choices = choices;
	}
	@Override
	public String toString() {
		StringBuilder srtbuilder = new StringBuilder();
		srtbuilder.append("max vlaue is " + maxValue);
		srtbuilder.append("\nChoices are "+Arrays.toString(choices));
		return srtbuilder.toString();
	}
	
}