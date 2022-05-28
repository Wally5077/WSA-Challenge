package models.sprites;

import static java.lang.Math.abs;

/**
 * @author - wally55077@gmail.com
 */
public class Hero extends Sprite {

    private static final String HERO_SYMBOL = "H";
    private int hp = 30;

    public Hero() {
        super(HERO_SYMBOL);
    }

    public void gainHp(int hp) {
        this.hp += abs(hp);
    }

    public void loseHp(int hp) {
        this.hp -= abs(hp);
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void removeSelfIfDead() {
        if (isDead()) {
            removeSelf();
        }
    }

    public int getHp() {
        return hp;
    }
}
