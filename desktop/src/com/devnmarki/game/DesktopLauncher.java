package com.devnmarki.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.devnmarki.game.engine.Engine;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(768, 768);
		config.setResizable(false);
		config.setForegroundFPS(60);
		config.setTitle("The Endless Hollow");
		new Lwjgl3Application(new Game(), config);
	}
}
