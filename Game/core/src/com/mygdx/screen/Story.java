package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.mygdx.game.HeroesOfOlympus;
import com.mygdx.level.*;

import java.io.FileNotFoundException;

public class Story implements Screen {

    HeroesOfOlympus game;
    VideoPlayer video;
    boolean finished = false, videoLoaded = false;
    FileHandle videoFile;

    public Story(HeroesOfOlympus game, String path) throws FileNotFoundException {
        this.game = game;

        //Initialise video
        video = VideoPlayerCreator.createVideoPlayer();

        video.setOnVideoSizeListener(new VideoPlayer.VideoSizeListener() {
            @Override
            public void onVideoSize(float width, float height) {
                videoLoaded = true;
            }
        });
        //Create file handle to locate internal file
        videoFile = Gdx.files.internal(path);
        //Check if file exists
        System.out.println("Exists?: " + videoFile.exists());
        //play the specified file
        //video.play(videoFile);

        // it is important to do a resize after starting the video
        video.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Set when the video is finished player to true on completion listener
        video.setOnCompletionListener(new VideoPlayer.CompletionListener() {
            @Override
            public void onCompletionListener(FileHandle file) {
                finished = true;
                System.out.println("onCompletionListener");
            }
        });
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isTouched()) {
            this.dispose();
            if(game.level instanceof Level1){
                game.setScreen(new MainGameScreen(game, new Level2()));
            } else if(game.level instanceof Level2){
                game.setScreen(new MainGameScreen(game, new Level3()));
            } else if(game.level instanceof Level3){
                game.setScreen(new MainGameScreen(game, new Level4()));
            } else if(game.level instanceof Level5){
                Gdx.app.exit();
            } else {
                game.setScreen(new MainGameScreen(game, new Level1()));
            }
            if (video.isPlaying()) video.stop();
        }

        if (videoLoaded) {
            if (!video.render()) {
                // end video
                videoLoaded = false;
                this.dispose();
                if(game.level instanceof Level1){
                    game.setScreen(new MainGameScreen(game, new Level2()));
                } else if(game.level instanceof Level2){
                    game.setScreen(new MainGameScreen(game, new Level3()));
                } else if(game.level instanceof Level3){
                    game.setScreen(new MainGameScreen(game, new Level4()));
                } else if(game.level instanceof Level5){
                    Gdx.app.exit();
                } else {
                    game.setScreen(new MainGameScreen(game, new Level1()));
                }
                if (video.isPlaying()) video.stop();
            }
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}