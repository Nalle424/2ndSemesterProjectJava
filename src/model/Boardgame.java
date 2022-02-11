package model;

public class Boardgame {
	
	private int gameID;
	private String name;
	private int amount;
	private String description;
	private String genre;
	private String timeOfPlay;
	private String noOfPlayers;

	/**
	 * the empty constructor is used so the program can start a Boardgame object that can then be filled 
	 */
	public Boardgame() {
		
	}

	/**
	 * this part of the constructor is used for editing of the data 
	 * @param name
	 * @param amount
	 * @param description
	 * @param genre
	 * @param timeOfPlay
	 * @param noOfPlayers
	 */
	public Boardgame(String name, int amount, String description, String genre, String timeOfPlay, String noOfPlayers) {
		this.setName(name);
		this.setAmount(amount);
		this.setDescription(description);
		this.setGenre(genre);
		this.setTimeOfPlay(timeOfPlay);
		this.setNoOfPlayers(noOfPlayers);
	}
	public int getGameID() {
		return gameID;
	}
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTimeOfPlay() {
		return timeOfPlay;
	}

	public void setTimeOfPlay(String timeOfPlay) {
		this.timeOfPlay = timeOfPlay;
	}

	public String getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(String noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}
	
	
}
