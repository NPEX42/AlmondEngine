package dev.npex42.almond.sandbox;

import java.awt.Color;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import dev.npex42.almond.core.IWindow;
import dev.npex42.almond.core.Window;
import dev.npex42.almond.glfw.Wrapper;
import dev.npex42.almond.opengl.GLRenderer;
import dev.npex42.almond.opengl.Shader;
import dev.npex42.almond.opengl.Texture2D;
import dev.npex42.almond.renderer.ICamera;
import dev.npex42.almond.renderer.IRenderer;
import dev.npex42.almond.renderer.IShader;
import dev.npex42.almond.renderer.Material;
import dev.npex42.almond.renderer.Mesh;
import dev.npex42.almond.renderer.ModelLoader;
import dev.npex42.almond.renderer.OrthoCamera;
import dev.npex42.almond.renderer.PerspectiveCam;
import dev.npex42.almond.renderer.Transform;

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
			1,1,1,0,
			1,1,1,1,
			1,1,1,1,
			1,1,1,1,
	};
	
	private static int indices[] = {  // note that we start from 0!
		    0, 1, 3,   // first triangle
		    1, 2, 3    // second triangle
	};
	
	public static void main(String[] args) {
		
		Wrapper.initialize();
		window = IWindow.constructWindow(480, 320, "Almond Engine - Sandbox");
		
		ModelLoader loader = new ModelLoader();
		Mesh rectangle = loader.loadMdl("res/models/rect.mdl");
		
		r = new GLRenderer();
		
		IShader shader = new Shader("res/shaders/base.vert.glsl", "res/shaders/base.frag.glsl");
		shader.bind();
		
		Texture2D test = Texture2D.missing();
		test.ScaleNearest();
		
		ICamera camera = new OrthoCamera(480, 320);
		

		Material mat = new Material(shader, test);
		mat.bind();
		
		Transform transform = new Transform();
		transform.setScale(new Vector3f(100, 100, 0));
		transform.setPosition(new Vector3f(240, 160, 0));
		
		long frameStart, frameEnd;
		
		while(!window.shouldClose()) {
			
			frameStart = System.nanoTime();
			
			Matrix4f mvp = new Matrix4f().identity();
			mvp.mul(camera.getProjection());
			mvp.mul(transform.getTransformMat());
			
			shader.setMatrix4f("uMVP", mvp);
			
			r.clear(Color.BLUE);
			
			if(window.isKeyDown(GLFW.GLFW_KEY_1)) {
				r.drawWireFrame(rectangle);
			} else {
				r.drawMesh(rectangle);
			}
			
			frameEnd = System.nanoTime();
			
			long frameTime = frameEnd - frameStart;
			frameTime /= 100;
			float deltaTime = (float) frameTime / 1000000f;
			
			if(window.isKeyDown(GLFW.GLFW_KEY_A)) {
				transform.translate(-100f * deltaTime, 0, 0);
			}
			
			if(window.isKeyDown(GLFW.GLFW_KEY_D)) {
				transform.translate(100f * deltaTime, 0, 0);
			}
			
			if(window.isKeyDown(GLFW.GLFW_KEY_W)) {
				transform.translate(0, -100f * deltaTime, 0);
			}
			
			if(window.isKeyDown(GLFW.GLFW_KEY_S)) {
				transform.translate(0, 100f * deltaTime, 0);
			}
			
			if(window.isKeyDown(GLFW.GLFW_KEY_Q)) {
				transform.rotate(0, 0, (float) Math.toRadians(20) * deltaTime);
			}
			
			if(window.isKeyDown(GLFW.GLFW_KEY_E)) {
				transform.rotate(0, 0, (float) Math.toRadians(-20) * deltaTime);
			}
			
			window.setTitle("Almond Engine - Sandbox - Triangles Drawn: "+r.getStatistics().trisDrawn()+" - Frame Time: "+frameTime+"us (dt: "+String.format("%01.6f", deltaTime)+")");
			r.resetStats();
			window.update();
		}
	}
}
