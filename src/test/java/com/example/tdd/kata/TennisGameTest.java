package com.example.tdd.kata;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TennisGameTest {

	@Test
	void init() {
		TennisGame tennisGame = new TennisGame();

		String score = tennisGame.getScore();

		Assertions.assertThat(score).isEqualTo("Love All");
	}

	@Test
	void thirty_love() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 2, 0);

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("30 - Love");
	}

	@Test
	void thirty_forty() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 2, 3);

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("30 - 40");
	}

	@Test
	void team_b_win() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 2, 3);
		tennisGame.bTeamGetPoint();

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("Team B Win");
	}

	@Test
	void team_a_win() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 2);
		tennisGame.aTeamGetPoint();

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("Team A Win");
	}

	@Test
	void deuce() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 3);

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("Deuce");
	}

	@Test
	void team_a_ad() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 3);
		tennisGame.aTeamGetPoint();

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("AD - __");
	}

	@Test
	void team_b_ad() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 3);
		tennisGame.aTeamGetPoint();
		tennisGame.bTeamGetPoint();
		tennisGame.bTeamGetPoint();

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("__ - AD");
	}

	@Test
	void team_a_win_after_deuce() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 3);
		tennisGame.aTeamGetPoint();
		tennisGame.aTeamGetPoint();

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("Team A Win");
	}

	@Test
	void team_b_win_after_deuce() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 3);
		tennisGame.bTeamGetPoint();
		tennisGame.bTeamGetPoint();

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("Team B Win");
	}

	@Test
	void deuce_after_ad() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 3);
		tennisGame.aTeamGetPoint();
		tennisGame.bTeamGetPoint();

		String score = tennisGame.getScore();
		Assertions.assertThat(score).isEqualTo("Deuce");
	}

	@Test
	void get_point_after_normal_game_is_over() {
		TennisGame tennisGame = new TennisGame();

		Assertions.assertThatThrownBy(
				() -> get_point_with_team_a_and_b(tennisGame, 7, 5)
		).hasMessage("Game is already over");

	}

	@Test
	void get_point_after_deuce_game_is_over() {
		TennisGame tennisGame = new TennisGame();

		get_point_with_team_a_and_b(tennisGame, 3, 3);

		Assertions.assertThatThrownBy(
				() -> get_point_with_team_a_and_b(tennisGame, 2, 2)
		).hasMessage("Game is already over");

	}

	private void get_point_with_team_a_and_b(TennisGame tennisGame, int getPointTime_A, int getPointTime_B) {
		for (int i = 0; i < getPointTime_A; i++) {
			tennisGame.aTeamGetPoint();
		}

		for (int i = 0; i < getPointTime_B; i++) {
			tennisGame.bTeamGetPoint();
		}
	}


}