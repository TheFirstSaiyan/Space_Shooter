package com.hari.spaceshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class SpaceShooter extends ApplicationAdapter {
   SpriteBatch batch;
    Preferences preferences;
    DrawPlayer hero;
    DrawScoreBoard scoreBoard;
    MainMenu mainMenu;
    GameOverScreen gameOverScreen;
    DrawBackGround drawBackGround;
    Random random;
    BitmapFont instructionFont;
    int speed;
    static ShapeRenderer shapeRenderer;
    static ArrayList<Laser> lasers;
    static float posX, posY, gap;
    static Rectangle player;
    static ArrayList<Aliens> aliens;
    static ArrayList<Aliens> aliensToDestroy;
    static ArrayList<Laser> lasersToDestroy;
    static int gamestate;
    static int score;
    static String buttonToDraw="pause";
    static int numberOfAliens ;
    static Sound lasersound, mainmenusound, gameoversound, playeroutsound,scoresound,vegeta,L;
    static  int highscore;
     static ArrayList<DestructionParticles>destructionParticles;
    static int destructionTime;
static  boolean drawPlayer=true;
/*gamestatee=0 mainmenu
  gamestate=1  playing
  gamestate=2   ingame but no motion
  gamestate=-1 gameover*/




    @Override
    public void create() {
        highscore=0;
        destructionTime=0;
        numberOfAliens=500;
        aliensToDestroy = new ArrayList<Aliens>();
        lasersToDestroy = new ArrayList<Laser>();
       batch = new SpriteBatch();
        hero =new DrawPlayer();
        scoreBoard=new DrawScoreBoard();
        mainMenu=new MainMenu();
        gameOverScreen=new GameOverScreen();
        drawBackGround=new DrawBackGround();
        gap = 30;
        shapeRenderer = new ShapeRenderer();
        gamestate = 0;
        aliens = new ArrayList<Aliens>();
        lasers = new ArrayList<Laser>();
        destructionParticles=new ArrayList<DestructionParticles>();
        random = new Random();
        posX = Gdx.graphics.getWidth() / 2 - gap / 2;
        posY = Gdx.graphics.getHeight() / 2 - gap / 2;
        speed=(int)gap/3;
        score = 0;
        instructionFont=new BitmapFont();
        lasersound = Gdx.audio.newSound(Gdx.files.internal("lasersound.mp3"));
        mainmenusound = Gdx.audio.newSound(Gdx.files.internal("mainmenusound.mp3"));
        mainmenusound.loop();
        mainmenusound.play();
        vegeta=Gdx.audio.newSound(Gdx.files.internal("vegeta.mp3"));
        vegeta.loop();
        vegeta.pause();
        L=Gdx.audio.newSound(Gdx.files.internal("duringgamesound.mp3"));
        L.loop();
        L.pause();
        gameoversound = Gdx.audio.newSound(Gdx.files.internal("gameoversound.mp3"));
        playeroutsound = Gdx.audio.newSound(Gdx.files.internal("playeroutsound.mp3"));
        scoresound = Gdx.audio.newSound(Gdx.files.internal("scoresound.mp3"));
        preferences= Gdx.app.getPreferences("my preference");
        highscore=preferences.getInteger("highscore",0);
        for (int i = 0; i < numberOfAliens; i++) //initially adding preset number of aliens
        {
            aliens.add(new Aliens());
        }



    }


        @Override
        public void render () {

            SimpleDateFormat time_formatter = new SimpleDateFormat("ss");
            String current_time_str = time_formatter.format(System.currentTimeMillis());
            System.out.println(time_formatter);
            if (gamestate == 0)
            {

                mainMenu.show();

            }
            else if(gamestate!=-1)
            {
                mainmenusound.stop();
                L.resume();

                drawBackGround.show();
                if(drawPlayer)

                hero.show();
                player = new Rectangle(posX, posY, DrawPlayer.player.getWidth(), DrawPlayer.player.getHeight());
                if (gamestate == 1) {
                  //Gdx.app.log("fps",Integer.toString(Gdx.graphics.getFramesPerSecond()));
                    if (buttonToDraw.equals("pause")) {
                        for (int j = 0; j < aliens.size(); j++) {
                            if (aliens.get(j) != null) {
                                aliens.get(j).show(j * 15);
                                aliens.get(j).update();
                            }

                        }


                        for (int i = 0; i < lasers.size(); i++) {
                            lasers.get(i).drawLaser();
                            lasers.get(i).update();
                            for (int l = 0; l < aliens.size(); l++) {
                                if (lasers.get(i).collidesWith(aliens.get(l))) {
                                    scoresound.play();
                                    //Gdx.app.log("hits", "hit");
                                    score += 1;
                                    if (score > highscore)
                                        highscore = score;
                                    aliens.set(l, new Aliens());
                                    lasersToDestroy.add(lasers.get(i));
                                }
                            }
                            preferences.putInteger("highscore", highscore);
                            preferences.flush();
                            if (lasers.get(i).laserY2 > Gdx.graphics.getHeight())
                                lasersToDestroy.add(lasers.get(i));


                        }
                        lasers.removeAll(lasersToDestroy);
                        lasersToDestroy.clear();


                        if (Gdx.input.justTouched()||Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

                            if (gamestate == 1 && !(Gdx.input.getX() >= 20 && Gdx.input.getX() <= 70
                                    && Gdx.input.getY() <= Gdx.graphics.getHeight() - 20 && Gdx.input.getY() >= Gdx.graphics.getHeight() - 70)) {
                                lasersound.play();
                                lasers.add(new Laser());

                            }

                        }
                    } else if (buttonToDraw.equals("play")) {

                        for (int j = 0; j < aliens.size(); j++) {
                            if (aliens.get(j) != null) {
                                aliens.get(j).show(j * 15);
                            }

                        }
                        for (int i = 0; i < lasers.size(); i++) {
                            lasers.get(i).drawLaser();

                        }


                    }

                }

                scoreBoard.show();

                if ((gamestate == 2 || gamestate == 1) &&buttonToDraw.equals("pause"))
                {
                    if(gamestate==2)
                    {
                        batch.begin();
                        instructionFont.setColor(Color.GOLD);
                        instructionFont.getData().setScale(3);
                        instructionFont.draw(batch,"TRY NOT TO LOSE!\n\n\n",Gdx.graphics.getWidth()/2-200,Gdx.graphics.getHeight()/2+200);
                        instructionFont.getData().setScale(2);
                        instructionFont.setColor(Color.WHITE);
                        instructionFont.draw(batch,"\npress W,S,A,D to begin",Gdx.graphics.getWidth()/2-150,Gdx.graphics.getHeight()/2+170);
                        batch.end();
                    }

                    if((Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A) )&& posX >= 0)
                    {
                       gamestate = 1;
                       posX -= speed;
                    } else if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D) )
                            && posX + gap <= Gdx.graphics.getWidth())
                    {
                        gamestate = 1;
                        posX += speed;
                    } else if ((Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.W) )
                            && posY + gap <= Gdx.graphics.getHeight())
                    {
                        gamestate = 1;
                        posY +=speed;
                    } else if ((Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.S) ) && posY >= 100)
                    {
                        gamestate = 1;
                        posY -= speed;
                    }

                }
            }

            if (gamestate == -1)
            {
                    L.pause();
                    vegeta.resume();
                    gameOverScreen.show();

                }



        }

        @Override
        public void dispose ()
        {
            batch.dispose();
            lasersound.dispose();
            mainmenusound.dispose();
            vegeta.dispose();
            gameoversound.dispose();
            playeroutsound.dispose();
            L.dispose();
            scoresound.dispose();

        }
    }

