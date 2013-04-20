//
// International Space Apps Challenge
//
// Catch A Meteor
// 		url: https://code.google.com/p/catch-a-meteor-tracker/
// 		made by:
//
// 			+ Aubry Nicolas (nox.aubry@gmail.com)
// 			+ Prevost Guillaume (guillaume.prevost.gp@gmail.com)
//
// file: OpenGLSectionFragment.java
// created: Apr 20, 2013
//


package com.nox.catch_a_meteor;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OpenGLSectionFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	/** Hold a reference to our GLSurfaceView */
	private GLSurfaceView mGLSurfaceView;
	/**
	 * Store the model matrix. This matrix is used to move models from object space (where each model can be thought
	 * of being located at the center of the universe) to world space.
	 */
	private float[] mModelMatrix = new float[16];

	/**
	 * Store the view matrix. This can be thought of as our camera. This matrix transforms world space to eye space;
	 * it positions things relative to our eye.
	 */
	private float[] mViewMatrix = new float[16];

	/** Store the projection matrix. This is used to project the scene onto a 2D viewport. */
	private float[] mProjectionMatrix = new float[16];

	/** Allocate storage for the final combined matrix. This will be passed into the shader program. */
	private float[] mMVPMatrix = new float[16];
	
	/** This will be used to pass in the transformation matrix. */
	private int mMVPMatrixHandle;

	/** This will be used to pass in model position information. */
	private int mPositionHandle;

	/** This will be used to pass in model color information. */
	private int mColorHandle;
	
	public OpenGLSectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mGLSurfaceView = new GLSurfaceView(inflater.getContext());

		// Check if the system supports OpenGL ES 2.0.
		final ActivityManager activityManager = (ActivityManager) inflater.getContext().getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

		if (supportsEs2) 
		{
			// Request an OpenGL ES 2.0 compatible context.
			mGLSurfaceView.setEGLContextClientVersion(2);
			mGLSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
				
				@Override
				public void onSurfaceCreated(GL10 gl, EGLConfig config) {
					// TODO Auto-generated method stub
					// Set the background clear color to gray.
					GLES20.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

					// Position the eye behind the origin.
					final float eyeX = 0.0f;
					final float eyeY = 0.0f;
					final float eyeZ = 1.5f;

					// We are looking toward the distance
					final float lookX = 0.0f;
					final float lookY = 0.0f;
					final float lookZ = -5.0f;

					// Set our up vector. This is where our head would be pointing were we holding the camera.
					final float upX = 0.0f;
					final float upY = 1.0f;
					final float upZ = 0.0f;

					// Set the view matrix. This matrix can be said to represent the camera position.
					// NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
					// view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
					Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);

					final String vertexShader =
						"uniform mat4 u_MVPMatrix;      \n"		// A constant representing the combined model/view/projection matrix.

					  + "attribute vec4 a_Position;     \n"		// Per-vertex position information we will pass in.
					  + "attribute vec4 a_Color;        \n"		// Per-vertex color information we will pass in.			  

					  + "varying vec4 v_Color;          \n"		// This will be passed into the fragment shader.

					  + "void main()                    \n"		// The entry point for our vertex shader.
					  + "{                              \n"
					  + "   v_Color = a_Color;          \n"		// Pass the color through to the fragment shader. 
					  											// It will be interpolated across the triangle.
					  + "   gl_Position = u_MVPMatrix   \n" 	// gl_Position is a special variable used to store the final position.
					  + "               * a_Position;   \n"     // Multiply the vertex by the matrix to get the final point in 			                                            			 
					  + "}                              \n";    // normalized screen coordinates.

					final String fragmentShader =
						"precision mediump float;       \n"		// Set the default precision to medium. We don't need as high of a 
																// precision in the fragment shader.				
					  + "varying vec4 v_Color;          \n"		// This is the color from the vertex shader interpolated across the 
					  											// triangle per fragment.			  
					  + "void main()                    \n"		// The entry point for our fragment shader.
					  + "{                              \n"
					  + "   gl_FragColor = v_Color;     \n"		// Pass the color directly through the pipeline.		  
					  + "}                              \n";												

					// Load in the vertex shader.
					int vertexShaderHandle = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);

					if (vertexShaderHandle != 0) 
					{
						// Pass in the shader source.
						GLES20.glShaderSource(vertexShaderHandle, vertexShader);

						// Compile the shader.
						GLES20.glCompileShader(vertexShaderHandle);

						// Get the compilation status.
						final int[] compileStatus = new int[1];
						GLES20.glGetShaderiv(vertexShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

						// If the compilation failed, delete the shader.
						if (compileStatus[0] == 0) 
						{				
							GLES20.glDeleteShader(vertexShaderHandle);
							vertexShaderHandle = 0;
						}
					}

					if (vertexShaderHandle == 0)
					{
						throw new RuntimeException("Error creating vertex shader.");
					}

					// Load in the fragment shader shader.
					int fragmentShaderHandle = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

					if (fragmentShaderHandle != 0) 
					{
						// Pass in the shader source.
						GLES20.glShaderSource(fragmentShaderHandle, fragmentShader);

						// Compile the shader.
						GLES20.glCompileShader(fragmentShaderHandle);

						// Get the compilation status.
						final int[] compileStatus = new int[1];
						GLES20.glGetShaderiv(fragmentShaderHandle, GLES20.GL_COMPILE_STATUS, compileStatus, 0);

						// If the compilation failed, delete the shader.
						if (compileStatus[0] == 0) 
						{				
							GLES20.glDeleteShader(fragmentShaderHandle);
							fragmentShaderHandle = 0;
						}
					}

					if (fragmentShaderHandle == 0)
					{
						throw new RuntimeException("Error creating fragment shader.");
					}

					// Create a program object and store the handle to it.
					int programHandle = GLES20.glCreateProgram();

					if (programHandle != 0) 
					{
						// Bind the vertex shader to the program.
						GLES20.glAttachShader(programHandle, vertexShaderHandle);			

						// Bind the fragment shader to the program.
						GLES20.glAttachShader(programHandle, fragmentShaderHandle);

						// Bind attributes
						GLES20.glBindAttribLocation(programHandle, 0, "a_Position");
						GLES20.glBindAttribLocation(programHandle, 1, "a_Color");

						// Link the two shaders together into a program.
						GLES20.glLinkProgram(programHandle);

						// Get the link status.
						final int[] linkStatus = new int[1];
						GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, linkStatus, 0);

						// If the link failed, delete the program.
						if (linkStatus[0] == 0) 
						{				
							GLES20.glDeleteProgram(programHandle);
							programHandle = 0;
						}
					}

					if (programHandle == 0)
					{
						throw new RuntimeException("Error creating program.");
					}
			        
			        // Set program handles. These will later be used to pass in values to the program.
			        mMVPMatrixHandle = GLES20.glGetUniformLocation(programHandle, "u_MVPMatrix");        
			        mPositionHandle = GLES20.glGetAttribLocation(programHandle, "a_Position");
			        mColorHandle = GLES20.glGetAttribLocation(programHandle, "a_Color");        
			        
			        // Tell OpenGL to use this program when rendering.
			        GLES20.glUseProgram(programHandle);    						
				}
				
				@Override
				public void onSurfaceChanged(GL10 gl, int width, int height) {
					// TODO Auto-generated method stub
					// Set the OpenGL viewport to the same size as the surface.
					GLES20.glViewport(0, 0, width, height);

					// Create a new perspective projection matrix. The height will stay the same
					// while the width will vary as per aspect ratio.
					final float ratio = (float) width / height;
					final float left = -ratio;
					final float right = ratio;
					final float bottom = -1.0f;
					final float top = 1.0f;
					final float near = 1.0f;
					final float far = 10.0f;

					Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);	
				}
				
				@Override
				public void onDrawFrame(GL10 gl) {
					// TODO Auto-generated method stub
					GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);			        
	                
			        // Do a complete rotation every 10 seconds.
			        long time = SystemClock.uptimeMillis() % 10000L;
				}
			});
			// Set the renderer to our demo renderer, defined below.
//			mGLSurfaceView.setRenderer(new LessonOneRenderer());
		} 
		else 
		{
			// This is where you could create an OpenGL ES 1.x compatible
			// renderer if you wanted to support both ES 1 and ES 2.
		}


		
//		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
//				container, false);
//		TextView dummyTextView = (TextView) rootView
//				.findViewById(R.id.section_label);
//		dummyTextView.setText(Integer.toString(getArguments().getInt(
//				ARG_SECTION_NUMBER)));
		return mGLSurfaceView;
	}
}