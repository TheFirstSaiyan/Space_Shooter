package com.hari.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import static com.hari.spaceshooter.SpaceShooter.buttonToDraw;
import static com.hari.spaceshooter.SpaceShooter.gamestate;

public class DrawScoreBoard {
    BitmapFont scoreText,bestScoreText;
    ShapeRenderer shapeRenderer;
    Texture pauseButton,playButton;
    SpriteBatch batch;
    public DrawScoreBoard() {
        pauseButton=new Texture("pause.png");
        playButton=new Texture("play.png");
        batch = new SpriteBatch();
        scoreText = new BitmapFont();
        bestScoreText=new BitmapFont();
        scoreText.setColor(Color.WHITE);
        scoreText.getData().setScale(2);
        bestScoreText.getData().setScale(1);
        bestScoreText.setColor(Color.GREEN);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.BLACK);

    }
    public void show()
    {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 100);
        shapeRenderer.end();
        batch.begin();
        scoreText.draw(batch, "score : "+Integer.toString(SpaceShooter.score), Gdx.graphics.getWidth() / 2 - 40, 60);
        bestScoreText.draw(batch,"best : "+Integer.toString(SpaceShooter.highscore),Gdx.graphics.getWidth()-100,25);
        if(buttonToDraw.equals("pause"))
            batch.draw(pauseButton,20,20,50,50);
        else if(buttonToDraw.equals("play"))
            batch.draw(playButton,20,20,50,50);
        batch.end();
        if(Gdx.input.getX()>=20&&Gdx.input.getX()<=70
                &&Gdx.input.getY()<=Gdx.graphics.getHeight()-20&&Gdx.input.getY()>=Gdx.graphics.getHeight()-70) {

            if(Gdx.input.justTouched()&&buttonToDraw.equals("pause")) {

                buttonToDraw="play";
            }
            else if(Gdx.input.justTouched()) {
                gamestate = 1;
                buttonToDraw = "pause";
            }
        }




    }
}
