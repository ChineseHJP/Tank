package com.hjp.pojo;

import org.hjp.game.utils.DrawUtils;

import java.io.IOException;
import java.util.Random;

import org.hjp.game.utils.CollsionUtils;
import com.hjp.game.Config;

public class Pokemon {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int blood=5;
	protected int power;
	protected int speed=4;
	protected String fangxiang="down";
	long lastTime ;// 最后一次发射子弹的时间
	long lastMoveTime=0;// 最后一次发射子弹的时间
	String badFangXiangSteel;
	String badFangXiangWater;
	String badFangXiangWall;
	String badFangXiangPokemon;
	public Pokemon(int x,int y){
		super();
		this.x=x;
		this.y=y;
		try {
			int[] size = DrawUtils.getSize("res/img/jucaoye_u.gif");
			width = size[0];
			height= size[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(){
		String img="";
		switch (fangxiang) {
		case "up":
			img="res/img/jucaoye_u.gif";
			break;
		case "down":
			img="res/img/jucaoye_l.gif";
			break;
		case "right":
			img="res/img/jucaoye_r.gif";
			break;
		case "left":
			img="res/img/jucaoye_l.gif";
			break;
		default:
			break;
		}
		try {
			DrawUtils.draw(img, x, y);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public String getfangxiang() {
		long nowMoveTime = System.currentTimeMillis();//获得当前的时间的毫秒数
		if(nowMoveTime-lastMoveTime<=1000) {
			
		}else {
		int nextInt=new Random().nextInt(4);
		switch (nextInt) {
		case 0:
			fangxiang="up";
			break;
		case 1:
			fangxiang="down";
			break;
		case 2:
			fangxiang="left";
			break;
		case 3:
			fangxiang="right";
			break;
		default:
			break;
		}
		lastMoveTime=nowMoveTime;
	}
		return fangxiang;
	}
	public void move(){
		if(fangxiang!=null) {
		String str=this.getfangxiang();
		if(badFangXiangSteel!=null && badFangXiangSteel.equals(str)){
			System.out.println("他碰撞铁了！！！");
			return;
		}else if(badFangXiangWall!=null	&& badFangXiangWall.equals(str)){
			System.out.println("他碰撞墙了！！！");
			return;
		}else if(badFangXiangWater!=null&&badFangXiangWater.equals(str)) {
			System.out.println("他碰撞水了！！！");
			return;
		}else if(badFangXiangPokemon!=null&&badFangXiangPokemon.equals(str)) {
			System.out.println("ta碰撞了！！！");
			return;
		}
		if(fangxiang!=str){
			this.fangxiang=str;
		}else{
			if(x>=0&&x<Config.WIDTH-64&&str.equals("right")){
				this.x+=speed;
			}
			if(x>0&&x<=Config.WIDTH-64&&str.equals("left")){
				this.x-=speed;
			}
			if(y>0&&y<=Config.HEIGHT-64&&str.equals("up")){
				this.y-=speed;
			}
			if(y>=0&&y<Config.HEIGHT-64&&str.equals("down")){
				this.y+=speed;
			}
		}
		}
	}
	public Bullet shot() {
		// TODO Auto-generated method stub
			//判断   没400ms 发射一颗子弹
			long nowTime = System.currentTimeMillis();//获得当前的时间的毫秒数
			
			if(nowTime - lastTime < 800){
				return null;
			}
			lastTime = nowTime;
			return new Bullet(this);

	}
	public boolean checkHit(Steel steel) {
		int x1 =steel.x;//贴砖的X 
		int y1 = steel.y;//铁砖Y
		int w1 = steel.width;
		int h1 = steel.height;
		
		int x2 = x; //预测坦克的一下步行走路线 
		int y2 = y; //预测坦克的一下步行走路线
		
		//预测坦克的行走路线  
		switch (fangxiang) {
		case "up":
			y2 -= speed;  //下一步坦克的位置
			badFangXiangSteel = "up";
			break;
		case "down":
			y2 += speed; // 下一步坦克的位置
			badFangXiangSteel = "down";
			break;
		case "left":
			x2 -= speed;// 下一步坦克的位置
			badFangXiangSteel = "left";
			break;
		case "right":
			x2 += speed;// 下一步坦克的位置
			badFangXiangSteel = "right";
			break;

		}
		boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, width, height);
		//true  代表碰撞   false 代表未碰撞
		if(flag){
			//让你的坦克不能继续前进  fangXiang  = up
		}else{
			badFangXiangSteel = null;
		}
		return flag;
	}
	public boolean checkHit(Water water) {
		int x3 =water.x;//贴砖的X 
		int y3 = water.y;//铁砖Y
		int w3 = water.width;
		int h3 = water.height;
		
		
		int x2 = x; //预测坦克的一下步行走路线 
		int y2 = y; //预测坦克的一下步行走路线
		
		//预测坦克的行走路线  
		switch (fangxiang) {
		case "up":
			y2 -= speed;  //下一步坦克的位置
			badFangXiangWater = "up";
			break;
		case "down":
			y2 += speed; // 下一步坦克的位置
			badFangXiangWater = "down";
			break;
		case "left":
			x2 -= speed;// 下一步坦克的位置
			badFangXiangWater = "left";
			break;
		case "right":
			x2 += speed;// 下一步坦克的位置
			badFangXiangWater = "right";
			break;

		}
		boolean flag1 = CollsionUtils.isCollsionWithRect(x3, y3, w3, h3, x2, y2, width, height);
		//true  代表碰撞   false 代表未碰撞
		if(flag1){
			//让你的坦克不能继续前进  fangXiang  = up
		}else{
			badFangXiangWater = null;
		}
		return flag1;
		
	}
	
	public boolean checkHit(Wall wall) {
		int x4 =wall.x;//贴砖的X 
		int y4 = wall.y;//铁砖Y
		int w4 = wall.width;
		int h4 = wall.height;
		
		
		int x2 = x; //预测坦克的一下步行走路线 
		int y2 = y; //预测坦克的一下步行走路线
		
		//预测坦克的行走路线  
		switch (fangxiang) {
		case "up":
			y2 -= speed;  //下一步坦克的位置
			badFangXiangWall = "up";
			break;
		case "down":
			y2 += speed; // 下一步坦克的位置
			badFangXiangWall = "down";
			break;
		case "left":
			x2 -= speed;// 下一步坦克的位置
			badFangXiangWall = "left";
			break;
		case "right":
			x2 += speed;// 下一步坦克的位置
			badFangXiangWall = "right";
			break;

		}
		boolean flag2 = CollsionUtils.isCollsionWithRect(x4, y4, w4, h4, x2, y2, width, height);
		//true  代表碰撞   false 代表未碰撞
		if(flag2){
			//让你的坦克不能继续前进  fangXiang  = up
		}else{
			badFangXiangWall = null;
		}
		return flag2;
		
	}
	public boolean checkHit(Tank tank) {
		// TODO Auto-generated method stub
		int x1 =tank.x;//贴砖的X 
		int y1 = tank.y;//铁砖Y
		int w1 = tank.width;
		int h1 = tank.height;
		
		
		int x2 = x; //预测坦克的一下步行走路线 
		int y2 = y; //预测坦克的一下步行走路线
		
		//预测坦克的行走路线  
		switch (fangxiang) {
		case "up":
			y2 -= speed;  //下一步坦克的位置
			badFangXiangPokemon = "up";
			break;
		case "down":
			y2 += speed; // 下一步坦克的位置
			badFangXiangPokemon = "down";
			break;
		case "left":
			x2 -= speed;// 下一步坦克的位置
			badFangXiangPokemon = "left";
			break;
		case "right":
			x2 += speed;// 下一步坦克的位置
			badFangXiangPokemon = "right";
			break;

		}
		boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, width, height);
		//true  代表碰撞   false 代表未碰撞
		if(flag){
			//让你的坦克不能继续前进  fangXiang  = up
		}else{
			badFangXiangPokemon = null;
		}
		return flag;
	}
	public Blast showBlast(){
		blood--;
		return new Blast(this,true);
	}
	public boolean getBlood(){
		return blood<=0;
	}
	public Blast stopBlast(){
		blood--;
		return new Blast(this,false);
	}
	public void destory(){
		try {
			DrawUtils.draw("res/img/end.gif", 0, 0);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
