package com.huawei;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class JXCFrame1 {
	
	private JFrame frame;
	private JDesktopPane desktopPane;
	private JLabel backLabel;
	private Preferences preferences;
	
	private Map<String,JInternalFrame> ifs = new HashMap<String,JInternalFrame>();
	
	public JXCFrame1(){
		frame = new JFrame("��ҵ���������ϵͳ");
		frame.getContentPane().setBackground(new Color(170, 188, 120));
		frame.addComponentListener(new FrameListener());
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		backLabel = new JLabel();
		backLabel.setVerticalAlignment(SwingConstants.TOP);
		backLabel.setHorizontalAlignment(SwingConstants.CENTER);
		updateBackImage();
		desktopPane = new JDesktopPane();
		desktopPane.add(backLabel,new Integer(Integer.MIN_VALUE));
		frame.getContentPane().add(desktopPane);
		JTabbedPane navigationPanel = createNavigationPanel();
		frame.getContentPane().add(navigationPanel,BorderLayout.NORTH);
		frame.setVisible(true);
	}
	
	private class FrameListener implements ComponentListener{

		@Override
		public void componentResized(ComponentEvent e) {
			updateBackImage();
			
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private JTabbedPane createNavigationPanel() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setFocusable(true);
		tabbedPane.setBackground(new Color(211,230,192));
		tabbedPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		JPanel baseManagePanel = new JPanel();
		baseManagePanel.setBackground(new Color(215,223,194));
		baseManagePanel.setLayout(new BoxLayout(baseManagePanel,BoxLayout.X_AXIS));
		baseManagePanel.add(createFrameButton("�ͻ���Ϣ����","KeHuGuanli"));
		baseManagePanel.add(createFrameButton("��Ʒ��Ϣ����","ShangPingGuanli"));
		baseManagePanel.add(createFrameButton("��Ӧ����Ϣ����","GysGuanli"));
		
		JPanel depotManagePanel = new JPanel();
		depotManagePanel.setBackground(new Color(215,223,194));
		depotManagePanel.setLayout(new BoxLayout(depotManagePanel,BoxLayout.X_AXIS));
		depotManagePanel.add(createFrameButton("����̵�","KuCunPanDian"));
		depotManagePanel.add(createFrameButton("�۸����","JiaGeTiaoZheng"));
		
		JPanel sellManagePanel = new JPanel();
		sellManagePanel.setBackground(new Color(215,223,194));
		sellManagePanel.setLayout(new BoxLayout(sellManagePanel,BoxLayout.X_AXIS));
		sellManagePanel.add(createFrameButton("���۵�","XiaoShouDan"));
		sellManagePanel.add(createFrameButton("�����˻�","XiaoShouTuiHuo"));
		
		JPanel searchManagePanel = new JPanel();
		searchManagePanel.setBounds(0, 0, 600, 41);
		searchManagePanel.setBackground(new Color(215,223,194));
		searchManagePanel.setLayout(new BoxLayout(searchManagePanel,BoxLayout.X_AXIS));
		searchManagePanel.add(createFrameButton("�ͻ���Ϣ��ѯ","XiaoShouDan"));
		searchManagePanel.add(createFrameButton("��Ʒ��Ϣ��ѯ","XiaoShouTuiHuo"));
		searchManagePanel.add(createFrameButton("��Ӧ����Ϣ��ѯ","XiaoShouDan"));
		searchManagePanel.add(createFrameButton("������Ϣ��ѯ","XiaoShouTuiHuo"));
		searchManagePanel.add(createFrameButton("�����˻���ѯ","XiaoShouDan"));
		searchManagePanel.add(createFrameButton("����ѯ","XiaoShouTuiHuo"));
		searchManagePanel.add(createFrameButton("����˻���ѯ","XiaoShouDan"));
		searchManagePanel.add(createFrameButton("��������","XiaoShouTuiHuo"));
		
		JPanel stockManagePanel = new JPanel();
		stockManagePanel.setBackground(new Color(215,223,194));
		stockManagePanel.setLayout(new BoxLayout(stockManagePanel,BoxLayout.X_AXIS));
		stockManagePanel.add(createFrameButton("������","XiaoShouDan"));
		stockManagePanel.add(createFrameButton("�����˻�","XiaoShouTuiHuo"));
		
		JPanel sysManagePanel = new JPanel();
		sysManagePanel.setBackground(new Color(215,223,194));
		sysManagePanel.setLayout(new BoxLayout(sysManagePanel,BoxLayout.X_AXIS));
		sysManagePanel.add(createFrameButton("����Ա����","XiaoShouDan"));
		sysManagePanel.add(createFrameButton("��������","XiaoShouTuiHuo"));
		sysManagePanel.add(createFrameButton("Ȩ�޹���","XiaoShouTuiHuo"));
		
		tabbedPane.addTab("������Ϣ����", null, baseManagePanel, "������Ϣ����");
		tabbedPane.addTab("������", null, depotManagePanel, "������");
		tabbedPane.addTab("���۹���", null, sellManagePanel, "���۹���");
		tabbedPane.addTab("��ѯͳ��", null, searchManagePanel, "��ѯͳ��");
		tabbedPane.addTab("��������", null, stockManagePanel, "��������");
		tabbedPane.addTab("ϵͳ����", null, sysManagePanel, "ϵͳ����");
		
		return tabbedPane;
	}

	private Component createFrameButton(String fName, String cName) {
		String imageUrl = "res/ActionIcon"+fName + ".png";
		String imageUrl_roll = "res/ActionIcon" + fName +"_roll.png";
		String imageUrl_down = "res/ActionIcon" + fName +"_down.png";
		Icon icon = new ImageIcon(imageUrl);
		Icon icon_roll = null;
		if(new File(imageUrl_roll).exists()){
			
			icon_roll = new ImageIcon(imageUrl_roll);
		}
		Icon icon_down = null;
		if(new File(imageUrl_down).exists()){
			icon_down = new ImageIcon(imageUrl_down);
		}
		
		Action action = new OpenFrameAction(fName,cName,icon);
		JButton button = new JButton(action);
		button.setMargin(new Insets(0,0,0,0));
		button.setBorderPainted(false);
		button.setHideActionText(true);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		if(icon_roll != null){
			button.setRolloverIcon(icon_roll);
		}
		if(icon_down != null){
			button.setPressedIcon(icon_down);
		}
		return button;
	}

	private void updateBackImage() {
		if(backLabel != null){
			int backw = this.frame.getWidth();
			int backh = this.frame.getHeight();
			backLabel.setSize(backw, backh);
			backLabel.setText("<html><body><image width='" + backw
					+ "' height='" + (backh - 110) + "' src="
					+ JXCFrame1.this.getClass().getResource("welcome.jpg")
					+ "'></img></body></html>");
		}
	}
	
	public class OpenFrameAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		private String cName;

		public OpenFrameAction(String fName, String cName, Icon icon) {
			System.out.println(icon);
			this.cName = cName;
			putValue(Action.NAME, fName);
			putValue(Action.SHORT_DESCRIPTION, fName);
			putValue(Action.SMALL_ICON, icon);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame jf = createIFrame(cName);
			jf.addInternalFrameListener(new InternalFrameAdapter(){
				@Override
				public void internalFrameClosed(InternalFrameEvent e) {
					ifs.remove(cName);
				}
			});
			if(jf.getDesktopPane() == null){
				desktopPane.add(jf);
				jf.setVisible(true);
			}
			try {
				jf.setSelected(true);
			} catch (PropertyVetoException e1) {
				e1.printStackTrace();
			}
		}

		private JInternalFrame createIFrame(String className) {
			JInternalFrame jf = null;
			if(!ifs.containsKey(className)){
				try {
					Class fclass = Class.forName("internalFrame."+className);
					Constructor<JInternalFrame> constructor = fclass.getConstructor(null);
					jf = constructor.newInstance(null);
					ifs.put(className, jf);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				jf = ifs.get(className);
			}
			return jf;
		}
	}

	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.invokeLater(new Runnable(){
				public void run(){
					new JXCFrame1();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
