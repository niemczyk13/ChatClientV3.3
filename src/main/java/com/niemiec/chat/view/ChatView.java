package com.niemiec.chat.view;

import com.niemiec.chat.dispatchers.general.DispatcherOfOutgoingRequest;

public class ChatView {
	private GetNickView getNickView;
	private GeneralChatView generalChatView;
	private ExitInformationAndAcceptanceView exitInformationAndAcceptanceView;

	public ChatView(DispatcherOfOutgoingRequest dispatcherOfOutgoingRequest) {
		getNickView = new GetNickView(dispatcherOfOutgoingRequest);
		generalChatView = new GeneralChatView(dispatcherOfOutgoingRequest);
		exitInformationAndAcceptanceView = new ExitInformationAndAcceptanceView(dispatcherOfOutgoingRequest);
	}

	public GetNickView getGetNickWindow() {
		return getNickView;
	}

	public GeneralChatView getGeneralChatView() {
		return generalChatView;
	}

	public ExitInformationAndAcceptanceView getExitInformationAndAcceptanceView() {
		return exitInformationAndAcceptanceView;
	}
}
