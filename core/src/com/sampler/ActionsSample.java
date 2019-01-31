package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.CustomActor;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;
import javafx.scene.shape.MoveTo;

// This imports all static methods so we can access them directly and shorten
// customActor.addAction(Actions.rotateBy(90f, 1));
// to
// customActor.addAction(rotateBy(90f, 1));
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class ActionsSample extends SampleBase {
    private static final Logger log = new Logger(ActionsSample.class.getName(), Logger.DEBUG);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(ActionsSample.class);

    private Viewport viewport;

    private Stage stage;
    private Texture texture;

    private CustomActor customActor;

    @Override
    public void create() {
        // Create actors and variables
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // Viewport will automatically create a camera to use.
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT);

        // Stage will automatically create a batch to use
        stage = new Stage(viewport);

        texture = new Texture(Gdx.files.internal("raw/custom-actor.png"));

        customActor = new CustomActor(new TextureRegion(texture));

        // If you do not set a size you will not be able to see the custom actor
        customActor.setSize(160, 80);
        customActor.setPosition(
                (WORLD_WIDTH - customActor.getWidth()) / 2,
                (WORLD_HEIGHT - customActor.getHeight()) / 2);



        stage.addActor(customActor);

        // Set ActionSample to be input processor
        Gdx.input.setInputProcessor(this);

        String LS = System.getProperty("line.separator");
        String TAB = "\t";
        log.debug(LS + "Press keys." + LS+
                TAB + "1 - RotateBy Action" + LS +
                TAB + "2 - FadeOut Action" + LS +
                TAB + "3 - FadeIn Action" + LS +
                TAB + "4 - ScaleTo Action" + LS +
                TAB + "5 - MoveTo Action" + LS +
                TAB + "6 - Sequential Action" + LS +
                TAB + "7 - Parallel Action" + LS);
    }

    @Override
    public boolean keyDown(int keycode) {
        // Clear will clear both actions and listeners
        customActor.clearActions();

        if (keycode == Input.Keys.NUM_1) {
            log.debug("RotateBy Action");
            customActor.addAction(rotateBy(90f, 1));
        } else if (keycode == Input.Keys.NUM_2) {
            log.debug("FadeOut Action");
            customActor.addAction((fadeOut(2f)));
        } else if (keycode == Input.Keys.NUM_3) {
            log.debug("FadeIn Action");
            customActor.addAction(fadeIn(2f));
        } else if (keycode == Input.Keys.NUM_4) {
            log.debug("ScaleTo Action");
            customActor.addAction(scaleTo(1.5f, 1.5f, 2f));
        } else if (keycode == Input.Keys.NUM_5) {
            log.debug("MoveTo Action");
            customActor.addAction(moveTo(100, 100, 3f));
        } else if (keycode == Input.Keys.NUM_6) {
            log.debug("Sequential Action");
            Action action = sequence(
                    fadeOut(1f),
                    fadeIn(0.5f));
            customActor.addAction(action);
        } else if (keycode == Input.Keys.NUM_7) {
            log.debug("Parallel Action");
            Action action = parallel(
                    rotateBy(90, 2f),
                    moveBy(50, 50, 2f)
            );
            customActor.addAction(action);
        }

        return true;
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
