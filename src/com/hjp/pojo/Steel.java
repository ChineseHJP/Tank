package com.hjp.pojo;

import java.io.IOException;

import org.hjp.game.utils.DrawUtils;
import org.hjp.game.utils.SoundUtils;

public class Steel {
	protected int x;
	protected int y;
	protected int width=64;
	protected int height=64;
	protected int blood=5;
	
	
	public Steel(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void draw(){
		try {
			DrawUtils.draw("res/img/steel.gif",x,y);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	//2. 挨打爆炸
		public Blast showBlast(){
			blood--;
			return new Blast(this,true);
		}
		public boolean getBlood(){
			return blood==0;
		}
		public Blast stopBlast(){
			blood--;
			try {
				SoundUtils.play("res/snd/blast.wav");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Blast(this,false);
		}
}
