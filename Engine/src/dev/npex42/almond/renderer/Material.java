package dev.npex42.almond.renderer;

import dev.npex42.almond.opengl.Shader;
import dev.npex42.almond.opengl.Texture2D;

public class Material {
	
	private static final int ALBEDO = 0, SPECULAR = 1;
	
	
	private Shader shader;
	private Texture2D albedo;
	
	public Material(Shader shdr, Texture2D _albedo) {
		shader = shdr;
		albedo = _albedo;
	}
	
	public void bind() {
		shader.bind();
		shader.setTexture2D("uAlbedo", ALBEDO, albedo);
	}
	
}
