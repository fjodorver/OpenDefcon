package net.vershinin;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by fjodor on 9/21/16.
 */
public class DefconViewport extends Viewport {

    public DefconViewport(float width, float height, Camera cam1) {
        setWorldSize(width, height);
        setCamera(cam1);
    }

    @Override
    public void update(int screenWidth, int screenHeight, boolean centerCamera) {
        setScreenWidth(screenWidth);
        Vector2 scaled = Scaling.fill.apply(getWorldWidth(), getWorldHeight(), screenWidth, screenHeight);
        int viewportWidth = Math.round(scaled.x);
        int viewportHeight = Math.round(scaled.y);

        // Center.
        setScreenBounds((screenWidth - viewportWidth) / 2, (screenHeight - viewportHeight) / 2, viewportWidth, viewportHeight);
        apply(centerCamera);
    }
}