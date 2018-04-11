package com.java.Helper;

import java.util.Random;

public class TokenGenerator {

	public static int getToken() {
		Random random = new Random();
		int token = random.nextInt();
		return token;
	}
}
