package com.mr_neez.framework;

public class Launcher {

	public static com.badlogic.gdx.Game launchGame() {
		Res.initialize();
		Ref.game = new Game();
		return Ref.game;
	}
}
