package com.sharad.puwjm.ch04;

public class FootballTeam implements Comparable<FootballTeam> {

	private int gamesWon;

	public FootballTeam(int i) {
		if (i < 0) {
			throw new IllegalArgumentException(
					"The number of games won can not be negative");
		}
		this.gamesWon = i;
	}

	public int getGamesWon() {
		return this.gamesWon;
	}

	@Override
	public int compareTo(FootballTeam otherTeam) {
		if (gamesWon > otherTeam.getGamesWon()) {
			return 1;
		} else if (gamesWon < otherTeam.getGamesWon()) {
			return -1;
		}
		return 0;
	}

}
