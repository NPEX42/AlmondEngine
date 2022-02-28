package dev.npex42.almond.opengl;

import java.awt.Color;

import dev.npex42.almond.renderer.IRenderer;
import dev.npex42.almond.renderer.Material;
import dev.npex42.almond.renderer.Mesh;
import dev.npex42.almond.renderer.RenderingStats;

import static org.lwjgl.opengl.GL46.*;

public class GLRenderer implements IRenderer {
	private int trisDrawn = 0;
	@Override
	public void clear(Color clr) {
		glClearColor(
				clr.getRed() / 255f,
				clr.getGreen() / 255f,
				clr.getBlue() / 255f,
				clr.getAlpha() / 255f
		);
		
		glClear(GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void drawMesh(Mesh mesh) {
		mesh.bind();
		glDrawElements(GL_TRIANGLES, mesh.vertexCount(), GL_UNSIGNED_INT, 0);
		trisDrawn += mesh.vertexCount() / 3;
	}

	@Override
	public void drawWireFrame(Mesh mesh) {
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		drawMesh(mesh);
		glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
	}

	@Override
	public void setActiveMaterial(Material mat) {
		mat.bind();
	}

	@Override
	public RenderingStats getStatistics() {
		return new RenderingStats(trisDrawn);
	}

	@Override
	public void resetStats() {
		trisDrawn = 0;
	}

}
