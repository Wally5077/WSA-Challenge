package models;

import models.collisions.SpriteCollision;
import models.sprites.Sprite;

import java.util.*;

import static java.util.Collections.swap;
import static java.util.Objects.*;
import static java.util.Optional.ofNullable;
import static utils.StreamUtil.generate;

/**
 * @author - wally55077@gmail.com
 */
public class World {

    public final static int NUMBER_OF_SPRITES = 30;
    private List<Sprite> sprites;
    private SpriteCollision<? extends Sprite, ? extends Sprite> collision;

    public World(Collection<Sprite> sprites, SpriteCollision<? extends Sprite, ? extends Sprite> spriteCollision) {
        setSprites(sprites);
        setCollision(spriteCollision);
    }

    public void removeSprite(Sprite sprite) {
        sprites.set(sprites.indexOf(sprite), new Sprite());
    }

    public void moveSprite(int x1, int x2) {
        int numberOfSprites = sprites.size();
        checkIndex(x1, numberOfSprites);
        checkIndex(x2, numberOfSprites);
        Sprite collideSprite = sprites.get(x1);
        Sprite beCollidedSprite = sprites.get(x2);
        mayHaveCollision()
                .ifPresent(collision -> collision.collide(collideSprite, beCollidedSprite));
        swap(sprites, x1, x2);
    }

    private Optional<SpriteCollision<? extends Sprite, ? extends Sprite>> mayHaveCollision() {
        return ofNullable(collision);
    }

    public void setSprites(Collection<Sprite> sprites) {
        this.sprites = new ArrayList<>(requireNonNullElseGet(sprites, Collections::emptyList));
        this.sprites.forEach(sprite -> sprite.setWorld(this));
    }

    public void setCollision(SpriteCollision<? extends Sprite, ? extends Sprite> collision) {
        this.collision = requireNonNull(collision);
    }

    @Override
    public String toString() {
        return "[系統]顯示地圖\n" +
                generate(NUMBER_OF_SPRITES, value -> value < 11 ? "0" + (value - 1) : String.valueOf(value - 1), " ")
                + "\n" + generate(sprites, "  ");
    }
}
