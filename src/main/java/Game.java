import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Game {

    private static final float vertices[] = { // Position of the vertices (change to change the triangle duh)
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f,
            0.5f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            -0.5f, 0.5f, 0.0f,
            0.5f, 0.5f, 0.0f
    };

    private static int triangleVaoId;
    private static int triangleVboId;


    public static void init(long window) {

        //init shaders
        Shader.initShaders();

        //generate all ids
        triangleVaoId = GL33.glGenVertexArrays();
        triangleVboId = GL33.glGenBuffers();

        //tell opengl we are currently using this object (vaoId)
        GL33.glBindVertexArray(triangleVaoId);
        //tell opengl we are currently writing to this buffer (vboId)
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, triangleVboId);

        FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length)
                .put(vertices)
                .flip(); // put here so it doesnt crash apparently

        // send buffer positions to the gpu
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, fb, GL33.GL_STATIC_DRAW);
        GL33.glVertexAttribPointer(0, 3, GL33.GL_FLOAT, false, 0, 0);
        GL33.glVertexAttribPointer(8, 3, GL33.GL_FLOAT, false, 0, 0);
        GL33.glEnableVertexAttribArray(0);

        // clear the buffer from memory (so a memory leak doesnt happen o_O)
        MemoryUtil.memFree(fb);
    }

    public static void render(long window) {
        GL33.glUseProgram(Shader.shaderProgramId);
        GL33.glBindVertexArray(triangleVaoId);
        GL33.glDrawArrays(GL33.GL_TRIANGLES, 0, vertices.length / 3);
    }

    public static void update(long window) {
    }

}
