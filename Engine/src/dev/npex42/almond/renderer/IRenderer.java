package dev.npex42.almond.renderer;

import java.awt.Color;

public interface IRenderer {
	public void clear(Color clr);
	public void drawMesh(Mesh mesh);
	public void drawWireFrame(Mesh mesh);
	public void setActiveMaterial(Material mat);
	public RenderingStats getStatistics();
	public void resetStats();
}
