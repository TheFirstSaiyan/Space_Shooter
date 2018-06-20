package com.hari.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

import static com.hari.spaceshooter.SpaceShooter.destructionParticles;
import static com.hari.spaceshooter.SpaceShooter.destructionTime;
import static com.hari.spaceshooter.SpaceShooter.drawPlayer;
import static com.hari.spaceshooter.SpaceShooter.player;
import static com.hari.spaceshooter.SpaceShooter.playeroutsound;
import static com.hari.spaceshooter.SpaceShooter.posX;
import static com.hari.spaceshooter.SpaceShooter.posY;




public class Aliens {
    SpriteBatch batch;
    public Texture alienTex;

    int randomX,randomY,alienX,alienY;
    Random random=new Random();
    Rectangle alien;
    boolean state=true;
    static boolean explosionActive=false;
    Color c;

    public void show(int setoff)
    {


        alien=new Rectangle(randomX+setoff,randomY+setoff,alienTex.getWidth(),alienTex.getHeight());//create a Rectangle for every alien
        batch.begin();
        batch.setColor(c);
        alienX=randomX+setoff;
        alienY=randomY+setoff;
        if(randomX+setoff>=Gdx.graphics.getWidth()-alienTex.getWidth())
        {
            randomX=0;
        }
        batch.draw(alienTex,randomX+setoff,randomY+setoff);
        batch.end();
        if(Intersector.overlaps(alien,SpaceShooter.player))//check for collision of player with aliens
        {
            if(state)
                explosionActive=true;



            for (int u = 0; u < 20 && state; u++)

                destructionParticles.add(new DestructionParticles());
            state = false;//confirm if the collision has happened atleast once




                   drawPlayer=false;


            SpaceShooter.playeroutsound.play();

        }
        if(explosionActive)
            destroyPlayer();
        if(!explosionActive&&!state)
        {
            state=true;
            destructionTime=0;
            SpaceShooter.gamestate=-1;
            destructionParticles.clear();
            drawPlayer=true;
        }


        if(randomY+setoff+alienTex.getHeight()<100) //reset x and y position of aliens whey they move off the screen
        {
            randomX=random.nextInt(Gdx.graphics.getWidth());
            randomY =  Gdx.graphics.getHeight();
        }
    }

public void destroyPlayer()
{
    for(int u = 0; u< destructionParticles.size(); u++)
    {        destructionParticles.get(u).explode();
             destructionParticles.get(u).show();


    }
    if(destructionTime>=200)
       explosionActive=false;
}





    public Aliens ()
    {
        alienTex=new Texture("alien.png");
        batch=new SpriteBatch();
        randomX=random.nextInt(Gdx.graphics.getWidth()-200);
        randomY=random.nextInt(Gdx.graphics.getHeight())+Gdx.graphics.getHeight();
        c=new Color(random.nextFloat(),random.nextFloat(),random.nextFloat(),1);

    }
    public void update()
    {
        randomY-=2;
    }
}
