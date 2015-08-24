package info.gridworld.maze;

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
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
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

		int row = loc.getRow();
		int col = loc.getCol();
		int[][] to = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		for (int i = 0; i < 4; i++) {
			Location tloc = new Location(row+to[i][0], col+to[i][1]);
			if (!gr.isValid(tloc))
			{
				continue;
			}
			else if (gr.get(tloc) == null)
			{
				valid.add(tloc);
			}
			// check that is end
			if ((gr.get(tloc) instanceof Rock) && gr.get(tloc).getColor().getRed() == Color.RED.getRed())
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
			} else if (!crossLocation.empty()) {
				return true;
			} else {
				return false;
			}
		}
		return false;
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

		last = getLocation();		// record last 

		ArrayList<Location> valid = getValid(getLocation());
		if (valid != null && valid.size() > 0) {	// go ahead
			next = valid.get((int)(Math.random() * valid.size()));
			valid.add(getLocation());
			crossLocation.push(valid);
		} else if (!crossLocation.empty()) {	// go back
			next = crossLocation.peek().get(crossLocation.peek().size() - 1);
			crossLocation.pop();
		}

		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, last);		// leave flower
	}
}
