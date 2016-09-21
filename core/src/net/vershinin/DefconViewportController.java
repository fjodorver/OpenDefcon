package net.vershinin;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DefconViewportController extends InputAdapter {

    private InputListener inputListener;

    public DefconViewportController(final Viewport viewport1, final Viewport viewport2) {
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
}