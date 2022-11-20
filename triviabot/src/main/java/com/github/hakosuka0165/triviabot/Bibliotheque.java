package com.github.hakosuka0165.triviabot;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Bibliotheque {
	public static Question[] library = {
			new Question(false, "The chairs in the House of Commons are red."),
			new Question(true, "Washington D.C. is the most populated city in the U.S."),
			new Question(false, "Pluto is the smallest planet in the Solar System."),
			new Question(false, "Coldplay are a dance group.")
	};
	public static Question[] ShuffleQuestions(Question[] lib) {
		Random rnd = ThreadLocalRandom.current();
		for(int i = lib.length - 1; i > 0; --i) {
			int index = rnd.nextInt(i + 1);
			
			Question a = lib[index];
			lib[index] = lib[i];
			lib[i] = a;
		}
		return lib;
	}
}
