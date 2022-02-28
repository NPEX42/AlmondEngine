#version 330 core
out vec4 FragColor;

in vec4 oTint;
in vec2 oTexCoord;
uniform sampler2D uAlbedo;

void main()
{
    FragColor = texture(uAlbedo, oTexCoord) * oTint;
} 