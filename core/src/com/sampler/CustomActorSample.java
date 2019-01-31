package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;

public class CustomActorSample extends SampleBase {
    private static final Logger log = new Logger(CustomActorSample.class.getName(), Logger.DEBUG);

    public static final SampleInfo SAMPLE_INFO = new SampleInfo(CustomActorSample.class);

    private Viewport viewport;

    private Stage stage;
    private Texture texture;

    @Override
    public void create() {
        // Create actors and variables
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // Viewport will automatically create a camera to use.
        viewport = new FitViewport(1080,720);

        // Stage will automatically create a batch to use
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal(""));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
