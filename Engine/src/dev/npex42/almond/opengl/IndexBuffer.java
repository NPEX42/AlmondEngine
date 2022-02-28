package dev.npex42.almond.opengl;

import static org.lwjgl.opengl.GL46.*;

public class IndexBuffer {
	private int id, length;
	
	public IndexBuffer() {
		this.id = glGenBuffers();
	}
	
	public void bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
	}
	
	public void setInts(int[] indices) {
		bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);
		length = indices.length;
	}
	
	public int length() {
		return length;
	}
 }
