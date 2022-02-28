package dev.npex42.almond.opengl;

import dev.npex42.almond.common.IO;
import dev.npex42.almond.common.Logger;

import static org.lwjgl.opengl.GL46.*;

import java.util.HashMap;
public class Shader {
	
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
	
	private int getUniform(String name) {
		if(uniforms.containsKey(name)) {
			return uniforms.get(name);
		} else {
			int loc = glGetUniformLocation(program_id, name);
			if(loc == -1) {
				Logger.warn("Failed To Locate Shader Uniform '%s'", name);
			} else {
				uniforms.put(name, loc);
			}
			
			return loc;
		}
	}
	
	
}
