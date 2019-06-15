package com.hjp.pojo;

import java.io.IOException;

import org.hjp.game.utils.CollsionUtils;
import org.hjp.game.utils.DrawUtils;
import org.hjp.game.utils.SoundUtils;

public class Bullet {
	int x;
	int y;
	int width;
	int height;
	int speed=8;
	String fangxiang;
	int leafwidth;
	int leafheight;
	
	public Bullet(Tank tank){
		int tankx=tank.x;
		int tanky=tank.y;
		int tankwidth=64;
		int tankheight=64;
		fangxiang=tank.fangxiang;
		try {
			int[] size=DrawUtils.getSize("res/img/lightning.gif");
			width=size[0];
			height=size[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (fangxiang) {
		case "up":
			x=tankx+tankwidth/2-width/2;
			y=tanky-height/2;
			break;
		case "down":
			x=tankx+tankwidth/2-width/2;
			y=tanky+height/2;
			break;
		case "left":
			x=tankx-width/2;
			y=tanky+tankheight/2-height/2;
			break;
		case "right":
			x=tankx+width/2;
			y=tanky+tankheight/2-height/2;
			break;
		}
		//添加子弹的声音
		try {
			SoundUtils.play("res/snd/fire.wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Bullet(Pokemon pokemon) {
		// TODO Auto-generated constructor stub
		int pokemonx=pokemon.x;
		int pokemony=pokemon.y;
		int pokemonwidth=64;
		int pokemonheight=64;
		fangxiang=pokemon.fangxiang;
		try {
			int[] size2=DrawUtils.getSize("res/img/leaf_u.gif");
			leafwidth=size2[0];
			leafheight=size2[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (fangxiang) {
		case "up":
			x=pokemonx+pokemonwidth/2-leafwidth/2;
			y=pokemony-leafheight/2;
			break;
		case "down":
			x=pokemonx+pokemonwidth/2-leafwidth/2;
			y=pokemony+leafheight/2;
			break;
		case "left":
			x=pokemonx-leafwidth/2;
			y=pokemony+pokemonheight/2-leafheight/2;
			break;
		case "right":
			x=pokemonx+leafwidth/2;
			y=pokemony+pokemonheight/2-leafheight/2;
			break;
		}
		//添加子弹的声音
		try {
			SoundUtils.play("res/snd/fire.wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(){
		String img="";
		switch (fangxiang) {
		case "up":
			img="res/img/lightning_u.gif";
			y-=speed;
			break;
		case "down":
			img="res/img/lightning_d.gif";
			y+=speed;
			break;
		case "right":
			img="res/img/lightning_r.gif";
			x+=speed;
			break;
		case "left":
			img="res/img/lightning_l.gif";
			x-=speed;
			break;
		}
		try {
			DrawUtils.draw(img,x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drawLeaf(){
		String img="";
		switch (fangxiang) {
		case "up":
			img="res/img/leaf_u.gif";
			y-=speed;
			break;
		case "down":
			img="res/img/leaf_d.gif";
			y+=speed;
			break;
		case "right":
			img="res/img/leaf_r.gif";
			x+=speed;
			break;
		case "left":
			img="res/img/leaf_l.gif";
			x-=speed;
			break;
		}
		try {
			DrawUtils.draw(img,x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//判断子弹和砖墙之间是否进行了碰撞 ，如果进行了碰撞，那么我就开始爆炸
		//返回true  代码碰撞了  false  代表了没有碰撞
		public boolean chechHit(Steel steel){
			//获得撞墙的体积
			int x1 = steel.x;
			int y1 = steel.y;
			int w1 = steel.width;
			int h1 = steel.height;		
			
			boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x, y, width, height);
			
			return flag;
		}

		//判断子弹和砖墙之间是否进行了碰撞 ，如果进行了碰撞，那么我就开始爆炸
				//返回true  代码碰撞了  false  代表了没有碰撞
				public boolean chechHit(Wall wall){
					//获得撞墙的体积
					int x1 = wall.x;
					int y1 = wall.y;
					int w1 = wall.width;
					int h1 = wall.height;		
					
					boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x, y, width, height);
					
					return flag;
				}
		public boolean leafchechHit(Steel steel){
			//获得撞墙的体积
			int x1 = steel.x;
			int y1 = steel.y;
			int w1 = steel.width;
			int h1 = steel.height;		
			
			boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x, y, leafwidth, leafheight);
			
			return flag;
		}

		//判断子弹和砖墙之间是否进行了碰撞 ，如果进行了碰撞，那么我就开始爆炸
				//返回true  代码碰撞了  false  代表了没有碰撞
				public boolean leafchechHit(Wall wall){
					//获得撞墙的体积
					int x1 = wall.x;
					int y1 = wall.y;
					int w1 = wall.width;
					int h1 = wall.height;		
					
					boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x, y, leafwidth, leafheight);
					
					return flag;
				}
				public boolean chechHit(Pokemon pokemon) {
					// TODO Auto-generated method stub
					int x1 = pokemon.x;
					int y1 = pokemon.y;
					int w1 = pokemon.width;
					int h1 = pokemon.height;		
					
					boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x, y, width, height);
					
					return flag;
					
				}
				public boolean chechHit(Tank tank) {
					// TODO Auto-generated method stub
					int x1 = tank.x;
					int y1 = tank.y;
					int w1 = tank.width;
					int h1 = tank.height;		
					
					boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x, y, leafwidth, leafheight);
					
					return flag;
				}
}
