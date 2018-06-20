package com.hari.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.badlogic.gdx.math.MathUtils.random;
import static com.hari.spaceshooter.SpaceShooter.shapeRenderer;



public class Laser {
    float laserX,laserY,laserY2;
    Rectangle laser;

    public Laser()
    {

        laserX=SpaceShooter.posX+DrawPlayer.player.getWidth()/2;
        laserY=8;
        laserY2=SpaceShooter.posY+DrawPlayer.player.getHeight();

    }
    public void update()
    {
        laserY2 += 8;

    }
     public void drawLaser()
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        laser = new Rectangle(laserX, laserY2, 3, laserY);
        shapeRenderer.setColor(random.nextFloat(),random.nextFloat(),random.nextFloat(),1);
        shapeRenderer.rect(laserX, laserY2, 3, laserY);
        shapeRenderer.end();


    }

    public boolean collidesWith(Aliens alien)
    {
        if(this.laserX>alien.alienX&&this.laserX<alien.alienTex.getWidth()+alien.alienX&&this.laserY2>alien.alienY
                &&this.laserY2<alien.alienTex.getHeight()+alien.alienY&&this.laserY2+laserY<Gdx.graphics.getHeight())
            return true;
        else
            return false;

    }
}
