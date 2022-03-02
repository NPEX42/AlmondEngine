package dev.npex42.almond.opengl;

import dev.npex42.almond.common.IO;
import dev.npex42.almond.common.Logger;
import dev.npex42.almond.renderer.IShader;
import dev.npex42.almond.renderer.ITexture2D;

import static org.lwjgl.opengl.GL46.*;

import java.util.HashMap;

import org.joml.*;
public class Shader implements IShader {
	
	private HashMap<String, Integer> uniforms = new HashMap<>();
	
	private int program_id = 0;
	
	public Shader(String vertPath, String fragPath) throws RuntimeException {
		String vertSrc = IO.loadString(vertPath);
		String fragSrc = IO.loadString(fragPath);
		int vertId = glCreateShader(GL_VERTEX_SHADER);
		int fragId = glCreateShader(GL_FRAGMENT_SHADER);
	
		glShaderSource(vertId, vertSrc);
		glShaderSource(fragId, fragSrc);
		
		glCompileShader(vertId);
		checkCompileErrors(vertId);
		glCompileShader(fragId);
		checkCompileErrors(fragId);
		
		
		program_id = glCreateProgram();
		glAttachShader(program_id, vertId);
		glAttachShader(program_id, fragId);
		
		glLinkProgram(program_id);
		checkLinkingErrors(program_id);
		
		glDeleteShader(vertId);
		glDeleteShader(fragId);
		
	}
	
	private void checkCompileErrors(int id) throws RuntimeException {
		if(glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {

			String msg = glGetShaderInfoLog(id);
			Logger.err("Shader Failed To Compile - %s", msg);
			
			throw new RuntimeException("Failed To Compile Shader. See Log For Details.");
		}
	}
	
	private void checkLinkingErrors(int id) throws RuntimeException {
		if(glGetShaderi(id, GL_LINK_STATUS) == GL_TRUE) {

			String msg = glGetProgramInfoLog(id);
			Logger.err("Shader Failed To Compile - %s", msg);
			
			throw new RuntimeException("Failed To Link Shader. See Log For Details.");
		}
	}
	
	@Override
	public void bind() {
		glUseProgram(program_id);
	}
	
	public void setInt(String name, int value) {
		glUniform1i(getUniform(name), value);
	}
	
	public void setTexture2D(String name, int unit, Texture2D texture) {
		texture.makeActive(unit);
		setInt(name, unit);
	}
	
	public void setMatrix3f(String name, Matrix3f mat) {
		float[] buf = new float[9];
		mat.get(buf);
		glUniform4fv(getUniform(name), buf);
	}
	
	public void setMatrix4f(String name, Matrix4f mat) {
		float[] buf = new float[16];
		glUniformMatrix4fv(getUniform(name), false,  mat.get(buf));
	}
	
	private int getUniform(String name) {
		if(uniforms.containsKey(name)) {
			return uniforms.get(name);
		} else {
			int loc = glGetUniformLocation(program_id, name);
			if(loc == -1) {
				Logger.warn("Failed To Locate Shader Uniform '%s'", name);
				uniforms.put(name, -1);
			} else {
				uniforms.put(name, loc);
			}
			
			return loc;
		}
	}

	@Override
	public IShader createFromFiles(String vertPath, String fragPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFloat(String name, float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVector2i(String name, Vector2i vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVector3i(String name, Vector3i vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVector4i(String name, Vector4i vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVector2f(String name, Vector2f vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVector3f(String name, Vector3f vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVector4f(String name, Vector4f vec) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMatrix2f(String name, Matrix2f mat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTexture(String name, ITexture2D tex) {
		if(tex instanceof Texture2D) {
			setTexture2D(name, tex.unit(), (Texture2D) tex);
		}
	}
	
	
}
