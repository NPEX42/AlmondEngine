#version 330 core
out vec4 FragColor;

in vec2 oTexCoord;
in vec4 oColor;
uniform sampler2D uAlbedo;

void main()
{
    FragColor = texture(uAlbedo, oTexCoord);
    //FragColor = vec4(oColor.xyz, 1);
} 