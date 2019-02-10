package com.niemiec.chat.view;

import java.io.IOException;

import com.niemiec.chat.controllers.ExitInformationAndAcceptanceController;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExitInformationAndAcceptanceView {
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private ExitInformationAndAcceptanceController controller;
	private FXMLLoader loader;
	private VBox vbox;
	private Stage stage;

	public ExitInformationAndAcceptanceView(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		this.dispatcherOfOutgoingRequest = dispatcherOfOutgoingRequest;
	}

	public void show() {
		Platform.runLater(() -> {
			loadFXMLLoader();
			updateGetNickController();
			view();
			addCloseOption();
		});
	}

	private void addCloseOption() {
		stage.setOnCloseRequest(e -> controller.cancel());
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/ExitInformationAndAcceptanceWindow.fxml"));
		try {
			vbox = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		controller = loader.getController();
	}

	private void updateGetNickController() {
		controller.setDisDispatcherOfOutgoingMessages(dispatcherOfOutgoingRequest);
	}

	private void view() {
		Scene scene = new Scene(vbox);
		stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}

	public void close() {
		Platform.runLater(() -> {
			stage.close();
		});
	}
}
