package models.sprites;

import models.World;

import java.util.Optional;

import static java.util.Objects.requireNonNullElse;
import static java.util.Optional.ofNullable;

/**
 * @author - wally55077@gmail.com
 */
public class Sprite {

    private World world;
    private String symbol;

    public Sprite() {
        this("-");
    }

    public Sprite(String symbol) {
        setSymbol(symbol);
    }

    protected void setSymbol(String symbol) {
        this.symbol = requireNonNullElse(symbol, "-");
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void removeSelf() {
        mayHaveWorld().ifPresent(world -> world.removeSprite(this));
    }

    protected Optional<World> mayHaveWorld() {
        return ofNullable(world);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
