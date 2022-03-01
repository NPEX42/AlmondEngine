package dev.npex42.almond.renderer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class OrthoCamera implements ICamera {

	private double far = 1000, near = 1, fov = 90;
	private float height, width;
	private Vector2f position, rotation;
	
	public OrthoCamera(float _width, float _height) {
		width = _width;
		height = _height;
		
		position = new Vector2f(0, 0);
	}
	
	@Override
	public void setFar(double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNear(double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFov(double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(double value) {
		width = (float) value;
	}

	@Override
	public void setHeight(double value) {
		height = (float) value;
	}

	@Override
	public Matrix4f getProjection() {
		Matrix4f mat = new Matrix4f();
		mat = mat.ortho(0, width, 0, height, 1, -1);
		return mat;
	}
	
	@Override
	public Matrix4f getView() {
		Matrix4f mat = new Matrix4f();
		mat = mat.identity();
		return mat;
	}

	@Override
	public void translate(float x, float y, float z) {
		// TODO Auto-generated method stub
		
	}
	
}
