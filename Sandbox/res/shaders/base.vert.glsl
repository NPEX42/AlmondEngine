#version 330 core
layout (location = 0) in vec3 aPos;
layout (location = 1) in vec2 aTexCoord;

out vec2 oTexCoord;
out vec4 oColor;

uniform mat4 uMVP;
void main()
{
    gl_Position = uMVP * vec4(aPos.x, aPos.y, aPos.z, 1.0);
    oTexCoord = aTexCoord;
    
}