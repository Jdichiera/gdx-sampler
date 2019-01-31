package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.CustomActor;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

public class CustomActorSample extends SampleBase {
    private static final Logger log = new Logger(CustomActorSample.class.getName(), Logger.DEBUG);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(CustomActorSample.class);

    private Viewport viewport;

    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        // Create actors and variables
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // Viewport will automatically create a camera to use.
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT);

        // Stage will automatically create a batch to use
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        CustomActor customActor = new CustomActor(new TextureRegion(texture));

        // If you do not set a size you will not be able to see the custom actor
        customActor.setSize(160, 80);
        customActor.setPosition(
                (WORLD_WIDTH - customActor.getWidth()) / 2,
                (WORLD_HEIGHT - customActor.getHeight()) / 2);

        customActor.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               log.debug("CustomActor clicked event | x: " + x + " y: " + y);
           }
        });

        stage.addActor(customActor);

        // Stage has an input processor built in
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        GdxUtils.clearScreen();

        // Act will make all actors do their things. Act will be called every frame
        stage.act();

        // Will draw all actors. This includes set projection matrix, begin and end batch
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        texture.dispose();
    }
}
