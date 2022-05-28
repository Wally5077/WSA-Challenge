package models.collisions;

import models.sprites.Sprite;

import static java.util.Objects.requireNonNull;

/**
 * @author - wally55077@gmail.com
 */
public abstract class SpriteCollision<CollideSprite extends Sprite, BeCollidedSprite extends Sprite> {

    private SpriteCollision<? extends Sprite, ? extends Sprite> spriteCollision;

    public SpriteCollision() {
        this(new NullSpriteCollision());
    }

    public SpriteCollision(SpriteCollision<? extends Sprite, ? extends Sprite> spriteCollision) {
        setSpriteCollision(spriteCollision);
    }

    @SuppressWarnings("unchecked")
    public void collide(Sprite collideSprite, Sprite beCollidedSprite) {
        requireNonNull(collideSprite);
        requireNonNull(beCollidedSprite);
        var collideSpriteClass = getCollideSpriteClass();
        var beCollidedSpriteClass = getBeCollidedSpriteClass();
        if (collideSpriteClass.isInstance(collideSprite) && beCollidedSpriteClass.isInstance(beCollidedSprite)) {
            doCollision((CollideSprite) collideSprite, (BeCollidedSprite) beCollidedSprite);
        }
        if (collideSpriteClass.isInstance(beCollidedSprite) && beCollidedSpriteClass.isInstance(collideSprite)) {
            doCollision((CollideSprite) beCollidedSprite, (BeCollidedSprite) collideSprite);
        }
        spriteCollision.collide(collideSprite, beCollidedSprite);
    }

    protected abstract Class<CollideSprite> getCollideSpriteClass();

    protected abstract Class<BeCollidedSprite> getBeCollidedSpriteClass();

    protected abstract void doCollision(CollideSprite collideSprite, BeCollidedSprite beCollidedSprite);

    public void setSpriteCollision(SpriteCollision<? extends Sprite, ? extends Sprite> spriteCollision) {
        this.spriteCollision = spriteCollision;
    }

    private static class NullSpriteCollision extends SpriteCollision<Sprite, Sprite> {

        public NullSpriteCollision() {
            super(null);
        }

        @Override
        protected Class<Sprite> getCollideSpriteClass() {
            return Sprite.class;
        }

        @Override
        protected Class<Sprite> getBeCollidedSpriteClass() {
            return Sprite.class;
        }

        @Override
        public void collide(Sprite collideSprite, Sprite beCollidedSprite) {
            // do nothing
        }

        @Override
        protected void doCollision(Sprite sprite, Sprite sprite2) {
            // do nothing
        }
    }
}
