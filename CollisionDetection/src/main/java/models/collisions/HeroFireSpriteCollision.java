package models.collisions;

import models.sprites.Fire;
import models.sprites.Hero;
import models.sprites.Sprite;

/**
 * @author - wally55077@gmail.com
 */
public class HeroFireSpriteCollision extends SpriteCollision<Hero, Fire> {

    private static final int HERO_LOST_HP = 10;

    public HeroFireSpriteCollision() {
        super();
    }

    public HeroFireSpriteCollision(SpriteCollision<? extends Sprite, ? extends Sprite> spriteCollision) {
        super(spriteCollision);
    }

    @Override
    protected Class<Hero> getCollideSpriteClass() {
        return Hero.class;
    }

    @Override
    protected Class<Fire> getBeCollidedSpriteClass() {
        return Fire.class;
    }

    @Override
    protected void doCollision(Hero hero, Fire fire) {
        hero.loseHp(HERO_LOST_HP);
        hero.removeSelfIfDead();
        fire.removeSelf();
        System.out.printf("Hero hp: %d\n", hero.getHp());
    }
}
