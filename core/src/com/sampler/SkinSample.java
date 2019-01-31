package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.CustomActor;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

import java.awt.*;

public class SkinSample extends SampleBase {
    private static final Logger log = new Logger(SkinSample.class.getName(), Logger.DEBUG);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(SkinSample.class);

    private static final String UI_SKIN = "ui/uiskin.json";
    private Viewport viewport;

    private Stage stage;

    private AssetManager assetManager;
    private Skin skin;

    @Override
    public void create() {
        // Create actors and variables
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        assetManager = new AssetManager();
        assetManager.getLogger().setLevel(Logger.DEBUG);
        assetManager.load(UI_SKIN, Skin.class);
        assetManager.finishLoading();


        // Viewport will automatically create a camera to use.
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT);

        // Stage will automatically create a batch to use
        stage = new Stage(viewport);

        skin = assetManager.get(UI_SKIN);

        Gdx.input.setInputProcessor(stage);

        initUI();

    }

    private void initUI() {
        Table table = new Table();
        table.defaults().pad(20);

        for(int i = 0; i < 4; i++){
            TextButton textButton = new TextButton("Button " + i, skin);

            textButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    log.debug("Event: " + event + " Actor: " + actor);
                }
            });
            table.add(textButton);
        }

        table.row();
        for(int i = 0; i < 2; i++){
            CheckBox checkbox = new CheckBox("Checkbox " + i, skin);

            checkbox.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    log.debug("Event: " + event + " Actor: " + actor);
                }
            });
            table.add(checkbox);
        }

        // Use a custom rule in uiSkin JSON by adding style name to the checkbox constructor
        CheckBox cb = new CheckBox("CB", skin, "custom");
        cb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                log.debug("Event: " + event + " Actor: " + actor);
            }
        });
        table.add(cb);

        table.center();
        table.setFillParent(true);
        table.pack();

        stage.addActor(table);
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
        assetManager.dispose();
    }
}
