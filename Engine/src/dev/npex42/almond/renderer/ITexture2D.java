package dev.npex42.almond.renderer;

public interface ITexture2D {
	public int id();
	public int unit();
	public int width();
	public int height();
	public int channels();
	public void setUnit(int unit);
}
