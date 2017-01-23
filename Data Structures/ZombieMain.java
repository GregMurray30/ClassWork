import java.util.*;
class ZombieMain {
class Zombie {
	
	public double hunger;
	public boolean madeKill = false;
	
	public boolean makesKill(double numHumans, double numZombies, double rand) {
		double killFactor = (numHumans)/4*(numHumans+numZombies);
		
		if ( killFactor >= rand) {
			madeKill = true;
		}
		return madeKill;
	}
	
	public void updateHunger() {
		if (madeKill) {
			hunger = 0.0;
		}
		else {
			hunger ++;
		}
	}
	
	public void reanimate(ArrayList zombieList, ArrayList humanList, boolean madeKill) {
		if (madeKill) {
			zombieList.add(new Zombie());
		}
	}
	
	
	Zombie() {
		hunger = 0.0;
		madeKill = false;
	}
}
import java.util.*;

class Human {
	
	public double age; 
	
	public void updateAge() {
		age ++;
	}
	
	public void breed(ArrayList aList, boolean bool) {
		if (bool) {
			aList.add(new Human());
		}
	}
	
	public boolean hasChild(double rand) {
		if (rand <= 0.05) {
			return true;
		}
		else {
			return false;
		}
	}
	
    Human() {
		age = 23.0;
	}

}

import java.util.*;
class Simulation {
	
	//Random number generator method
	static double randomGenerator() {
		Random rnd = new Random();
		rnd.setSeed(1234);
		double rand = rnd.nextDouble();
		return rand;
	}
	//Declare the arrayLists for zombies and humans.
	static ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	static ArrayList<Human> humanList = new ArrayList<Human>();
	
	//Initialize the temporary arrays iteratively and then add each  
	//element of that array into the arrayLists
	static void initHumanLists () {
		for (int i = 0; i < 100; i ++) {
			humanList.add(new Human());
		}
		System.out.println(humanList.size());
	}
	static void initZombieLists() {
		for (int a = 0; a < 20; a ++) {
			zombieList.add(new Zombie());
		}
		System.out.println(zombieList.size());
	}
	
	//Spring method to simulate the human breeding season
	static void Spring() {
		for (int i = 0; i < humanList.size(); i ++) {
			if (humanList.get(i).age >= 14) {
				double fertility = randomGenerator();
				humanList.get(i).breed(humanList, humanList.get(i).hasChild(fertility));
			}
		}
			
	}
	
	static void Summer() {
		int counter = 0;
		for (Iterator<Zombie> it = zombieList.iterator(); it.hasNext();) {
			double rand = randomGenerator();
			boolean madeKill = it.next().makesKill(humanList.size(), zombieList.size(), rand);
			if (madeKill){
				counter ++;
			}
			
		}
		for (int i = 0; i < counter; i++) {
			zombieList.add(new Zombie());
			humanList.remove(0);
		}
	}
	
	static void Fall() {
		for (int i = 0; i < humanList.size(); i ++) {
			humanList.get(i).updateAge();
			
		}
		for (int j = 0; j < zombieList.size(); j ++) {
			zombieList.get(j).updateHunger();
			
		}
	}
	
	static void Winter() {
		for (Iterator<Zombie> it = zombieList.iterator(); it.hasNext();) {
			if (it.next().hunger>= 3.0) {
				it.remove();
			}
		}
		
		
	}
	static void printOutput() {
		double avgAge = 0;
		double avgHunger = 0;
		for (int i = 0; i < humanList.size(); i ++) {
			avgAge = avgAge + humanList.get(i).age;
		}
		avgAge = avgAge/humanList.size();
		
		for (int i = 0; i < zombieList.size(); i ++) {
			avgHunger = avgHunger + zombieList.get(i).hunger;
		}
		avgHunger = avgHunger/zombieList.size();
		
		System.out.printf("There are " + humanList.size() + " humans (avg age %.2f) and " + zombieList.size() + " zombies (avg hunger %.2f)\n", avgAge, avgHunger);
		
	}
	public static void main(String[] args) {
		initHumanLists();
		initZombieLists();
		System.out.println(randomGenerator());
		for (int i = 1; i <= 10; i ++) {
			printOutput();
			Spring();
			Summer();
			Fall();
			Winter();
			
		
		}
	}
		
	}
}
