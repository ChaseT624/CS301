import java.util.ArrayList;
import java.util.Random;

public class BinPacking {
	
	public static final int TEST_SIZE = 100;
	public static final int ARRAY_SIZE = 1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();	
		int firstFitTot = 0;
		int bestFitTot = 0;
		for (int i = 0; i < TEST_SIZE; i++) {
			double[] test = new double[ARRAY_SIZE];
			for(int j = 0; j < ARRAY_SIZE; j++) {
				double num = random.nextInt(100) / 100.0;
				test[j] = num;
			}
			firstFitTot += packBinsFirstFit(test);
			bestFitTot += packBinsBestFit(test);
		}
		double firstAvg = (double) firstFitTot/TEST_SIZE;
		double bestAvg = (double)  bestFitTot/TEST_SIZE;
		System.out.println("The average amount of bins used by FirstFit was: " + firstAvg);
		System.out.println("The average amount of bins used by BestFit was: " + bestAvg);
		if (firstAvg > bestAvg) {
			System.out.println("BestFit tends to use less bins");
		}
		else if (firstAvg == bestAvg){
			System.out.println("They averaged the same amount of bins"); 
		}
		else {
			System.out.println("FirstFit tends to use less bins");
		}
		/*double[] test = {.2, .8, .1, .9, .7, .4, .4,.2,.3};
		System.out.println(packBinsFirstFit(test));
		System.out.println(packBinsBestFit(test));*/
	}
	
	public static int packBinsFirstFit(double[] items) {
		//Create an ArrayList of bins and add the first bin
		ArrayList<ArrayList<Double>> bins = new ArrayList<>();
		ArrayList<Double> firstBin = new ArrayList<>();
		bins.add(firstBin);
		for (double num: items) {
			boolean foundBin = false;
			int index = 0;
			//until we find a location keep looking
			while(!foundBin) {
				if (index >= bins.size()) {
					//if we go over the amount of bins make a new bin
					ArrayList<Double> newBin = new ArrayList<>();
					bins.add(newBin);
					newBin.add(num);
					foundBin = true;
				}			
				else if (num + calcValue(bins.get(index)) > 1) {
				//if the total value in this bin + num is > 1 move to the next bin	
					index++;
				}
				else {
				//if the num fits add it to the bin
					bins.get(index).add(num);
					foundBin = true;
				}		
			}
		}
		//return the amount of bins 
		return bins.size();
	}
	
	public static int packBinsBestFit(double[] items) {
		//Create an ArrayList of bins and add the first bin
		ArrayList<ArrayList<Double>> bins = new ArrayList<>();
		ArrayList<Double> firstBin = new ArrayList<>();
		bins.add(firstBin);
		for (double num: items) {
			int bestIndex = -1;
			//continue until the index is >= size
			for(int index = 0; index < bins.size(); index++) {		
				//if the value could fit check to see if it is the bestIndex
				if (num + calcValue(bins.get(index)) <= 1) {	
					if (bestIndex == -1 || num + calcValue(bins.get(index)) > num +
							calcValue(bins.get(bestIndex)) ) {
						bestIndex = index;
					}
				}	
			}
			//if there was no bin that fit it make a new bin
			if (bestIndex == -1) {
				ArrayList<Double> newBin = new ArrayList<>();
				bins.add(newBin);
				newBin.add(num);
			}
			//add to the best location 
			else {
				bins.get(bestIndex).add(num);
			}
		}
		//return the amount of bins
		return bins.size();
		
	}
	
	public static double calcValue(ArrayList<Double> list) {
		double total = 0;
		//add all items in the list and return total
		for (Double num: list) {
			total += num;
		}
		return total;
	}

}
