package internalFrame.gysGuanLi;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import keyListener.InputKeyListener;
import model.Gysinfo;

import com.huawei.dao.DAOException;
import com.huawei.dao.DAOFactory;
import com.huawei.dao.GysDAO;

public class GysTianJiaPane extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField EMailF;
	private JTextField yinHangF;
	private JTextField lianXiRenDianHuaF;
	private JTextField lianXiRenF;
	private JTextField chuanZhenF;
	private JTextField dianHuaF;
	private JTextField diZhiF;
	private JTextField bianMaF;
	private JTextField jianChengF;
	private JTextField quanChengF;
	private JButton resetButton;
	
	public GysTianJiaPane(){
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);
		
		setupComponet(new JLabel("供应商全称："), 0, 0, 1, 1, false);

		quanChengF = new JTextField();
		setupComponet(quanChengF, 1, 0, 3, 400, true);

		setupComponet(new JLabel("简称："), 0, 1, 1, 1, false);

		jianChengF = new JTextField();
		setupComponet(jianChengF, 1, 1, 1, 160, true);

		setupComponet(new JLabel("邮政编码："), 2, 1, 1, 1, false);
		bianMaF = new JTextField();
		bianMaF.addKeyListener(new InputKeyListener());
		setupComponet(bianMaF, 3, 1, 1, 0, true);

		setupComponet(new JLabel("地址："), 0, 2, 1, 1, false);
		diZhiF = new JTextField();
		setupComponet(diZhiF, 1, 2, 3, 0, true);

		setupComponet(new JLabel("电话："), 0, 3, 1, 1, false);
		dianHuaF = new JTextField();
		dianHuaF.addKeyListener(new InputKeyListener());
		setupComponet(dianHuaF, 1, 3, 1, 0, true);

		setupComponet(new JLabel("传真："), 2, 3, 1, 1, false);
		chuanZhenF = new JTextField();
		chuanZhenF.addKeyListener(new InputKeyListener());
		setupComponet(chuanZhenF, 3, 3, 1, 0, true);

		setupComponet(new JLabel("联系人："), 0, 4, 1, 1, false);
		lianXiRenF = new JTextField();
		setupComponet(lianXiRenF, 1, 4, 1, 0, true);

		setupComponet(new JLabel("联系人电话："), 2, 4, 1, 1, false);
		lianXiRenDianHuaF = new JTextField();
		lianXiRenDianHuaF.addKeyListener(new InputKeyListener());
		setupComponet(lianXiRenDianHuaF, 3, 4, 1, 0, true);

		setupComponet(new JLabel("开户银行："), 0, 5, 1, 1, false);
		yinHangF = new JTextField();
		setupComponet(yinHangF, 1, 5, 1, 0, true);

		setupComponet(new JLabel("电子信箱："), 2, 5, 1, 1, false);
		EMailF = new JTextField();
		setupComponet(EMailF, 3, 5, 1, 0, true);

		final JButton tjButton = new JButton();
		tjButton.addActionListener(new TjActionListener());
		tjButton.setText("添加");
		setupComponet(tjButton, 2, 6, 1, 0, false);

		resetButton = new JButton();
		setupComponet(resetButton, 3, 6, 1, 0, false);
		resetButton.addActionListener(new ResetActionListener());
		resetButton.setText("重填");
	}
	
	
	// 设置组件位置并添加到容器中
		private void setupComponet(JComponent component, int gridx, int gridy,
				int gridwidth, int ipadx, boolean fill) {
			final GridBagConstraints gridBagConstrains = new GridBagConstraints();
			gridBagConstrains.gridx = gridx;
			gridBagConstrains.gridy = gridy;
			if(gridwidth>1){
				gridBagConstrains.gridwidth = gridwidth;
			}
			if(ipadx>0){
				gridBagConstrains.ipadx = ipadx;
			}
			gridBagConstrains.insets = new Insets(5, 1, 3, 1);
			if (fill)
				gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
			this.add(component, gridBagConstrains);
		}
		
		public class ResetActionListener implements ActionListener{// 重填按钮的事件监听类
			@Override
			public void actionPerformed(ActionEvent e) {
				EMailF.setText("");
				yinHangF.setText("");
				lianXiRenDianHuaF.setText("");
				lianXiRenF.setText("");
				chuanZhenF.setText("");
				dianHuaF.setText("");
				diZhiF.setText("");
				bianMaF.setText("");
				jianChengF.setText("");
				quanChengF.setText("");
				
			}
			
		}
		
		public class TjActionListener implements ActionListener{// 添加按钮的事件监听类
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = EMailF.getText().trim();
				String yh = yinHangF.getText().trim();
				String ltel = lianXiRenDianHuaF.getText().trim();
				String lian = lianXiRenF.getText().trim();
				String fax = chuanZhenF.getText().trim();
				String tel = dianHuaF.getText().trim();
				String address = diZhiF.getText().trim();
				String bianma = bianMaF.getText().trim();
				String jc = jianChengF.getText().trim();
				String name = quanChengF.getText().trim();
				if(name.equals("")){
					JOptionPane.showMessageDialog(GysTianJiaPane.this, "请填写供应商全称!");
					return;
				}else if(tel.equals("")){
					JOptionPane.showMessageDialog(GysTianJiaPane.this, "请填写电话!");
					return;
				}
					
				try {
					GysDAO gysDAO = DAOFactory.getGysDAO();
					Gysinfo gys = gysDAO.findByName(name);
					if(gys != null){
						JOptionPane.showMessageDialog(GysTianJiaPane.this,
								"供应商信息添加失败，存在同名供应商", "供应商添加信息",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					int maxid = gysDAO.getMaxId();
					String id = "gys"+(maxid+1);
					Gysinfo gysinfo = new Gysinfo(id, name, jc, address, bianma, tel, fax, lian, ltel, yh, email);
					
					gysDAO.add(gysinfo);
					
					JOptionPane.showMessageDialog(GysTianJiaPane.this, "已成功添加客户",
							"客户添加信息", JOptionPane.INFORMATION_MESSAGE);
					resetButton.doClick();
					
				} catch (DAOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
}
