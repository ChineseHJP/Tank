package com.hjp.pojo;

import org.hjp.game.utils.DrawUtils;

public class Wall {
	protected int x;
	protected int y;
	protected int width=64;
	protected int height=64;
	protected int blood=2;
	public Wall(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void draw(){
		try {
			DrawUtils.draw("res/img/wall.gif",x,y);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Blast showBlast(){
		blood--;
		return new Blast(this,true);
	}
	public boolean getBlood(){
		return blood==0;
	}
	public Blast stopBlast(){
		blood--;
		return new Blast(this,false);
	}
}
