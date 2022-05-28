package models.collisions;

import models.sprites.Hero;
import models.sprites.Sprite;
import models.sprites.Water;

/**
 * @author - wally55077@gmail.com
 */
public class HeroWaterCollision extends SpriteCollision<Hero, Water> {

    private static final int HERO_GAINED_HP = 10;

    public HeroWaterCollision() {
        super();
    }

    public HeroWaterCollision(SpriteCollision<? extends Sprite, ? extends Sprite> spriteCollision) {
        super(spriteCollision);
    }

    @Override
    protected Class<Hero> getCollideSpriteClass() {
        return Hero.class;
    }

    @Override
    protected Class<Water> getBeCollidedSpriteClass() {
        return Water.class;
    }

    @Override
    protected void doCollision(Hero hero, Water water) {
        hero.gainHp(HERO_GAINED_HP);
        water.removeSelf();
        System.out.printf("Hero hp: %d\n", hero.getHp());
    }
}
