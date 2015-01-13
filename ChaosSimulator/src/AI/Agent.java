package AI;
import java.awt.Color;
import java.util.ArrayList;

import PathPlanners.PathFinder;
import WorldClasses.CityObject;
import WorldClasses.Road;
import Behaviours.MovementBehavior;

public abstract class Agent extends CityObject {
	protected MovementBehavior movementBehavior;
	protected PathFinder pathFinder;
	protected CityObject target;
	protected CityObject location;
	protected ArrayList<CityObject> path;
	private int positionIndex = 1;
	
	protected int nrTraversedNodes = 0;
	private Color color;
	protected boolean isOnline = true;
	
	// abstract methods
	public abstract void think();

	
	public Agent(Color color){
		this.color = color;
	}
	


//	public Agent(MovementBehavior movementBehavior, CityObject location) {
//		this.movementBehavior = movementBehavior;
//		this.location = location;
//	}

	public void moveTo(CityObject nextLocation) {
		this.location = nextLocation;
	}
	
	public void move(){
		CityObject nextLocation = path.get(positionIndex);
		
		/* check if the road that we want to move to is blocked (or occupied?) */ 
		if(nextLocation.isBlocked() && nextLocation instanceof Road){
			System.out.println("the road was blocked");
			
			getPathFinder().handleBlockade(nextLocation, getLocation());
			setPath(getPathFinder().getPath());
		}
		else{
			this.location = path.get(positionIndex);
			moveTo(path.get(positionIndex));
			positionIndex++;
			nrTraversedNodes++;
		}
	}
	
	//TODO add all errorcorrection to Agent instead of having it in FireFighter
	public void reportBadPath(){
		setTarget(null);
		setPath(null);
	}
	public boolean atEndOfPath(){
//		System.out.println("atEndOfPath "+ positionIndex +"/"+path.size());
		if(positionIndex == path.size())
			return true;
		else return false;
	}
	

	// getters & setters
	public MovementBehavior getMovementBehavior() {
		return movementBehavior;
	}

	public void setMovementBehavior(MovementBehavior movementBehavior) {
		this.movementBehavior = movementBehavior;
	}

	public CityObject getTarget() {
		return target;
	}

	public void setTarget(CityObject target) {
		this.target = target;
	}

	public CityObject getLocation() {
		return location;
	}

	public void setLocation(CityObject location) {
		this.location = location;
	}

	public PathFinder getPathFinder() {
		return pathFinder;
	}

	public void setPathFinder(PathFinder pathFinder) {
		this.pathFinder = pathFinder;
	}

	public ArrayList<CityObject> getPath() {
		return path;
	}

	public void setPath(ArrayList<CityObject> path) {
		this.path = path;
		positionIndex = 1;
	}

	public int getNrTraversedNodes() {
		return nrTraversedNodes;
	}


	public boolean isOnline() {
		// TODO Auto-generated method stub
		return isOnline;
	}
}
