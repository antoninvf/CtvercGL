import org.lwjgl.opengl.GL33;

public class Shader {

    public static final String vertexShaderSource = "#version 330 core\n" +
            "layout (location = 0) in vec3 aPos;\n" +
            "void main()\n" +
            "{\n" +
            " gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n" +
            "}";

    public static final String fragmentShaderSource = "#version 330 core\n" +
            "out vec4 FragColor;\n" +
            "void main()\n" +
            "{\n" +
            "   FragColor = vec4(1.0f, 1.0f, 0.0f, 1.0f);\n" + // color of the triangle
            "}\n";

    public static int vertexShaderId;
    public static int fragmentShaderId;
    public static int shaderProgramId;

    public static void initShaders() {

        //generate shader ids
        vertexShaderId = GL33.glCreateShader(GL33.GL_VERTEX_SHADER);
        fragmentShaderId = GL33.glCreateShader(GL33.GL_FRAGMENT_SHADER);

        //compile vertex shader
        GL33.glShaderSource(vertexShaderId, vertexShaderSource);
        GL33.glCompileShader(vertexShaderId);

        //print the log for vertex shader
        System.out.println(GL33.glGetShaderInfoLog(vertexShaderId));

        //compile fragment shader
        GL33.glShaderSource(fragmentShaderId, fragmentShaderSource);
        GL33.glCompileShader(fragmentShaderId);

        //print the log for fragment shader
        System.out.println(GL33.glGetShaderInfoLog(fragmentShaderId));

        //shader attachment
        shaderProgramId = GL33.glCreateProgram();
        GL33.glAttachShader(shaderProgramId, vertexShaderId);
        GL33.glAttachShader(shaderProgramId, fragmentShaderId);
        GL33.glLinkProgram(shaderProgramId);

        //print the log for program
        System.out.println(GL33.glGetProgramInfoLog(shaderProgramId));

        //delete cuz its on gpu now
        GL33.glDeleteShader(vertexShaderId);
        GL33.glDeleteShader(fragmentShaderId);
    }
}
