package dev.npex42.almond.core;

import static org.lwjgl.glfw.GLFW.*;

public class Window extends IWindow {
	private long id;
	protected Window(long id) {
		this.id = id;
	}
	
	public void update() {
		
		glfwPollEvents();
		if(glfwGetWindowAttrib(id, GLFW_ICONIFIED) == GLFW_TRUE) {return;}
		glfwSwapBuffers(id);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(id);
	}
	
	public int width() {
		int[] x = {0};
		int[] y = {0};
		glfwGetWindowSize(id, x, y);
		return x[0];
	}
	
	public int height() {
		int[] x = {0};
		int[] y = {0};
		glfwGetWindowSize(id, x, y);
		return y[0];
	}
	
	
	public boolean isKeyDown(int key) {
		return glfwGetKey(id, key) == GLFW_PRESS;
	}
	
	public void setTitle(String title) {
		glfwSetWindowTitle(id, title);
	}
}
