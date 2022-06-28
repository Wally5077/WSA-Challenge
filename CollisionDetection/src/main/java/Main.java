import models.World;
import models.collisions.HeroFireSpriteCollision;
import models.collisions.HeroWaterCollision;
import models.collisions.WaterFireSpriteCollision;
import models.sprites.Fire;
import models.sprites.Hero;
import models.sprites.Sprite;
import models.sprites.Water;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.shuffle;
import static models.World.NUMBER_OF_SPRITES;
import static utils.StreamUtil.generate;

/**
 * @author - wally55077@gmail.com
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        var spriteCollision = new WaterFireSpriteCollision(new HeroFireSpriteCollision(new HeroWaterCollision()));
        var sprites = generateSprites();
        World world = new World(sprites, spriteCollision);
        boolean isContinueMoving = true;
        while (isContinueMoving) {
            System.out.println(world);
            System.out.println("[系統]開始移動目標");
            int x1 = in.nextInt();
            int x2 = in.nextInt();
            world.moveSprite(x1, x2);
            System.out.println("[系統]還要繼續移動嗎？（Y/N）");
            isContinueMoving = "Y".equalsIgnoreCase(in.next());
        }
    }

    private static Collection<Sprite> generateSprites() {
        List<Sprite> sprites = new ArrayList<>(NUMBER_OF_SPRITES);
        sprites.add(new Hero());
        sprites.addAll(generate(5, index -> new Water()));
        sprites.addAll(generate(5, index -> new Fire()));
        sprites.addAll(generate(NUMBER_OF_SPRITES - sprites.size(), index -> new Sprite()));
        shuffle(sprites);
        return sprites;
    }
}
