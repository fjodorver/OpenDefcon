package net.vershinin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Sprite sprite;

    private Camera cam1, cam2;
    private Viewport v1, v2;

    private static final float WIDTH = 300;
    private static final float HEIGHT = 300;

	@Override
	public void create () {
        cam1 = new OrthographicCamera();
        cam2 = new OrthographicCamera();
        v1 = new StretchViewport(WIDTH, HEIGHT, cam1);
        v2 = new StretchViewport(WIDTH, HEIGHT, cam2);

		batch = new SpriteBatch();
		sprite = new Sprite(new Texture("map.png"));
        sprite.setSize(WIDTH, HEIGHT);

        Gdx.input.setInputProcessor(new DefconViewportController(v1, v2));
	}

    @Override
    public void resize(int width, int height) {
        v1.update(width, height, true);
        v2.update(width, height, true);
    }

    @Override
	public void render () {
        cam1.update();
        cam2.update();
        Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        v1.apply();
        batch.setProjectionMatrix(cam1.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();

        v2.apply();
        batch.setProjectionMatrix(cam2.combined);
		batch.begin();
        sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		sprite.getTexture().dispose();
	}
}
