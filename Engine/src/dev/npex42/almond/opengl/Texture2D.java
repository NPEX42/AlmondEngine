package dev.npex42.almond.opengl;
import static org.lwjgl.opengl.GL46.*;

import java.net.BindException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.stb.STBImage;

import dev.npex42.almond.common.Logger;
import dev.npex42.almond.renderer.ITexture2D;
public class Texture2D implements ITexture2D {
	private int textureID;
	private int textureUnit;
	private int width, height, channels;
	
	public Texture2D() {
		textureID = glGenTextures();
	}
	
	public void bind() {
		glBindTexture(GL_TEXTURE_2D, textureID);
	}
	
	public void setPixels(int width, int height, FloatBuffer values) {
		bind();
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_FLOAT, values);
		this.height = height;
		this.width = width;
		this.channels = 3;
	}
	
	
	public static Texture2D white() {
		
		return Texture2D.load("res/textures/white.png", false);
	}
	
	public static Texture2D missing() {
		return Texture2D.load("res/textures/missing.png", false);
	}
	
	public void generateMipMaps() {
		bind();
		glGenerateMipmap(GL_TEXTURE_2D);
	}
	
	
	public void ScaleLerp() {
		bind();
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	}
	
	public void ScaleNearest() {
		bind();
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	}
	
	
	public static Texture2D load(String path, boolean flip) {
		int[] width = {0};
		int[] height = {0};
		int[] channels = {0};
		STBImage.nstbi_set_flip_vertically_on_load(flip ? 1 : 0);
		FloatBuffer img = STBImage.stbi_loadf(path, width, height, channels, 3);
		
		Logger.log("Loaded Texture '%s', (%dx%dx%d)", path, width[0], height[0], channels[0]);
		
		Texture2D tex = new Texture2D();
		tex.setPixels(width[0], height[0], img);
		tex.generateMipMaps();
		
		return tex;
	}
	
	public void makeActive(int unit) {
		
		
		this.textureUnit = GL_TEXTURE0 + unit;
		glActiveTexture(this.textureUnit);
		bind();
	}

	@Override
	public int id() {
		// TODO Auto-generated method stub
		return textureID;
	}

	@Override
	public int unit() {
		// TODO Auto-generated method stub
		return textureUnit;
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int channels() {
		// TODO Auto-generated method stub
		return channels;
	}

	@Override
	public void setUnit(int unit) {
		textureUnit = GL_TEXTURE0 + unit;
	}
}
