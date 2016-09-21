package net.vershinin;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DefconViewportController extends InputAdapter {

    private InputListener inputListener;
    private Viewport v1, v2;

    public DefconViewportController(final Viewport viewport1, final Viewport viewport2) {
        v1 = viewport1;
        v2 = viewport2;
        inputListener = new DragListener(){
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                float dx = getDeltaX();
                if(Math.abs(dx) >= 5){
                    dx = (dx > 0) ? 5 : -5;
                }
                viewport1.setScreenX((int) (viewport1.getScreenX() - dx));
                viewport2.setScreenX(viewport1.getScreenX() + viewport1.getScreenWidth());
                if(Math.abs(viewport1.getScreenX()) > viewport1.getScreenWidth()){
                    viewport1.setScreenX(0);
                }
                if(viewport1.getScreenX() > 0){
                    viewport2.setScreenX(viewport1.getScreenX() - viewport1.getScreenWidth());
                }
            }
        };
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return inputListener.touchDown(new InputEvent(), screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        inputListener.touchDragged(new InputEvent(), screenX, screenY, pointer);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        update((ScalingViewport) v1, 2 * amount);
        update((ScalingViewport) v2, 2 * amount);
        v2.setScreenX(v1.getScreenX() + v1.getScreenWidth());
        if(v1.getScreenX() > 0){
            v2.setScreenX(v1.getScreenX() - v1.getScreenWidth());
        }
        return true;
    }

    private void update(ScalingViewport scalingViewport, int amount){
        int screenW = scalingViewport.getScreenWidth();
        int screenH = scalingViewport.getScreenHeight();
        float worldW = scalingViewport.getWorldWidth();
        float worldH = scalingViewport.getWorldHeight();
        Vector2 scaled = scalingViewport.getScaling().apply(worldH, worldW, screenW-amount, screenH-amount);
        int viewportWidth = Math.round(scaled.x);
        int viewportHeight = Math.round(scaled.y);
        scalingViewport.setScreenSize(viewportWidth, viewportHeight);
    }
}