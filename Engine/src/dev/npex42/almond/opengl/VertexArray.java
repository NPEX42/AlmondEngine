package dev.npex42.almond.opengl;

import static org.lwjgl.opengl.GL46.*;

public class VertexArray {
	private int id;
	public VertexArray() {
		this.id = glGenVertexArrays();
	}
	
	public void bind() {
		glBindVertexArray(id);
	}
	
	public void unbind() {
		glBindVertexArray(0);
	}
	
	public void enableAttribIndex(int index) {
		glEnableVertexAttribArray(index);
	}
}
