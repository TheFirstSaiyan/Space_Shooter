package com.hari.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Nikhileshwar on 9/1/2017.
 */

public class DrawBackGround {
    SpriteBatch batch;
    Texture bg;
    public DrawBackGround(){
        batch=new SpriteBatch();

        bg=new Texture("bg.png");


    }
    public void show()
    {
        batch.begin();
        batch.draw(bg,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

    }

}
