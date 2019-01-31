package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sampler.common.CustomActor;
import com.sampler.common.SampleBase;
import com.sampler.common.SampleInfo;
import com.sampler.utils.GdxUtils;

// Tables are used to layout UI
public class TableSample extends SampleBase {
    private static final Logger log = new Logger(TableSample.class.getName(), Logger.DEBUG);

    private static final float WORLD_WIDTH = 1080f;
    private static final float WORLD_HEIGHT = 720f;
    public static final SampleInfo SAMPLE_INFO = new SampleInfo(TableSample.class);

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

        initUI();
    }

    private void initUI() {
        Table table = new Table();

        // Add defaults for every cell in the table. All actors are placed in a cell.
        table.defaults().space(20);

        for(int i = 0; i < 6; i++) {
            CustomActor customActor = new CustomActor(new TextureRegion(texture));
            // Remember to set the size for your actors because by default it is all 0
            customActor.setSize(180, 60);

            // Add the actor to the table on row 0 (from the bottom up)
            table.add(customActor);

            // Move to the next row for the next actor. If we do not call this then the
            // Actors we are adding will be side by side and not on top of eachother
            table.row();

        }

        table.row();
        CustomActor actor = new CustomActor(new TextureRegion(texture));
        actor.setSize(200, 40);
        // You can chain methods : add an actor, fill the actors cell on X, expand the cell on X and float left
        table.add(actor).fillX().expandX().left();

        // Will center the table inside of the screen (center will center in the screen unless you tell it to fill the parent)
        table.center();

        // Sets the table to fill the parent inside stage
        table.setFillParent(true);

        // Packs up the table which means it reloads and resizes the table with the most current changes
        table.pack();

        // Add the table to the stage like you would any other actor.
        stage.addActor(table);

        // Actors have a built in debug to help with debugging UI.
        // Adds blue border around table and green border around individual actors since we are debugging stage
        stage.setDebugAll(true);
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
