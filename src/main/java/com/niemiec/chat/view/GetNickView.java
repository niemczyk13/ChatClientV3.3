package com.niemiec.chat.view;

import java.io.IOException;

import com.niemiec.chat.controllers.GetNickController;
import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GetNickView {
	private DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest;
	private GetNickController getNickController;
	private FXMLLoader loader;
	private VBox vbox;
	private Stage stage;

	public GetNickView(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
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
		stage.setOnCloseRequest(e -> {
			System.exit(0);
		});
	}

	private void loadFXMLLoader() {
		loader = new FXMLLoader(this.getClass().getResource("/fxml/GetNickWindow.fxml"));
		try {
			vbox = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		getNickController = loader.getController();
	}

	private void updateGetNickController() {
		getNickController.setDisDispatcherOfOutgoingMessages(dispatcherOfOutgoingRequest);
	}

	private void view() {
		Scene scene = new Scene(vbox);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Podaj nick");
		stage.sizeToScene();
		stage.show();
	}

	public void close() {
		Platform.runLater(() -> {
			stage.close();
		});
	}

	public void viewInformation(String info) {
		Platform.runLater(() -> {
			getNickController.setTextInformation(info);
		});
	}
}