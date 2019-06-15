package com.hjp.pojo;

import org.hjp.game.utils.DrawUtils;

public class Water {
	protected int x;
	protected int y;
	protected int width=64;
	protected int height=64;
	public Water(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void draw(){
		try {
			DrawUtils.draw("res/img/water.gif", x, y);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
