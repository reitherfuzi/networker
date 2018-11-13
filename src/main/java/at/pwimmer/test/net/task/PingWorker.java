package at.pwimmer.test.net.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import at.pwimmer.test.net.view.panels.StatusPanel;
import at.pwimmer.test.net.view.panels.StatusPanel.Status;

public class PingWorker extends SwingWorker<Void, Boolean> {
	private final String ip;
	private final StatusPanel panel;
	private final ExecutorService exService;
	
	public PingWorker(String ip, StatusPanel statusPanel) {
		if(ip != null && !ip.trim().isEmpty() && statusPanel != null) {
			this.ip = ip;
			this.panel = statusPanel;
			this.exService = Executors.newSingleThreadExecutor();
		}
		else {
			throw new IllegalArgumentException("One or more of the passed Arguments is invalid!");
		}
	}
	
	@Override
	protected Void doInBackground() throws Exception {
		Future<Boolean> future = exService.submit(new PingTask(ip));
		
		while(!future.isDone()) {
			TimeUnit.MILLISECONDS.sleep(300);
		}
		
		boolean result = future.get();
		
		if(result) {
			panel.setStatus(Status.RUNNING);
		}
		else {
			panel.setStatus(Status.STOPPED);
		}
		
		return null;
	}
}