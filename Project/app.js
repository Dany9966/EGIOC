/* 
	Main app file. Handles canvas element from the html file, and puts the WebGL canvas to it.
	
	This app is a demo of how shadows are mapped according to a moving lightsource and camera angle. You can move
	through the scene using WASD keys and changing camera angle with arrow keys.
	The scene is exported from a Blender file (.blend extension) which I converted to JSON,
	so I can parse it into the app using Javascript's parseJSON method. 
	Other libraries used here: 
		async.js ( https://github.com/caolan/async )- to make JavaScript opertaions asynchronous
		gl-matrix.js ( http://glmatrix.net ) - to make vector and matrix operations more easily and faster


*/

'use strict';

var Demo;

function Init() {
	var canvas = document.getElementById('gl-surface');
	var gl = canvas.getContext('webgl');
	if (!gl) {
		console.log('Failed to get WebGL context - trying experimental context');
		gl = canvas.getContext('experimental-webgl');
	}
	if (!gl) {
		alert('Your browser does not support WebGL - please use a different browser\nGoogleChrome works great!');
		return;
	}

	Demo = new LightMapDemoScene(gl);
	Demo.Load(function (demoLoadError) {
		if (demoLoadError) {
			alert('Could not load the demo - see console for more details');
			console.error(demoLoadError);
		} else {
			Demo.Begin();
		}
	});
}