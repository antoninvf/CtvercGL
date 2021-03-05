import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;


public class Main {

    public static float random1;
    public static float random2; // tady jsem něco zkoušel ale ššš
    public static float random3;

    public static void main(String[] args) throws Exception {
        GLFW.glfwInit();

        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3); // OpenGL 3.3

        long window = GLFW.glfwCreateWindow(800, 600, "what the fuck CTVERC, ale je to obdelnik pane uciteli wtf", 0, 0);
        if (window == 0) {
            GLFW.glfwTerminate();
            throw new Exception("retard lmfao nemůže otevřít okno lsxd debil");
        }
        GLFW.glfwMakeContextCurrent(window);

        GL.createCapabilities(); // init OpenGL
        GL33.glViewport(0, 0, 800, 600);

        GLFW.glfwSetFramebufferSizeCallback(window, (win, w, h) -> {
            GL33.glViewport(0, 0, w, h);
        });

        //init the Game.java file
        Game.init(window);

        while (!GLFW.glfwWindowShouldClose(window)) {
            // change background color
            GL33.glClearColor(0.1f, 0.1f, 0.1f, 1f);
            GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);

            // if ESC is pressed, exit the program
            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_ESCAPE) == GLFW.GLFW_PRESS) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }

            //rainbow lmao
            if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_Q) == GLFW.GLFW_PRESS) {
                int min = 0;
                int max = 1;

                random1 = (float) ((Math.random() * (max - min)) + min);
                random2 = (float) ((Math.random() * (max - min)) + min);
                random3 = (float) ((Math.random() * (max - min)) + min);

                GL33.glClearColor(random1, random2, random3, 1f);
                GL33.glClear(GL33.GL_COLOR_BUFFER_BIT);
                Thread.sleep(100);


            }

            Game.render(window);
            Game.update(window);

            // Swap the color buffer (screen tearing solution)
            GLFW.glfwSwapBuffers(window);
            // Listen to input
            GLFW.glfwPollEvents();
        }

        GLFW.glfwTerminate();
    }

}
