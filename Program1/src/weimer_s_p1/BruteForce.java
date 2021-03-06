package program1;

import java.util.ArrayList;

public class BruteForce {
	public ArrayList<Integer> computeMaxProfit(ArrayList<Card> cards, Integer availableMoney){
		Integer maxProfit = 0;//will return an array of indexes of cards in "cards" array to be bought and max profit
		ArrayList<Card> S = new ArrayList<Card>();
		ArrayList<Integer> retArray = new ArrayList<Integer>();
		int sumAllCardPrices = 0;
		for (int i = 0; i < cards.size(); i++) {
			sumAllCardPrices += cards.get(i).getGertsPrice();
		}
		if (sumAllCardPrices <= availableMoney) {
			int sumMarketPrices = 0;
			for (int i = 0; i < cards.size(); i++) {
				retArray.add(i);
				sumMarketPrices += cards.get(i).getMarketPrice();
			}
			retArray.add(sumMarketPrices-sumAllCardPrices);
			return retArray;
		}	
		int size = cards.size(); 
        int i = 0;
        while (i < (1<<size)) {
        	S.clear();
        	int currentCost = 0;
    		int currentProfit = 0;
            for (int j = 0; j < size; j++) {//generate subsets
                if ((i & (1 << j)) > 0) {
                    S.add(cards.get(j));
                }
            }
            i++;
            for (int j = 0; j < S.size(); j++) {//calculate cost
            	currentCost += S.get(j).getGertsPrice();//calculate cost of subset
				currentProfit += S.get(j).getMarketPrice();//calculate profit of subset
			}
            if (currentCost <= availableMoney) {//update maxprofit and return array if necessary
    			if (currentProfit > maxProfit) {
    				maxProfit = currentProfit-currentCost;
    				retArray.clear();
    				for (int j = 0; j < cards.size(); j++) {
    					if(S.contains(cards.get(j))) {
    						retArray.add(j);
    					}
    				}
    			}
    		}
        }
		retArray.add(maxProfit);
		return retArray;
	}
	
	public void printMax(String price_list_file, String market_price_file) {
		DataSetUp d = new DataSetUp();
		d.setUpCardAvailability(price_list_file, market_price_file);
		for (int i = 0; i < d.getProblem_sets().size(); i++) {
			System.out.print(d.getNumGertsCards_set().get(i) + " ");//size of input
			long beginTime = System.nanoTime();
			ArrayList<Integer> solutionArray = computeMaxProfit(d.getProblem_sets().get(i), d.getAvailableMoney_set().get(i));
			long endTime = System.nanoTime();
			long timeElapsed = (endTime - beginTime);
			long nano = (long) Math.pow(10, -9);
			System.out.print(solutionArray.get((solutionArray.size()-1)) + " ");//profit
			System.out.print(solutionArray.size()-1 + " ");
			System.out.print(timeElapsed*nano);
			System.out.println();
			for (int j = 0; j < solutionArray.size()-1; j++) {
				System.out.println(d.getProblem_sets().get(i).get(solutionArray.get(j)).getName());
			}
		}
	}
}
