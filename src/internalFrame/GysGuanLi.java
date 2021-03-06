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
		setTitle("供应商管理");
		JTabbedPane tabPane = new JTabbedPane();
		final GysXiuGaiPane spxgPanel = new GysXiuGaiPane();
		final GysTianJiaPane sptjPanel = new GysTianJiaPane();
		tabPane.addTab("供应商信息添加", null, sptjPanel, "供应商添加");
		tabPane.addTab("供应商信息修改与删除", null, spxgPanel, "修改与删除");
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
