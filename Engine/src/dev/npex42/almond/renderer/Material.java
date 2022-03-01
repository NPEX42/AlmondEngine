package dev.npex42.almond.renderer;

import dev.npex42.almond.opengl.Shader;
import dev.npex42.almond.opengl.Texture2D;

public class Material {
	
	private static final int ALBEDO = 0, SPECULAR = 1;
	
	
	private IShader shader;
	private ITexture2D albedo;
	
	public Material(IShader shdr, ITexture2D _albedo) {
		shader = shdr;
		albedo = _albedo;
		albedo.setUnit(ALBEDO);
	}
	
	public void bind() {
		shader.bind();
		shader.setTexture("uAlbedo", albedo);
	}
	
}
