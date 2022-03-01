package dev.npex42.almond.renderer;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import dev.npex42.almond.common.IO;
import dev.npex42.almond.common.Logger;

public class ModelLoader {
	private static enum State {
		Start,
		Tris, Verts, TexCoords,
	}
	
	private State state = State.Start;
	
	private ArrayList<Integer> tris = new ArrayList<>();
	private ArrayList<Float> verts = new ArrayList<>();
	private ArrayList<Float> uvs = new ArrayList<>();
	
	
	public Mesh loadMdl(String path) throws RuntimeException {
		String[] lines = IO.loadString(path).split("\r\n");
		tris.clear();
		verts.clear();
		uvs.clear();
		for(String line : lines) {
			if(line.startsWith("@vertices"))  { state = State.Verts;     continue;}
			if(line.startsWith("@uvs"))       { state = State.TexCoords; continue;}
			if(line.startsWith("@triangles")) { state = State.Tris;      continue;}
			
			
			
			switch(state) {
			case Start: break;
			case Verts: processVertices(line); break;
			case TexCoords: processTexCoords(line); break;
			case Tris: processTris(line); break;
			}
		}
		
		state = State.Start;
		
		
		
		return Mesh.from(verts, uvs, tris);
	}


	private void processVertices(String line) {
		if(line.isBlank()) {return;}
		for(String value : line.split(",")) {
			Logger.log("Processing '%s' of '%s'", value, line);
			verts.add(Float.parseFloat(value.trim()));
		}
	}
	
	private void processTexCoords(String line) {
		if(line.isBlank()) {return;}
		for(String value : line.split(",")) {
			uvs.add(Float.parseFloat(value.trim()));
		}
	}
	
	private void processTris(String line) {
		if(line.isBlank()) {return;}
		for(String value : line.split(",")) {
			tris.add(Integer.parseInt(value.trim()));
		}
	}
	
}
