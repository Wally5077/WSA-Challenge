import models.*;

/**
 * @author - wally55077@gmail.com
 */
public class Youtube {

    public static void main(String[] args) {
        Channel pewDiePie = new Channel("PewDiePie");
        Channel wsa = new Channel("水球軟體學院");
        User waterBall = new WaterBall("水球");
        User fireBall = new FireBall("火球");
        waterBall.subscribe(wsa, pewDiePie);
        fireBall.subscribe(wsa, pewDiePie);
        wsa.upload(new Video("C1M1S2", "這個世界正是物件導向的呢！").withMinutes(4));
        pewDiePie.upload(new Video("Hello guys", "Clickbait").witSeconds(30));
        wsa.upload(new Video("C1M1S3", "物件 vs. 類別").withMinutes(1));
        pewDiePie.upload(new Video("Minecraft", "Let’s play Minecraft").withMinutes(30));
    }
}
