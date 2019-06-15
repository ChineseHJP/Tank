package com.hjp.pojo;

import org.hjp.game.utils.DrawUtils;

public class Grass {
	private int x;
	private int y;
	public Grass(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void draw(){
		try {
			DrawUtils.draw("res/img/grass.gif",x,y);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
