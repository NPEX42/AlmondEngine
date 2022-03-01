#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec2 aTexCoord;

out vec2 oTexCoord;
out vec4 oColor;

uniform mat4 uProjection; 
uniform mat4 uView;
uniform mat4 uModel;
void main()
{
	mat4 mvp = uProjection;
    gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);
    oTexCoord = aTexCoord;
    oColor = vec4(1, 1, 1, 1) * mvp;
    
}