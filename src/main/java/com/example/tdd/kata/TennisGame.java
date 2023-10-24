package com.example.tdd.kata;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

public class TennisGame {

	public static final Map<Integer, String> SCORE_TABLE = Map.ofEntries(
			new SimpleImmutableEntry<>(0, "Love"),
			new SimpleImmutableEntry<>(1, "15"),
			new SimpleImmutableEntry<>(2, "30"),
			new SimpleImmutableEntry<>(3, "40")
	);
	private int aTeamPoint;
	private int bTeamPoint;

	public String getScore() {
		return minScoreBeforeForty() ? handleNormalGame() : handleDeuceAndAfter();
	}

	private String handleNormalGame() {
		if (normalGameWin()) {
			return aTeamPoint > bTeamPoint ? "Team A Win" : "Team B Win";
		} else if (samePointState()) {
			return transferToScore(aTeamPoint) + " All";
		} else {
			return transferToScore(aTeamPoint) + " - " + transferToScore(bTeamPoint);
		}
	}

	private boolean normalGameWin() {
		return Math.max(aTeamPoint, bTeamPoint) > 3;
	}

	private String handleDeuceAndAfter() {
		if (samePointState()) {
			return "Deuce";
		}

		if (winAfterAdState()) {
			return aTeamPoint > bTeamPoint ? "Team A Win" : "Team B Win";
		} else {
			return aTeamPoint > bTeamPoint ? "AD - __" : "__ - AD";
		}

	}

	private boolean winAfterAdState() {
		return Math.abs(aTeamPoint - bTeamPoint) > 1;
	}

	private boolean minScoreBeforeForty() {
		return Math.min(aTeamPoint, bTeamPoint) < 3;
	}

	private boolean samePointState() {
		return aTeamPoint == bTeamPoint;
	}

	private String transferToScore(int point) {
		return SCORE_TABLE.get(point);
	}

	public void aTeamGetPoint() {
		operationAfterGameOver();
		aTeamPoint++;
	}

	public void bTeamGetPoint() {
		operationAfterGameOver();
		bTeamPoint++;
	}

	private void operationAfterGameOver() {
		if (minScoreBeforeForty() && normalGameWin()) {
			throw new RuntimeException("Game is already over");
		} else if (!minScoreBeforeForty() && winAfterAdState()) {
			throw new RuntimeException("Game is already over");
		}
	}
}
