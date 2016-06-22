package io.lyonix.phoenix.api.utils;

public class MathsUtils {

	public static double round(double num, int multipleOf) {
		return Math.floor((num + (double) multipleOf / 2) / multipleOf) * multipleOf;
	}

}