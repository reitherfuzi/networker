package at.pwimmer.test.net.task;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingWorker.StateValue;

import at.pwimmer.test.net.view.panels.StatusPanel;
import at.pwimmer.test.net.view.panels.StatusPanel.Status;

public class WorkerPropertyListener implements PropertyChangeListener {
	private final StatusPanel panel;
	
	public WorkerPropertyListener(StatusPanel panelWorkerStatus) {
		if(panelWorkerStatus != null) {
			this.panel = panelWorkerStatus;
		}
		else {
			throw new IllegalArgumentException("The passed Status-Panel is null!");
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equalsIgnoreCase("state")) {
			StateValue value = (StateValue) evt.getNewValue();
			
			switch (value) {
			case STARTED:	panel.setStatus(Status.RUNNING); break;
			case PENDING:	panel.setStatus(Status.PAUSED); break;
			case DONE:		panel.setStatus(Status.STOPPED); break;
			default: break;
			}
		}
	}
}