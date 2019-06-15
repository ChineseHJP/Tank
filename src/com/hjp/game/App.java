package com.hjp.game;

public class App {
	public static void main(String[] args) {
		GameWindow gw=new GameWindow(Config.TITLE,Config.WIDTH,Config.HEIGHT,Config.FPS);
		gw.start();
	}
}


