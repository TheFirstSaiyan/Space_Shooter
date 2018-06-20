package com.hari.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import static com.hari.spaceshooter.SpaceShooter.destructionTime;
import static com.hari.spaceshooter.SpaceShooter.player;
import static com.hari.spaceshooter.SpaceShooter.shapeRenderer;

/**
 * Created by Nikhileshwar on 9/4/2017.
 */

public class DestructionParticles {

    int particleX,particleY;
    Random random=new Random();
    public DestructionParticles()
    {

        particleX=(int)(SpaceShooter.posX+player.getWidth()/2)+random.nextInt(20)-10;//,random.nextInt())
        particleY=(int)(SpaceShooter.posY+player.getHeight()/2)+random.nextInt(20)-10;

    }
    public void show()
    {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
       // particleSpeck= new Rectangle(particleX, particleY, 3, 9);
        shapeRenderer.setColor(random.nextFloat(),random.nextFloat(),random.nextFloat(),random.nextFloat());
        shapeRenderer.rect(particleX,particleY,3,9);
        shapeRenderer.end();
        destructionTime++;


    }
    public void explode()
    {
        //destructionTime++;
        particleY+=random.nextInt(10)-5;
        particleX+=random.nextInt(20)-10;
    }
}
