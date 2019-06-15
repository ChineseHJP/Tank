package com.hjp.pojo;

import java.io.IOException;

import org.hjp.game.utils.DrawUtils;
import org.hjp.game.utils.SoundUtils;

public class Blast {
	
	//定义子弹的属性
		int x;
		int y;
		int width;//子弹宽度
		int height;//子弹的高度
		
		boolean isDesctory; //记录当前的爆炸物是否可以消失
		
		int num = 0;
		//
		String[] imgs = {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif","res/img/blast_6.gif","res/img/blast_7.gif","res/img/blast_8.gif"};
		//爆炸物的位置  要根据贴砖来进行坐标的定位
		public Blast(Steel steel,boolean flag) {
			//获得爆炸物的宽高
			try {
				int[] size = DrawUtils.getSize("res/img/blast_1.gif");
				width = size[0];
				height= size[1];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			////1.爆炸物位置不对
			this.x = steel.x +steel.width/2-width/2 ;
			this.y = steel.y + steel.height/2 - height/2 ;
			
			if(flag) {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif"};
			}else {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif","res/img/blast_6.gif","res/img/blast_7.gif","res/img/blast_8.gif"};
			}
			try {
				SoundUtils.play("res/snd/blast.wav");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Wall
		public Blast(Wall wall,boolean flag) {
			//获得爆炸物的宽高
			try {
				int[] size = DrawUtils.getSize("res/img/blast_1.gif");
				width = size[0];
				height= size[1];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			////1.爆炸物位置不对
			this.x = wall.x +wall.width/2-width/2 ;
			this.y = wall.y + wall.height/2 - height/2 ;
			
			if(flag) {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif"};
			}else {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif","res/img/blast_6.gif","res/img/blast_7.gif","res/img/blast_8.gif"};
			}
			try {
				SoundUtils.play("res/snd/blast.wav");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public Blast(Pokemon pokemon, boolean flag) {
			try {
				int[] size = DrawUtils.getSize("res/img/blast_1.gif");
				width = size[0];
				height= size[1];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			////1.爆炸物位置不对
			this.x = pokemon.x +pokemon.width/2-width/2 ;
			this.y = pokemon.y + pokemon.height/2 - height/2 ;
			
			if(flag) {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif"};
			}else {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif","res/img/blast_6.gif","res/img/blast_7.gif","res/img/blast_8.gif"};
			}
			try {
				SoundUtils.play("res/snd/blast.wav");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public Blast(Tank tank, boolean flag) {
			// TODO Auto-generated constructor stub
			try {
				int[] size = DrawUtils.getSize("res/img/blast_1.gif");
				width = size[0];
				height= size[1];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			////1.爆炸物位置不对
			this.x = tank.x +tank.width/2-width/2 ;
			this.y = tank.y + tank.height/2 - height/2 ;
			
			if(flag) {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif"};
			}else {
				imgs =new String[] {"res/img/blast_1.gif","res/img/blast_2.gif","res/img/blast_3.gif","res/img/blast_4.gif","res/img/blast_5.gif","res/img/blast_6.gif","res/img/blast_7.gif","res/img/blast_8.gif"};
			}
			try {
				SoundUtils.play("res/snd/blast.wav");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void draw(){
			String img = imgs[num++];
			if(num>=imgs.length){
				 num = 0;
				 isDesctory = true;
				 return;
			}
			try {
				DrawUtils.draw(img, x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean isDesctory(){
			return isDesctory;
		}




	
}
