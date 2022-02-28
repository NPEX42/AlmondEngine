package dev.npex42.almond.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IO {
	public static String loadString(String path) throws RuntimeException {
		try {
			return Files.readString(Paths.get(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
