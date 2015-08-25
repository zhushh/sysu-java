
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MyMazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class SmartMazeBug extends Bug {
    private Location next;
    private Location last;
    private boolean isEnd = false;
    private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private Integer stepCount = 0;
    private boolean hasShown = false;//final message has been shown

    public static final int TOWARDS = 4;
    private int[] towards = new int[TOWARDS];

    /**
     * Constructs a box bug that traces a square of a given side length
     * 
     * @param length
     *            the side length
     */
    public SmartMazeBug() {
        setColor(Color.GREEN);
        last = new Location(0, 0);
        // set init time
        for (int i = 0; i < TOWARDS; i++) {
            towards[i] = 1;
        }
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        boolean willMove = canMove();
        if (isEnd) {
        //to show step count when reach the goal        
            if (!hasShown) {
                String msg = stepCount.toString() + " steps";
                JOptionPane.showMessageDialog(null, msg);
                hasShown = true;
            }
        } else if (willMove) {
            // call selectNextToMove method to get the next
            selectNextToMove();
            move();
            //increase step count when move 
            stepCount++;
        }
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Location> valid = new ArrayList<Location>();

        int[] dirs = {
            Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST
        };
        for (int d : dirs) {
            Location tloc = loc.getAdjacentLocation(d);
            if (!gr.isValid(tloc))
            {
                continue;
            }
            else if (gr.get(tloc) == null)
            {
                valid.add(tloc);
            }
            // check that is end
            else if ((gr.get(tloc) instanceof Rock) && gr.get(tloc).getColor().getRed() == Color.RED.getRed())
            {
                isEnd = true;
            }
        }
        return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {
        if (!isEnd) {
            ArrayList<Location> valid = getValid(getLocation());
            if (valid != null && valid.size() > 0) {
                return true;
            } else if (crossLocation.empty()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // select a loc to move
    public Location selectRandomMove(ArrayList<Location> locs)
    {
        if (locs == null || locs.size() <= 0) {
            return null;
        } else {
            // return locs.get((int)(Math.random() * locs.size()));
            int sum = 0;
            boolean[] exist = new boolean[TOWARDS];
            for (int i = 0; i < TOWARDS; i++) {
                exist[i] = false;
            }

            for (Location loc : locs) {
                switch (getLocation().getDirectionToward(loc)) {
                    case Location.NORTH: 
                        sum += towards[0]; 
                        exist[0] = true; 
                        break;
                    case Location.EAST: 
                        sum += towards[1]; 
                        exist[1] = true; 
                        break;
                    case Location.SOUTH: 
                        sum += towards[2]; 
                        exist[2] = true; 
                        break;
                    case Location.WEST: 
                        sum += towards[3]; 
                        exist[3] = true; 
                        break;
                }
            }

            int r = (int)(Math.random() * sum);
            int cur = 0;
            for (int i = 0; i < TOWARDS; i++) {
                if (exist[i]) {
                    cur += towards[i];
                }
                if (exist[i] && cur >= r) {
                    return getLocation().getAdjacentLocation(i * 90);
                }
            }

            return locs.get(locs.size() - 1);
        }
    }

    // select next to move
    public void selectNextToMove()
    {
        ArrayList<Location> valid = getValid(getLocation());
        if (valid != null && valid.size() > 0) {    // go ahead
            next = selectRandomMove(valid);
            valid.add(getLocation());
            crossLocation.push(valid);

            switch (getLocation().getDirectionToward(next)) {
                case Location.NORTH: 
                    towards[0]++; 
                    break;
                case Location.EAST: 
                    towards[1]++; 
                    break;
                case Location.SOUTH: 
                    towards[2]++; 
                    break;
                case Location.WEST: 
                    towards[3]++; 
                    break;
            }

        } else if (!crossLocation.empty()) {    // go back
            next = crossLocation.peek().get(crossLocation.peek().size() - 1);
            crossLocation.pop();

            switch (getLocation().getDirectionToward(next)) {
                case Location.NORTH: 
                    towards[2]--; 
                    break;
                case Location.EAST: 
                    towards[3]--; 
                    break;
                case Location.SOUTH: 
                    towards[0]--; 
                    break;
                case Location.WEST: 
                    towards[1]--; 
                    break;
            }

        }
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }

        last = getLocation();       // record last 

        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, last);     // leave flower
    }
}
