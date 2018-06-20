package com.hari.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.hari.spaceshooter.SpaceShooter.destructionTime;


/**
 * Created by Nikhileshwar on 9/1/2017.
 */
public class GameOverScreen {

    SpriteBatch batch;
    Texture gameover;
    BitmapFont font,exitfont,scorefont,bestfont;

    public GameOverScreen()
    {
        font=new BitmapFont();
        exitfont=new BitmapFont();
        scorefont=new BitmapFont();
        bestfont=new BitmapFont();
        font.getData().setScale(2);
        font.setColor(Color.YELLOW);
        exitfont.getData().setScale(1);
        exitfont.setColor(Color.RED);
        scorefont.getData().setScale(2);
        scorefont.setColor(Color.GOLD);
        bestfont.getData().setScale(2);
        bestfont.setColor(Color.GOLD);
        gameover=new Texture("gameover.png");
        batch=new SpriteBatch();

    }
    public void show()
    {
        batch.begin();
        batch.draw(gameover,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        font.draw(batch,"click to play again",210,100);
        exitfont.draw(batch,"ESC to exit",280,50);
        scorefont.draw(batch,"SCORE : "+Integer.toString(SpaceShooter.score),250,450);
        bestfont.draw(batch,"BEST     : "+Integer.toString(SpaceShooter.highscore),250,420);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        batch.end();
        if(Gdx.input.justTouched()&&destructionTime==0) {
            SpaceShooter.vegeta.pause();
            SpaceShooter.gameoversound.play();
            SpaceShooter.lasers.clear();
            SpaceShooter.gamestate = 2;
            SpaceShooter.aliens.clear();
            SpaceShooter.lasers.clear();
            SpaceShooter.posX=Gdx.graphics.getWidth()/2-SpaceShooter.gap/2;
            SpaceShooter.posY=Gdx.graphics.getHeight()/2-SpaceShooter.gap/2;
            SpaceShooter.aliens.clear();
            for(int i=0;i<SpaceShooter.numberOfAliens;i++) {
                SpaceShooter.aliens.add(new Aliens());
            }
            SpaceShooter.score=0;

        }


    }
}
