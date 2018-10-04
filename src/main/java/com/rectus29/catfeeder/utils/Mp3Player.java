package com.rectus29.catfeeder.utils;

import javazoom.jl.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mp3Player {

	private static final Logger logger = LogManager.getLogger(Mp3Player.class);
	private Player player;

	public Player play(String filePath) {
		try {
			player = new Player(getClass().getClassLoader().getResourceAsStream(filePath));
			player.play();
		} catch (Exception e) {
			logger.error("Error while playing mp3", e);
		}
		return player;
	}

	public Player stop() {
		player.close();
		return player;
	}
}