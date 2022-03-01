package dev.npex42.almond.renderer;

import org.joml.*;

public interface IShader {
	public IShader createFromFiles(String vertPath, String fragPath);
	public void bind();
	
	public void setInt(String name, int x);
	public void setFloat(String name, float x);
	
	public void setVector2i(String name, Vector2i vec);
	public void setVector3i(String name, Vector3i vec);
	public void setVector4i(String name, Vector4i vec);
	public void setVector2f(String name, Vector2f vec);
	public void setVector3f(String name, Vector3f vec);
	public void setVector4f(String name, Vector4f vec);
	
	public void setMatrix2f(String name, Matrix2f mat);
	public void setMatrix3f(String name, Matrix3f mat);
	public void setMatrix4f(String name, Matrix4f mat);
	
	public void setTexture(String name, ITexture2D tex);
}
