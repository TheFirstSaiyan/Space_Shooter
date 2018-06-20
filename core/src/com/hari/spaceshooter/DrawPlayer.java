package com.hari.spaceshooter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class DrawPlayer  {
   static Texture player;
    SpriteBatch batch;
    public DrawPlayer()
    {
        player=new Texture("player2.png");
        batch=new SpriteBatch();
    }
    public void show()
    {
        batch.begin();
        batch.setColor(Color.PINK);
        batch.draw(player,SpaceShooter.posX,SpaceShooter.posY,player.getWidth(),player.getHeight());
        batch.end();
    }
}
