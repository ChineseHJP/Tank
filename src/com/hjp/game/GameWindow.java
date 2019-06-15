package com.hjp.game;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

import org.hjp.game.Window;
import org.hjp.game.utils.SoundUtils;
import org.lwjgl.input.Keyboard;

import com.hjp.pojo.Blast;
import com.hjp.pojo.Bullet;
import com.hjp.pojo.Grass;
import com.hjp.pojo.Pokemon;
import com.hjp.pojo.Steel;
import com.hjp.pojo.Tank;
import com.hjp.pojo.Wall;
import com.hjp.pojo.Water;

public class GameWindow extends Window{
	CopyOnWriteArrayList<Wall> walls=new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<Water> water=new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<Grass> grass=new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<Steel> steels=new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<Bullet> bullets=new CopyOnWriteArrayList<>();
	CopyOnWriteArrayList<Bullet> leafbullets=new CopyOnWriteArrayList<>();
	/* CopyOnWriteArrayList<Pokemon> pokemons=new CopyOnWriteArrayList<>(); */
	Pokemon pokemon;
	Tank tank;
	//爆炸物
	CopyOnWriteArrayList<Blast> blasts = new CopyOnWriteArrayList<>();

	public GameWindow(String title, int width, int height, int fps) {
		super(title, width, height, fps);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 窗体创建时的回调
	 */
	protected void onCreate(){
		System.out.println("游戏开始");
		for(int j=1;j<Config.HEIGHT/64-1;j++){
			for (int i = 0; i < Config.WIDTH/64; i++) {
			if(j!=4){
				if(Math.random()>0.5){
					walls.add(new Wall(64*i,64*j));
				}else{
					grass.add(new Grass(64*i, 64*j));
				}
			}else{
				if(Math.random()>0.5){
					water.add(new Water(64*i,64*j));
				}else{
					steels.add(new Steel(64*i, 64*j));
				}
			}
			}
			try {
				SoundUtils.play("res/snd/add.wav");
				SoundUtils.play("res/snd/start.wav", true);
			} catch (Exception e) {
				// TODO: handle exception
			}
			}
			tank=new Tank(0,64*8);
			pokemon=new Pokemon(Config.WIDTH-64,0);
		/*
		 * Pokemon p1=new Pokemon(0,0); pokemons.add(p1);
		 */
		/*
		 * Pokemon p2=new Pokemon(Config.WIDTH-64,0); pokemons.add(p2);
		 */
	}

	/**
	 * 鼠标down事件
	 * 
	 * @param key
	 *            0为左键 1为右键
	 * @param x
	 *            x坐标
	 * @param y
	 *            y坐标
	 */
	protected void onMouseEvent(int key, int x, int y){
		if(key==0){
			System.out.println("您点击了左键");
		}else if(key==1){
			System.out.println("您点击了右键");
		}
	}

	/**
	 * 按键事件
	 * 
	 * @param key
	 *            按键，{@code Keyboard.key_xx}
	 */
	protected void onKeyEvent(int key){
		if(key==Keyboard.KEY_RIGHT){
			tank.move("right"); 
		}
		if(key==Keyboard.KEY_LEFT){
			tank.move("left");
		}
		if(key==Keyboard.KEY_UP){
			tank.move("up");
		}
		if(key==Keyboard.KEY_DOWN){
			tank.move("down");
		}
		if(key==Keyboard.KEY_RETURN){
			SoundUtils.stop("res/snd/start.wav");
		}
		if(key==Keyboard.KEY_SPACE){
			Bullet shot=tank.shot();
			if(shot!=null){
				bullets.add(shot);
			}
		}
	}

	/**
	 * 当屏幕刷新时的回调
	 */
	protected void onDisplayUpdate(){
		for(Wall wall:walls){
			wall.draw();
		}
		for(Water water:water){
			water.draw();
		}
		// 绘制爆炸物
		for(Bullet bullet:bullets){
			bullet.draw();
		}
		for(Bullet bullet:leafbullets){
			bullet.drawLeaf();
		}
		for(Steel steel:steels){
			steel.draw();
		}
		tank.draw();
		pokemon.draw();
		/*
		 * for (Pokemon pokemon : pokemons) { pokemon.draw(); }
		 */
		for(Grass grass:grass){
			grass.draw();
		}
		for (Blast bl : blasts) {
			bl.draw();
		}
		//判断铁墙 是否和坦克碰撞
		for (Steel steel:steels) {
			boolean checkHit = tank.checkHit(steel);
			if(checkHit){
				break;
			}
		}
		for (Wall wall:walls) {
			boolean checkHit = tank.checkHit(wall);
			if(checkHit){
				break;
			}
		}
		for (Water water:water) {
			boolean checkHit = tank.checkHit(water);
			if(checkHit){
				break;
			}
		}
		//判断铁墙 是否和坦克碰撞
		for (Steel steel:steels) {
			boolean checkHit = pokemon.checkHit(steel);
			if(checkHit){
				break;
			}
			/*
			 * for(Pokemon pokemon:pokemons) { boolean checkHit = pokemon.checkHit(steel);
			 * if(checkHit){ break; } }
			 */
		}
		for (Wall wall:walls) {
			boolean checkHit = pokemon.checkHit(wall);
			if(checkHit){
				break;
			}
			/*
			 * for(Pokemon pokemon:pokemons) { boolean checkHit = pokemon.checkHit(wall);
			 * if(checkHit){ break; } }
			 */
		}
		for (Water water:water) {
			boolean checkHit = pokemon.checkHit(water);
			if(checkHit){
				break;
			}
			/*
			 * for(Pokemon pokemon:pokemons) { boolean checkHit = pokemon.checkHit(water);
			 * if(checkHit){ break; } }
			 */
		}
		//判断子弹是否和物体碰撞
				for (Bullet bullet : bullets) {
					for (Steel steel:steels) {
						boolean flag = bullet.chechHit(steel);
						if(flag){
							//1.消灭子弹
							bullets.remove(bullet);
							
							//2.显示爆炸
							Blast showBlast = steel.showBlast();
							blasts.add(showBlast);
						}
					}
				}
				
				for (Bullet bullet : leafbullets) {
					for (Steel steel:steels) {
						boolean flag = bullet.leafchechHit(steel);
						if(flag){
							//1.消灭子弹
							leafbullets.remove(bullet);
							
							//2.显示爆炸
							Blast showBlast = steel.showBlast();
							blasts.add(showBlast);
						}
					}
				}
				
				
				// 判断爆炸物是否可以消失了
				for (Blast bl : blasts) {
					if(bl.isDesctory()){
						blasts.remove(bl);
					}
				}
				
				for (Steel steel:steels) {
					if(steel.getBlood()){
						Blast stopBlast =steel.stopBlast();
						blasts.add(stopBlast);
						steels.remove(steel);
					}
				}
				
				
				//判断子弹是否和物体碰撞
				for (Bullet bullet : bullets) {
					for (Wall wall:walls) {
						boolean flag = bullet.chechHit(wall);
						if(flag){
							//1.消灭子弹
							bullets.remove(bullet);
							
							//2.显示爆炸
							Blast showBlast = wall.showBlast();
							blasts.add(showBlast);
						}
					}
				}
				//判断子弹是否和物体碰撞
				for (Bullet bullet : leafbullets) {
					for (Wall wall:walls) {
						boolean flag = bullet.leafchechHit(wall);
						if(flag){
							//1.消灭子弹
							leafbullets.remove(bullet);
							
							//2.显示爆炸
							Blast showBlast = wall.showBlast();
							blasts.add(showBlast);
						}
					}
				}
				tank.checkHit(pokemon);
				pokemon.checkHit(tank);
				for (Wall wall:walls) {
					if(wall.getBlood()){
						Blast stopBlast =wall.stopBlast();
						blasts.add(stopBlast);
						walls.remove(wall);
					}
				}
				pokemon.move();
				Bullet shot=pokemon.shot();
				if(shot!=null){ leafbullets.add(shot); } 
		/*
		 * for(Pokemon pokemon:pokemons) { pokemon.move(); Bullet shot=pokemon.shot();
		 * if(shot!=null){ leafbullets.add(shot); } }
		 */
				for (Bullet bullet : bullets) {
						boolean flag = bullet.chechHit(pokemon);
						if(flag){
							try {
								SoundUtils.play("res/snd/hit.wav", true);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//1.消灭子弹
							bullets.remove(bullet);
							
							//2.显示爆炸
							Blast showBlast = pokemon.showBlast();
							blasts.add(showBlast);
						}
				}
				if(pokemon.getBlood()){
					Blast stopBlast =pokemon.stopBlast();
					blasts.add(stopBlast);
					System.out.println("YOU WIN");
					pokemon.destory();
					this.stop();
				}
				
				
				for (Bullet bullet : leafbullets) {
					boolean flag = bullet.chechHit(tank);
					if(flag){

						try {
							SoundUtils.play("res/snd/hit.wav", true);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//1.消灭子弹
						leafbullets.remove(bullet);
						
						//2.显示爆炸
						Blast showBlast = tank.showBlast();
						blasts.add(showBlast);
					}
			}
			if(tank.getBlood()){
				Blast stopBlast =tank.stopBlast();
				blasts.add(stopBlast);
				System.out.println("YOU LOSE");
				tank.destory();
				this.stop();
			}
	}
}
