
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import info.gridworld.actor.Rock;
import java.awt.Color;

/**
 * This class runs a world that contains maze bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class SmartMazeBugRunner
{
    private SmartMazeBugRunner() {}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(); 
        SmartMazeBug mb = new SmartMazeBug();
        world.add(new Location(0,0), mb);
        world.add(new Location(1,1),new Rock());
        world.show();
    }
}