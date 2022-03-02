package dev.npex42.almond.renderer;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transform {
	public static final Vector3f 
		POS_X = new Vector3f(1, 0, 0),
		POS_Y = new Vector3f(0, 1, 0),
		POS_Z = new Vector3f(0, 0, 1),
		ZERO =  new Vector3f(0, 0, 0),
		ONE  =  new Vector3f(1, 1, 1);
		
		

	private Vector3f position = ZERO, rotation = ZERO, scale = ONE; 
	
	public Matrix4f getTransformMat() {
		Matrix4f mat = new Matrix4f()
				.translate(position)
				.rotate(rotation.x, POS_X)
				.rotate(rotation.y, POS_Y)
				.rotate(rotation.z, POS_Z)
				.scale(scale);
		
		return mat;
	}
	
	public void setPosition(Vector3f pos) {
		position = pos;
	}
	
	public void setRotation(Vector3f rot) {
		rotation = rot;
	}
	
	public void setScale(Vector3f _scale) {
		scale = _scale;
	}
	
	public void rotate(float dx, float dy, float dz) {
		rotation.add(dx, dy, dz);
	}
	
	public void translate(float dx, float dy, float dz) {
		position.add(dx, dy, dz);
	}
	
	public static Transform origin() {
		Transform t = new Transform();
		return t;
	}
}
