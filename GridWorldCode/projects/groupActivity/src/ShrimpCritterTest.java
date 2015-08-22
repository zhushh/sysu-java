import static org.junit.Assert.*;
import org.junit.Test;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import org.junit.BeforeClass;
import org.junit.Test;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;

import java.awt.Color;

public class ShrimpCritterTest {
    private static final double DARKENING_FACTOR = 0.05;
    public static ShrimpCritter shrimpCritter = new ShrimpCritter();
    public static ActorWorld world = new ActorWorld();

    @BeforeClass
    public static void init() {
        world.add(new Location(0, 0), shrimpCritter);
        world.add(new Location(4, 4), new Rock(Color.ORANGE));
        world.add(new Location(1, 6), new Flower(Color.RED));
    }

    @Test
    public void testcase1() {
        shrimpCritter.moveTo(new Location(0, 0));
        shrimpCritter.setDirection(0);

        int red = shrimpCritter.getColor().getRed();
        int green = shrimpCritter.getColor().getGreen();
        int blue = shrimpCritter.getColor().getBlue();

        shrimpCritter.act();
        assertEquals((int)(red * (1 - DARKENING_FACTOR)), shrimpCritter.getColor().getRed());
        assertEquals((int)(green * (1 - DARKENING_FACTOR)), shrimpCritter.getColor().getGreen());
        assertEquals((int)(blue * (1 - DARKENING_FACTOR)), shrimpCritter.getColor().getBlue());

        int direction = shrimpCritter.getDirection();
        Location loc = shrimpCritter.getLocation();

        if (direction == Location.EAST) {
            assertEquals(new Location(0, 1), shrimpCritter.getLocation());
        } else if (direction == Location.SOUTHEAST) {
            assertEquals(new Location(1, 1), shrimpCritter.getLocation());
        } else if (direction == Location.SOUTH) {
            assertEquals(new Location(1, 0), shrimpCritter.getLocation());
        } else {
            // wrong result, throw an error
            assertEquals(-1, direction);
        }
    }

   @Test
   public void testcase2() {
        shrimpCritter.moveTo(new Location(5, 4));
        shrimpCritter.setDirection(0);

        int red = shrimpCritter.getColor().getRed();
        int green = shrimpCritter.getColor().getGreen();
        int blue = shrimpCritter.getColor().getBlue();

        shrimpCritter.act();

        Color color = shrimpCritter.getGrid().get(new Location(4, 4)).getColor();
        assertEquals(red, color.getRed());
        assertEquals(green, color.getGreen());
        assertEquals(blue, color.getBlue());

        int direction = shrimpCritter.getDirection();
        Location loc = shrimpCritter.getLocation();

        if (direction == Location.NORTH) {
            assertEquals(new Location(4, 4), loc);
        } else if (direction == Location.NORTHEAST) {
            assertEquals(new Location(4, 5), loc);
        } else if  (direction == Location.EAST) {
            assertEquals(new Location(5, 5), loc);
        } else if (direction == Location.SOUTHEAST) {
            assertEquals(new Location(6, 5), loc);
        } else if (direction == Location.SOUTH) {
            assertEquals(new Location(6, 4), loc);
        } else if (direction == Location.SOUTHWEST) {
            assertEquals(new Location(6, 3), loc);
        } else if (direction == Location.WEST) {
            assertEquals(new Location(5, 3), loc);
        } else if (direction == Location.NORTHWEST) {
            assertEquals(new Location(4, 3), loc);
        } else {
            // wrong result, throw an error
            assertEquals(-1, direction);
        }
   }

   @Test
   public void testcase3() {
        shrimpCritter.moveTo(new Location(2, 6));
        shrimpCritter.setDirection(0);

        Actor flower = shrimpCritter.getGrid().get(new Location(1, 6));
        int red = shrimpCritter.getColor().getRed();
        int green = shrimpCritter.getColor().getGreen();
        int blue = shrimpCritter.getColor().getBlue();

        shrimpCritter.act();

        Color color = flower.getColor();
        assertEquals(red, color.getRed());
        assertEquals(green, color.getGreen());
        assertEquals(blue, color.getBlue());

        int direction = shrimpCritter.getDirection();
        Location loc = shrimpCritter.getLocation();

        if (direction == Location.NORTH) {
            assertEquals(new Location(0, 6), loc);
        } else if (direction == Location.NORTHEAST) {
            assertEquals(new Location(0, 7), loc);
        } else if  (direction == Location.EAST) {
            assertEquals(new Location(1, 7), loc);
        } else if (direction == Location.SOUTHEAST) {
            assertEquals(new Location(2, 7), loc);
        } else if (direction == Location.SOUTH) {
            assertEquals(new Location(2, 6), loc);
        } else if (direction == Location.SOUTHWEST) {
            assertEquals(new Location(2, 5), loc);
        } else if (direction == Location.WEST) {
            assertEquals(new Location(1, 5), loc);
        } else if (direction == Location.NORTHWEST) {
            assertEquals(new Location(0, 5), loc);
        } else {
            // wrong result, throw an error
            assertEquals(-1, direction);
        }

        assertEquals(new Location(2, 6), flower.getLocation());
   }
}

