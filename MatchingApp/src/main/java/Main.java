import models.Coord;
import models.Gender;
import models.Individual;
import models.MatchmakingSystem;
import models.strategies.MatchmakingStrategy;
import utils.StreamUtil;

import java.util.List;
import java.util.Random;

import static java.util.stream.IntStream.rangeClosed;
import static utils.StreamUtil.filterToList;
import static utils.StreamUtil.generate;

/**
 * @author - wally55077@gmail.com
 */
public class Main {
    private final static Random RANDOM = new Random();
    private final static int AGE = 18;
    private final static String[] HABITS = {"打籃球", "烹飪", "玩遊戲", "唱歌", "健身", "看書", "跳舞", "寫作"};
    private final static int COORD_RADIUS = 5;
    private final static List<Integer> COORD_RANGE = rangeClosed(-1 * COORD_RADIUS, COORD_RADIUS).boxed().toList();
    private final static MatchmakingStrategy[] MATCHMAKING_STRATEGIES = {
            MatchmakingStrategy.matching(Individual::getCoord)
                    .thenMatching(Individual::getId),
            MatchmakingStrategy.matching(Individual::getHabits, StreamUtil::intersectionCount)
                    .thenMatching(Individual::getId)
    };

    public static void main(String[] args) {
        List<Individual> individuals = generate(4, Main::createIndividual);
        MatchmakingSystem matchmakingSystem = new MatchmakingSystem(individuals);
        matchmakingSystem.launch();
        matchmakingSystem.showAllMatch();
    }

    public static Individual createIndividual(int id) {
        Gender[] genders = Gender.values();
        Gender gender = genders[RANDOM.nextInt(genders.length)];
        int age = RANDOM.nextInt(AGE, 40) + 1;
        String habits = String.join(",", filterToList(HABITS, habit -> RANDOM.nextBoolean()));
        Coord coord = new Coord(COORD_RANGE.get(RANDOM.nextInt(COORD_RANGE.size())), COORD_RANGE.get(RANDOM.nextInt(COORD_RANGE.size())));
        MatchmakingStrategy matchmakingStrategy = MATCHMAKING_STRATEGIES[RANDOM.nextInt(MATCHMAKING_STRATEGIES.length)];
        return new Individual(id, gender, age, habits, coord, matchmakingStrategy);
    }
}