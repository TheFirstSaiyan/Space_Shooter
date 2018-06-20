package com.hari.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.hari.spaceshooter.SpaceShooter.gameoversound;



public class MainMenu {

    SpriteBatch batch;
    Texture playbutton;
    Texture bg;
    public MainMenu()
    {
bg=new Texture("niceone.jpg");
        playbutton=new Texture("play.png");
        batch=new SpriteBatch();

    }
    public void show()
    {
        batch.begin();
        batch.draw(bg,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(playbutton,250,50,100,100);
        batch.end();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        if(Gdx.input.getX()>=250&&Gdx.input.getX()<=350
                &&Gdx.input.getY()<=Gdx.graphics.getHeight()-50&&Gdx.input.getY()>=Gdx.graphics.getHeight()-150) {

            if(Gdx.input.justTouched()) {
                gameoversound.play();
                SpaceShooter.gamestate = 2;
            }
        }
    }

}
