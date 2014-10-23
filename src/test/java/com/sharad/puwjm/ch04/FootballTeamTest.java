package com.sharad.puwjm.ch04;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class FootballTeamTest {

	public Object[] noOfGamesWon() {
		return $(0, 1, 3, 4, 5, 6);
	}

	public Object[] illegalNoOfGamesWon() {
		return $(-10, -11, -13, -4, -5, -6);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	@Parameters(method = "noOfGamesWon")
	public void constructorShouldSetGamesWon(int noOfGamesWon) {
		FootballTeam team = new FootballTeam(noOfGamesWon);
		assertEquals(
				noOfGamesWon + " games passed to constructor, but "
						+ team.getGamesWon() + " were returned", noOfGamesWon,
				team.getGamesWon());
	}

	@Test
	@Parameters(method = "illegalNoOfGamesWon")
	public void constructorShouldThrowIllegalArgumentException(
			int illegalNoOfGamesWon) {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The number of games won can not be negative");
		new FootballTeam(illegalNoOfGamesWon);
	}

	@Test
	public void shouldBeAbleToCompareTeams() {
		int ANY_POSITIVE_NUMBER = 10;
		FootballTeam team = new FootballTeam(10);
		assertTrue("Football team must implement Comparable interface",
				team instanceof Comparable<?>);
	}

	@Test
	public void teamsWithMoreMatchesWonShouldBeGreater() {
		FootballTeam teamA = new FootballTeam(10);
		FootballTeam teamB = new FootballTeam(5);
		assertTrue(
				"teamB with " + teamB.getGamesWon()
						+ " games won should be ranked after the teamA with "
						+ teamA.getGamesWon() + " games won",
				teamA.compareTo(teamB) >=1);
	}

	@Test
	public void teamsWithLesseMatchesWonShouldBeLesser() {
		FootballTeam teamA = new FootballTeam(5);
		FootballTeam teamB = new FootballTeam(10);
		assertTrue(
				"teamA with " + teamA.getGamesWon()
						+ " games won should be ranked after the teamB with "
						+ teamB.getGamesWon() + " games won",
				teamA.compareTo(teamB) < 0);
	}

	@Test
	public void teamsWithSameMatchesWonShouldBeEqual() {
		FootballTeam teamA = new FootballTeam(10);
		FootballTeam teamB = new FootballTeam(10);
		assertTrue(
				"Both teams have won the same number of games: "
						+ teamA.getGamesWon() + " vs. " + teamB.getGamesWon()
						+ " and should be ranked equal",
				teamA.compareTo(teamB) == 0);
	}
}
