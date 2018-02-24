package com.example.android.cgl1;


import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dani on 2/20/18.
 */

public class MainActivityRenderer implements GLSurfaceView.Renderer{

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        gl10.glClearColor(1,0,0,1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        gl10.glViewport(0,0,i,i1);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    }
}
