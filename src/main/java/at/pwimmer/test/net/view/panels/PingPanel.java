package at.pwimmer.test.net.view.panels;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import at.pwimmer.test.net.config.AppConfig;
import at.pwimmer.test.net.model.IPTableModel;
import at.pwimmer.test.net.model.PingSystem;
import at.pwimmer.test.net.task.PingWorker;
import at.pwimmer.test.net.task.WorkerPropertyListener;
import net.miginfocom.swing.MigLayout;

public class PingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final Font DEFAULT_FONT = new Font("Tahoma", Font.PLAIN, 14);
	private static final String GOOGLE_IP = "172.217.18.67";
	
	private final AppConfig config;
	private final IPTableModel tableModel;
	
	private JTable tableSystems;
	private StatusPanel panelStatusGW;
	private StatusPanel panelStatusGoogle;
	private StatusPanel panelWorkerStatus;
	
	public PingPanel(AppConfig config) {
		if(config == null) 	throw new IllegalArgumentException("The passed App-Config is null!");

		this.config = config;
		this.tableModel = new IPTableModel(config.getSystemList());
		initComponents();
	}
	
	@SuppressWarnings("unused")
	private PingPanel() {
		this.config = null;
		this.tableModel = new IPTableModel();
		initComponents();
	}
	
	public void pingSingleSystem(String ip, String name, StatusPanel statusPanel) {
		if(ip != null && name != null && statusPanel != null) {
			PingWorker worker = new PingWorker(ip, statusPanel);
			worker.addPropertyChangeListener(new WorkerPropertyListener(panelWorkerStatus));
			worker.execute();
		}
	}
	
	public void pingMultipleSystems(List<PingSystem> systemList) {
		
	}
	
	private void initComponents() {
		setLayout(new MigLayout("", "[grow][grow][grow]", "[30.00][grow][][grow][]"));
		
		JLabel lblDefaultgateway = new JLabel("Gateway");
		lblDefaultgateway.setFont(DEFAULT_FONT);
		add(lblDefaultgateway, "cell 0 0,alignx center");
		
		panelStatusGW = new StatusPanel();
		add(panelStatusGW, "cell 1 0,grow");
		
		JButton btnPingGateway = new JButton("Ping");
		btnPingGateway.addActionListener(e -> pingSingleSystem(config.getGateway(), "Gateway", panelStatusGW));
		btnPingGateway.setFont(DEFAULT_FONT);
		add(btnPingGateway, "cell 2 0,growx");
		
		JLabel lblGoogle = new JLabel("Google");
		lblGoogle.setFont(DEFAULT_FONT);
		add(lblGoogle, "cell 0 1,alignx center");
		
		panelStatusGoogle = new StatusPanel();
		add(panelStatusGoogle, "cell 1 1,grow");
		
		JButton btnPingGoogle = new JButton("Ping");
		btnPingGoogle.addActionListener(e -> pingSingleSystem(GOOGLE_IP, "Google", panelStatusGoogle));
		btnPingGoogle.setFont(DEFAULT_FONT);
		add(btnPingGoogle, "cell 2 1,growx");
		
		JSeparator separator_2 = new JSeparator();
		add(separator_2, "cell 0 2 3 1,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 3 1,grow");

		tableSystems = new JTable();
		tableSystems.setFont(DEFAULT_FONT);
		tableSystems.setFillsViewportHeight(true);
		tableSystems.setModel(tableModel);
		scrollPane.setViewportView(tableSystems);
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 4 3 1,grow");
		panel.setLayout(new MigLayout("", "[grow][][grow][grow]", "[grow]"));

		JButton btnPingSystemlist = new JButton("Ping System-List");
		btnPingSystemlist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnPingSystemlist, "flowx,cell 0 0,growx");
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		panel.add(separator, "cell 1 0,growy");
		
		JButton btnPingAll = new JButton("Ping All");
		panel.add(btnPingAll, "cell 2 0,growx");
		btnPingAll.setToolTipText("Ping all Systems, including the ones inside the System-Table.");
		btnPingAll.setFont(DEFAULT_FONT);
		btnPingAll.addActionListener(e -> {
			pingSingleSystem(config.getGateway(), "Gateway", panelStatusGW);
			pingSingleSystem(GOOGLE_IP, "Google", panelStatusGoogle);
			pingMultipleSystems(config.getSystemList());
		});
		
		panelWorkerStatus = new StatusPanel();
		panel.add(panelWorkerStatus, "cell 3 0,grow");
	}
}