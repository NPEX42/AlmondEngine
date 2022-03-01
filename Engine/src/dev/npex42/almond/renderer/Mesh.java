package dev.npex42.almond.renderer;

import java.util.List;

import dev.npex42.almond.opengl.IndexBuffer;
import dev.npex42.almond.opengl.VertexArray;
import dev.npex42.almond.opengl.VertexBuffer;

public class Mesh {
	private VertexArray vao;
	private VertexBuffer positions, uvs;
	private IndexBuffer indices;
	private int vertexCount = 0;
	
	public Mesh(float[] vertices, float[] texCoords, int[] tris) {
		
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
		
		
		vao.enableAttribIndex(2);
		
		indices = new IndexBuffer();
		indices.setInts(tris);
	
	}
	
	public static Mesh from(List<Float> positions2, List<Float> texCoords, List<Integer> faces) {
		float[] p = new float[positions2.size()];
		float[] t = new float[texCoords.size()];
		int[] ind = new int[faces.size()];
		
		for(int i = 0; i < p.length; i++) {
			p[i] = positions2.get(i);
		}
		
		for(int i = 0; i < t.length; i++) {
			t[i] = texCoords.get(i);
		}
		
		for(int i = 0; i < ind.length; i++) {
			ind[i] = faces.get(i);
		}
		
		return new Mesh(p, t, ind);
	}

	public void bind() {
		vao.bind();
	}
	
	public int vertexCount() {
		return indices.length();
	}
}
