package bouncysquare.cg.bouncysquare;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL11;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by P17-15 on 4/19/2018.
 */

public class SquareRenderer implements GLSurfaceView.Renderer {
    private final Context context;
    private Square mSquare1;
    private Square mSquare2;
    private float mTransY;
    private float mTransX;





    public SquareRenderer(Context context) {
        this.context = context;
        float squareColorsYMCA[] =
                {
                        1.0f, 1.0f, 0.0f, 1.0f,
                        0.0f, 1.0f, 1.0f, 1.0f,
                        0.0f, 0.0f, 0.0f, 1.0f,
                        1.0f, 0.0f, 1.0f, 1.0f
                };

        float squareColorsRGBA[] =
                {
                        1.0f, 0.0f, 0.0f, 1.0f,
                        0.0f, 1.0f, 0.0f, 1.0f,
                        0.0f, 0.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f
                };

        mSquare1 = new Square(squareColorsYMCA);
        mSquare2 = new Square(squareColorsRGBA);
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL11.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f,(float)Math.sin(mTransY), -3.0f);
        mTransY += 0.075f;
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        mSquare1.draw(gl);

        gl.glLoadIdentity();
        gl.glTranslatef((float)(Math.sin(mTransX)/2.0f),0.0f, -2.9f);
        gl.glColor4f(1.0f, 1.0f, 1.0f, 0.75f);
        //mSquare2.draw(gl);
        mTransX += .075f;
        gl.glEnable(GL10.GL_BLEND);
        //gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE);
        mSquare2.draw(gl);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        float ratio = (float) width / height;
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);

    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        int resid = R.drawable.goldengate;
        mSquare1.createTexture(gl, this.context, resid);

        gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glClearColor(0,0,0,1);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);

    }
}
