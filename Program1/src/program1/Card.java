package program1;

public class Card {
	private String name;
	private int gertsPrice = 0;
	private int marketPrice = 0;
	private int consider = 0;
	
	public Card(String name, int gertsPrice, int marketPrice) {
		this.name = name;
		this.gertsPrice = gertsPrice;
		this.marketPrice = marketPrice;
	}
	
	public Card(String name, int gertsPrice) {
		this.name = name;
		this.gertsPrice = gertsPrice;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGertsPrice() {
		return gertsPrice;
	}
	
	public void setGertsPrice(int gertsPrice) {
		this.gertsPrice = gertsPrice;
	}
	
	public int getMarketPrice() {
		return marketPrice;
	}
	
	public void setMarketPrice(int marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public int getConsider() {
		return consider;
	}
	
	public void setConsider(int consider) {
		this.consider = consider;
	}
}
