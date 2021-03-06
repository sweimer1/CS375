package program1;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class DataSetUp {
	private ArrayList<ArrayList<Card>> problem_sets = new ArrayList<ArrayList<Card>>();
	private ArrayList<Integer> availableMoney_set = new ArrayList<Integer>();
	private ArrayList<Integer> numGertsCards_set = new ArrayList<Integer>();
	private ArrayList<Integer> numMarketPricedCards_set = new ArrayList<Integer>();
	
	public boolean isNum(String str) {
	    if (str == null) {
	        return false;
	    }
	    try {
	        Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	        return false;
	    }
	    return true;
	}
	
	public int numProblems(String file_name) {
		int problems = 0;
		try {
			File plf = new File(file_name);//setting up cards Gertrude has
			Scanner plfS = new Scanner(plf);
			while (plfS.hasNextLine()) {
				String[] name_and_price = plfS.nextLine().split(" ");
				if (isNum(name_and_price[0])) {
					problems++;
				}
			}
			plfS.close();
			return problems;
		} catch (FileNotFoundException e) {
			System.out.println("price list file not found");
			System.out.println(e.getClass());
		}
		return problems;
	}
	
	public void setUpCardAvailability(String plf_name, String mpf_name) {
		try {
			File plf = new File(plf_name);//setting up money and cards Gertrude has for each problem set
			Scanner plfS = new Scanner(plf);
			int problems = numProblems(plf_name);
			for (int i = 0; i < problems; i++) {
					ArrayList<Card> available_cards = new ArrayList<Card>();
					Integer numGertsCards = 0;
					Integer availableMoney = 0;
					int runs = 1;//run once to get the number of cards in the problem
					while (plfS.hasNextLine() && (runs >= 0)) {
						String[] name_and_price = plfS.nextLine().split(" ");
						if (isNum(name_and_price[0])) {
							numGertsCards = Integer.parseInt(name_and_price[0]);
							runs = numGertsCards;//run the number of times there are cards in the problem
							availableMoney = Integer.parseInt(name_and_price[1]);
						}
						else {
							Card newCard = new Card(name_and_price[0], Integer.parseInt(name_and_price[1]));
							available_cards.add(newCard);
						}
						runs--;//after each run, decrease amount of runs left
					}
					problem_sets.add(available_cards);//add data to ArrayList for each problem
					numGertsCards_set.add(numGertsCards);
					availableMoney_set.add(availableMoney);
			}
			plfS.close();
			File mpf = new File(mpf_name);//getting market prices for each card
			Scanner mpfS = new Scanner(mpf);
			while (mpfS.hasNextLine()) {
				String[] name_and_price = mpfS.nextLine().split(" ");
				if (isNum(name_and_price[0])) {
					numMarketPricedCards_set.add(Integer.parseInt(name_and_price[0]));
				}
				else {
					for (int i = 0; i < problem_sets.size(); i ++) {
						for (int j = 0; j < problem_sets.get(i).size(); j++) {
							if (problem_sets.get(i).get(j).getName().equals(name_and_price[0])) {
								problem_sets.get(i).get(j).setMarketPrice(Integer.parseInt(name_and_price[1]));
							}
						}
					}
				}
			}
			for (int i = 0; i < problem_sets.size(); i++) {
				for (int j = 0; j < problem_sets.get(i).size(); j++) {
					if (problem_sets.get(i).get(j).getMarketPrice() == 0) {
						mpfS.close();
						throw new IllegalArgumentException("\nWARNING: Market Price File does not contain all of Gertrude's cards");
					}
				}
			}
			//making equal number of market priced cards and problem sets
			if ((numMarketPricedCards_set.size() < problem_sets.size()) && (numMarketPricedCards_set.size() > 0)) {
				for (int i = numMarketPricedCards_set.size(); i < problem_sets.size(); i++) {
					numMarketPricedCards_set.add(numMarketPricedCards_set.get(i-1));
				}
			}
			mpfS.close();
		} catch (FileNotFoundException e) {
			System.out.println("market price file not found");
			System.out.println(e.getClass());
		}
	}
	
	public ArrayList<ArrayList<Card>> getProblem_sets() {
		return problem_sets;
	}

	public ArrayList<Integer> getAvailableMoney_set() {
		return availableMoney_set;
	}

	public ArrayList<Integer> getNumGertsCards_set() {
		return numGertsCards_set;
	}

	public ArrayList<Integer> getNumMarketPricedCards_set() {
		return numMarketPricedCards_set;
	}

	public void printAll() {
		for (int i = 0; i < problem_sets.size(); i ++) {
			System.out.println("Number of market price cards: " + numMarketPricedCards_set.get(i));
			System.out.println("Number of cards Gertrude has: " + numGertsCards_set.get(i));
			System.out.println("Available money: " + availableMoney_set.get(i));
			for (int j = 0; j < problem_sets.get(i).size(); j++) {
				System.out.print("Name: " + problem_sets.get(i).get(j).getName() + ", ");
				System.out.print("Gerts price: " + problem_sets.get(i).get(j).getGertsPrice() + ", ");
				System.out.println("Market price: " + problem_sets.get(i).get(j).getMarketPrice());
			}
			System.out.println();
		}
	}
}
