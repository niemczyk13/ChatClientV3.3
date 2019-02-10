package com.niemiec.chat.command.processors.options.data;

import com.niemiec.chat.command.processors.options.CancelExitProcessor;
import com.niemiec.chat.command.processors.options.ExitGeneralChatProcessor;
import com.niemiec.chat.command.processors.options.UpdaterActualInterlocutorProcessor;
import com.niemiec.chat.data.ChatData;

public class OptionChatProcessorData {
	private ChatData chatData;

	private UpdaterActualInterlocutorProcessor updaterOpponentPlayerNickProcessor;
	private ExitGeneralChatProcessor exitGeneralChatProcessor;
	private CancelExitProcessor cancelExitProcessor;

	public OptionChatProcessorData(ChatData chatData) {
		this.chatData = chatData;
	}

	public UpdaterActualInterlocutorProcessor getUpdaterOpponentPlayerNickProcessor() {
		if (updaterOpponentPlayerNickProcessor == null) {
			updaterOpponentPlayerNickProcessor = new UpdaterActualInterlocutorProcessor(chatData);
		}
		return updaterOpponentPlayerNickProcessor;
	}

	public ExitGeneralChatProcessor getExitGeneralChatProcessor() {
		if (exitGeneralChatProcessor == null) {
			exitGeneralChatProcessor = new ExitGeneralChatProcessor(chatData);
		}
		return exitGeneralChatProcessor;
	}

	public CancelExitProcessor getCancelExitProcessor() {
		if (cancelExitProcessor == null) {
			cancelExitProcessor = new CancelExitProcessor(chatData);
		}
		return cancelExitProcessor;
	}
}
