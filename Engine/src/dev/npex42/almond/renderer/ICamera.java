package dev.npex42.almond.renderer;

import org.joml.Matrix4f;

public interface ICamera {
	public void setFar(double value);
	public void setNear(double value);
	public void setFov(double angle);
	public void setWidth(double value);
	public void setHeight(double value);
	
	public Matrix4f getProjection();
	public Matrix4f getView();
	public void translate(float x, float y, float z);
}
