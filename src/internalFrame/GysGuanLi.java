package internalFrame;
import internalFrame.gysGuanLi.GysTianJiaPane;
import internalFrame.gysGuanLi.GysXiuGaiPane;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class GysGuanLi extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GysGuanLi() {
		setIconifiable(true);
		setClosable(true);
		setBounds(50, 50, 700, 400);
		setTitle("��Ӧ�̹���");
		JTabbedPane tabPane = new JTabbedPane();
		final GysXiuGaiPane spxgPanel = new GysXiuGaiPane();
		final GysTianJiaPane sptjPanel = new GysTianJiaPane();
		tabPane.addTab("��Ӧ����Ϣ���", null, sptjPanel, "��Ӧ�����");
		tabPane.addTab("��Ӧ����Ϣ�޸���ɾ��", null, spxgPanel, "�޸���ɾ��");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spxgPanel.initComboBox();
			}
		});
		pack();
		setVisible(true);
	}
}
