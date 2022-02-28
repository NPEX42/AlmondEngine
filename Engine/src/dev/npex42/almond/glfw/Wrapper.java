package dev.npex42.almond.glfw;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

public class Wrapper {
	public static void initialize() throws RuntimeException {
		GLFWErrorCallback.createPrint(System.err).set();
		if(!glfwInit()) { throw new RuntimeException("Failed To Initialize GLFW..."); } 
		
		
	}
}
