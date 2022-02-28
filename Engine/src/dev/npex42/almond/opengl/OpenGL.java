package dev.npex42.almond.opengl;

import org.lwjgl.opengl.GL;

public class OpenGL  {
	private static boolean isInitialized = false;
	
	public static void init() {
		if(!isInitialized) {
			GL.createCapabilities();
			isInitialized = true;
		}
	}
	
	
}
