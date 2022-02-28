package dev.npex42.almond.renderer;

import dev.npex42.almond.opengl.IndexBuffer;
import dev.npex42.almond.opengl.VertexArray;
import dev.npex42.almond.opengl.VertexBuffer;

public class Mesh {
	private VertexArray vao;
	private VertexBuffer positions, uvs, tint;
	private IndexBuffer indices;
	private int vertexCount = 0;
	
	public Mesh(float[] vertices, float[] texCoords, float[] tints, int[] tris) {
		
		this.vao = new VertexArray();
		this.vao.bind();
		
		this.positions = new VertexBuffer();
		this.positions.setFloats(vertices);
		this.positions.setFloatLayout(0, 3);
		vao.enableAttribIndex(0);
		
		uvs = new VertexBuffer();
		uvs.setFloats(texCoords);
		uvs.setFloatLayout(1, 2);
		
		vao.enableAttribIndex(1);
		
		tint = new VertexBuffer();
		tint.setFloats(tints);
		tint.setFloatLayout(2, 4);
		
		vao.enableAttribIndex(2);
		
		indices = new IndexBuffer();
		indices.setInts(tris);
	
	}
	
	public void bind() {
		vao.bind();
	}
	
	public int vertexCount() {
		return indices.length();
	}
}
