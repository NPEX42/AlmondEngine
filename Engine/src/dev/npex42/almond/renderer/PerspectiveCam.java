package dev.npex42.almond.renderer;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class PerspectiveCam implements ICamera {
	private double far = 1000, near = 1, fov = 90;
	private float height, width;
	private Vector2f position, rotation;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Matrix4f getProjection() {
		Matrix4f mat = new Matrix4f().identity();
		mat = mat.perspective((float)fov, width/height, (float)near, (float)far);
		return mat;
	}

	@Override
	public Matrix4f getView() {
		return new Matrix4f().identity();
	}

	@Override
	public void translate(float x, float y, float z) {
		
	}
	
}
