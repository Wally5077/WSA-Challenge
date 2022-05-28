package models.collisions;

import models.sprites.Fire;
import models.sprites.Sprite;
import models.sprites.Water;

/**
 * @author - wally55077@gmail.com
 */
public class WaterFireSpriteCollision extends SpriteCollision<Water, Fire> {

    public WaterFireSpriteCollision() {
        super();
    }

    public WaterFireSpriteCollision(SpriteCollision<? extends Sprite, ? extends Sprite> spriteCollision) {
        super(spriteCollision);
    }

    @Override
    protected Class<Water> getCollideSpriteClass() {
        return Water.class;
    }

    @Override
    protected Class<Fire> getBeCollidedSpriteClass() {
        return Fire.class;
    }

    @Override
    protected void doCollision(Water water, Fire fire) {
        water.removeSelf();
        fire.removeSelf();
        System.out.println("水火不容");
    }
}
