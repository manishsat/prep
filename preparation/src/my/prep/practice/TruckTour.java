package my.prep.practice;
/*
 * Suppose there is a circle. There are  petrol pumps on that circle. Petrol pumps are numbered  to  (both inclusive). 
 * You have two pieces of information corresponding to each of the petrol pump: (1) the amount of petrol that particular petrol pump will give, and (2) the distance 
 * from that petrol pump to the next petrol pump.Initially, you have a tank of infinite capacity carrying no petrol. You can start the tour at any of the petrol pumps. Calculate the first point from where the truck will be able to complete the circle. Consider that the truck will stop at each of the petrol pumps. The truck will move one kilometer for each litre of the petrol.
 */
public class TruckTour {
	public static void main(String[] args) {
		PetrolPump[] pumps = new PetrolPump[3];
		pumps[0] = new PetrolPump(1,1, 5);
		pumps[1] = new PetrolPump(2,10, 3);
		pumps[2] = new PetrolPump(3,3, 4);

		printAns(getStartingPetrolPump(pumps));
		
		printAns(getStartingPetrolPump(pumps));
	}
	
	private static void printAns(PetrolPump startingPump) {
		if(startingPump!=null)
			System.out.println("Starting petrol pump "+ startingPump.getPumpNumber());
		else
			System.out.println("Starting petrol pump -1");
	}
	//O(N^2)
	private static PetrolPump getStartingPetrolPump(PetrolPump[] pumps) {
		//the logic is start with every pump and if you reach to the last pump then that pump could be the starting point.
		PetrolPump startPump = null;
		for(int i =0;i<pumps.length;i++) {
			startPump = pumps[i];
			int netDistance = startPump.getPetrol() - startPump.getDistanceToNextPump();
			
			for(int j=i+1;j%pumps.length!=i && netDistance >=0 ;j++) {
				netDistance += pumps[j%pumps.length].getPetrol() - pumps[j%pumps.length].getDistanceToNextPump();
			}	
			
			if(netDistance>=0) {
				//we found the starting pump and we reached till the end 
				return startPump;
			}
			
		}
		return null;
	}
	//O(N)
	private static PetrolPump getStartingPetrolPumpImproved(PetrolPump[] pumps) {
		int sum = 0 ;
		PetrolPump startingPump = pumps[0];
		for(int i=0;i<pumps.length ;i++) {
			if(startingPump == null) {
				startingPump = pumps[i];
			}
			sum+= (pumps[i].getPetrol() - pumps[i].getDistanceToNextPump());
			if(sum < 0) {
				startingPump = null;
				sum = 0;
			}
		}
		return (startingPump !=null && sum > 0) ? startingPump: null; 
	}
	
	
}

class PetrolPump {
	private int pumpNumber;
	private int petrol;
	private int distance;
	
	public PetrolPump(int pumpNumber,int petrol,int distance) {
		this.pumpNumber =pumpNumber;
		this.petrol = petrol;
		this.distance = distance;
		
	}
	
	public int getPetrol() {
		return this.petrol;
	}
	
	public int getDistanceToNextPump() {
		return this.distance;
	}
	
	public int getPumpNumber() {
		return this.pumpNumber;
	}
}