package com.niemiec.chat.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	Chat chat;
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		chat = new Chat();
		chat.runChat();
	}
}
