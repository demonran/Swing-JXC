package internalFrame.gysGuanLi;

import internalFrame.guanli.Item;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class GysXiuGaiPane extends JPanel{

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
	private JComboBox<Item> gys;
	
	
	public GysXiuGaiPane() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);

		setupComponet(new JLabel("供应商全称："), 0, 0, 1, 1, false);
		quanChengF = new JTextField();
		quanChengF.setEditable(false);
		setupComponet(quanChengF, 1, 0, 3, 300, true);

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

		setupComponet(new JLabel("选择供应商"), 0, 7, 1, 0, false);
		gys = new JComboBox<Item>();
		gys.setPreferredSize(new Dimension(230, 21));
		initComboBox();
		gys.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		
		// 定位供应商信息的下拉选择框
		setupComponet(gys, 1, 7, 2, 0, true);
		JButton modifyButton = new JButton("修改");
		JButton delButton = new JButton("删除");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// 定位按钮
		setupComponet(panel, 3, 7, 1, 0, false);
		// 处理删除按钮的单击事件
		delButton.addActionListener(new DelActionListener());
		// 处理修改按钮的单击事件
		modifyButton.addActionListener(new ModifyActionListener());
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
	
	public void initComboBox(){
		GysDAO gysDAO  = DAOFactory.getGysDAO();
		try {
			List<Item> items = gysDAO.getGysItems();
			gys.removeAllItems();
			for(Item item : items){
				gys.addItem(item);
			}
			doGysSelectAction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void doGysSelectAction(){
		Item selectItem = (Item)gys.getSelectedItem();
		if(selectItem == null){
			return;
		}
		try {
			GysDAO gysDAO  = DAOFactory.getGysDAO();
			Gysinfo gysinfo = gysDAO.findByItem(selectItem);
			
			
			EMailF.setText(gysinfo.getEmail());
			yinHangF.setText(gysinfo.getYh());
			lianXiRenDianHuaF.setText(gysinfo.getLtel());
			lianXiRenF.setText(gysinfo.getLian());
			chuanZhenF.setText(gysinfo.getFax());
			dianHuaF.setText(gysinfo.getTel());
			diZhiF.setText(gysinfo.getAddress());
			bianMaF.setText(gysinfo.getBianma());
			jianChengF.setText(gysinfo.getJc());
			quanChengF.setText(gysinfo.getName());
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	private class DelActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Item item = (Item)gys.getSelectedItem();
			if(item == null){
				return;
			}
			int option =JOptionPane.showConfirmDialog(GysXiuGaiPane.this, "确认删除供应商信息吗？");
			if(option != JOptionPane.OK_OPTION){
				return;
			}
			try {
				GysDAO gysDAO  = DAOFactory.getGysDAO();
				gysDAO.delete(item.getId());
				JOptionPane.showMessageDialog(GysXiuGaiPane.this,"供应商：" + item.getName() + "删除成功");
				initComboBox();
			} catch (DAOException e1) {
				JOptionPane.showMessageDialog(GysXiuGaiPane.this, "无法删除客户：" + item.getName() + "。");
				e1.printStackTrace();
			}
		}
	}
	
	private class ModifyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Item item = (Item)gys.getSelectedItem();
			if(item == null){
				return;
			}
			String id = item.getId();
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
			Gysinfo gysinfo = new Gysinfo(id, name, jc, address, bianma, tel, fax, lian, ltel, yh, email);
			try {
				GysDAO gysDAO  = DAOFactory.getGysDAO();
				gysDAO.update(gysinfo);
				JOptionPane.showMessageDialog(GysXiuGaiPane.this,"修改成功！！");
				//doGysSelectAction();//更新信息
			} catch (DAOException e1) {
				JOptionPane.showMessageDialog(GysXiuGaiPane.this, "修改失败！！");
				e1.printStackTrace();
			}
		}
	}
		
}
