package at.pwimmer.test.net.view;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import at.pwimmer.test.net.config.AppConfig;
import at.pwimmer.test.net.view.panels.PingPanel;
import at.pwimmer.test.net.view.panels.SysInfoPanel;
import net.miginfocom.swing.MigLayout;

public class NetView implements Runnable {
	private final JFrame frame;
	private final JPanel contentPane;
	private final AppConfig config;
	
	public NetView(AppConfig appConfig) {
		if(appConfig != null) {
			this.config = appConfig;
		}
		else {
			throw new IllegalArgumentException("The passed App-Config is null!");
		}
		
		frame = new JFrame();
		frame.setTitle("Net-View");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][][grow]"));
		frame.setContentPane(contentPane);
		
		JLabel lblHeadline = new JLabel("Network - Application");
		lblHeadline.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblHeadline, "cell 0 0,alignx center");
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 1,growx");
		
		SysInfoPanel panelSysInfo = new SysInfoPanel(config);
		panelSysInfo.setBorder(new TitledBorder(null, "System-Informations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelSysInfo, "cell 0 2,grow");
		
		PingPanel panelPing = new PingPanel(config);
		panelPing.setBorder(new TitledBorder(null, "Ping", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelPing, "cell 0 3,grow");
	}
	
	@Override
	public void run() {
		frame.pack();
		frame.setVisible(true);
	}
	
	public void dispose() {
		frame.dispose();
	}
}