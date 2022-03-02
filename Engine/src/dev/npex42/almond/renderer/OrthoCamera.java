package dev.npex42.almond.renderer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class OrthoCamera implements ICamera {

	private double far = 1000, near = 1, fov = 90;
	private float height, width;
	private Transform transform = Transform.origin();
	
	public OrthoCamera(float _width, float _height) {
		width = _width;
		height = _height;
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
		return new Matrix4f().ortho(0, width, height, 0, -1000, 1000);
	}
	
	@Override
	public Matrix4f getView() {
		return new Matrix4f().identity();
	}

	@Override
	public void translate(float dx, float dy, float dz) {
		transform.translate(dx, dy, dz);
	}
	
}
