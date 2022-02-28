package dev.npex42.almond.core;
import static org.lwjgl.glfw.GLFW.*;

import dev.npex42.almond.opengl.OpenGL;

public abstract class IWindow {
	public static Window constructWindow(int width, int height, String title)
	throws RuntimeException {
		long window = glfwCreateWindow(width, height, title, 0, 0);
		if(window == 0) {throw new RuntimeException("Failed To Create Window '"+title+"'");}
		glfwMakeContextCurrent(window);
		// Make the window visible
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		OpenGL.init();
		return new Window(window);
	}
}
