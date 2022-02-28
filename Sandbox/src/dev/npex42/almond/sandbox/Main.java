package dev.npex42.almond.sandbox;

import java.awt.Color;

import org.lwjgl.glfw.GLFW;

import dev.npex42.almond.core.IWindow;
import dev.npex42.almond.core.Window;
import dev.npex42.almond.glfw.Wrapper;
import dev.npex42.almond.opengl.GLRenderer;
import dev.npex42.almond.opengl.Shader;
import dev.npex42.almond.opengl.Texture2D;
import dev.npex42.almond.renderer.IRenderer;
import dev.npex42.almond.renderer.Material;
import dev.npex42.almond.renderer.Mesh;

public class Main {
	private static Window window;
	private static IRenderer r;
	
	private static float vertices[] = {
		     0.5f,  0.5f, 0.0f,  // top right    - 0
		     0.5f, -0.5f, 0.0f,  // bottom right - 1
		    -0.5f, -0.5f, 0.0f,  // bottom left  - 2
		    -0.5f,  0.5f, 0.0f   // top left     - 3 
	};
	
	private static float[] uvs = {
			1,1, // 0
			1,0, // 1
			0,0, // 2
			0,1  // 3
	};
	
	private static float[] colors = {
			1,1,1,1,
			1,1,0,1,
			1,0,1,1,
			0,1,1,1,
	};
	
	private static int indices[] = {  // note that we start from 0!
		    0, 1, 3,   // first triangle
		    1, 2, 3    // second triangle
	};
	
	public static void main(String[] args) {
		Wrapper.initialize();
		window = IWindow.constructWindow(480, 320, "Almond Engine - Sandbox");
		Mesh triangle = new Mesh(vertices, uvs, colors, indices);
		r = new GLRenderer();
		Shader shader = new Shader("res/shaders/base.vert.glsl", "res/shaders/base.frag.glsl");
		shader.bind();
		
		Texture2D test = Texture2D.load("res/textures/test.png", true);
		test.ScaleNearest();
		
		Material mat = new Material(shader, test);
		mat.bind();
		long frameStart, frameEnd;
		while(!window.shouldClose()) {
			frameStart = System.nanoTime();
			r.clear(Color.BLUE);
			if(window.isKeyDown(GLFW.GLFW_KEY_1)) {
				r.drawWireFrame(triangle);
			} else {
				r.drawMesh(triangle);
			}
			frameEnd = System.nanoTime();
			
			long frameTime = frameEnd - frameStart;
			frameTime /= 100;
			float deltaTime = (float) frameTime / 1000000f;
			
			window.setTitle("Almond Engine - Sandbox - Triangles Drawn: "+r.getStatistics().trisDrawn()+" - Frame Time: "+frameTime+"us (dt: "+String.format("%01.6f", deltaTime)+")");
			r.resetStats();
			window.update();
		}
	}
}
