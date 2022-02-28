package dev.npex42.almond.opengl;
import static org.lwjgl.opengl.GL46.*;

import java.nio.ByteBuffer;
public class VertexBuffer {
	private int id;
	public VertexBuffer() {
		this.id = glGenBuffers();
	}
	
	public void setFloats(float[] values) {
		bind();
		glBufferData(GL_ARRAY_BUFFER, values, GL_STATIC_DRAW);
	}
	
	public void setFloatLayout(int index, int size) {
		setFloatLayout(index, size, 0, 0);
	}
	
	public void setFloatLayout(int index, int size, int stride, int offset) {
		glVertexAttribPointer(index, size, GL_FLOAT, false, stride, offset);
	}

	
	public void bind() {
		glBindBuffer(GL_ARRAY_BUFFER, id);
	}
	
	
}
