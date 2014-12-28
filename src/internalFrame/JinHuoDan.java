package internalFrame;

import internalFrame.guanli.Item;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import model.Gysinfo;
import model.RukuDetail;
import model.RukuMain;
import model.Spinfo;
import model.User;

import com.huawei.dao.DAOException;
import com.huawei.dao.DAOFactory;
import com.huawei.dao.GysDAO;
import com.huawei.dao.RukuMainDAO;
import com.huawei.dao.SpDAO;
import com.huawei.login.Login;

public class JinHuoDan extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private final JTable table;
	private User user = Login.getUser(); 			// 登录用户信息
	private final JTextField jhsj = new JTextField(); 	// 进货时间
	private final JTextField jsr = new JTextField(); 	// 经手人
	private final JComboBox<String> jsfs = new JComboBox<String>(); 	// 计算方式
	private final JTextField lian = new JTextField(); 	// 联系人
	private final JComboBox<Item> gys = new JComboBox<Item>(); 		// 供应商
	private final JTextField piaoHao = new JTextField();// 票号
	private final JTextField pzs = new JTextField("0"); // 品种数量
	private final JTextField hpzs = new JTextField("0");// 货品总数
	private final JTextField hjje = new JTextField("0");// 合计金额
	private final JTextField ysjl = new JTextField(); 	// 验收结论
	private final JTextField czy = new JTextField(user.getName());// 操作员
	private JComboBox<Spinfo> sp;
	
	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public JinHuoDan(){
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle("进货单");
		setSize(700, 400);

		setupComponet(new JLabel("进货票号："), 0, 0, 1, 0, false);
		piaoHao.setFocusable(false);
		setupComponet(piaoHao, 1, 0, 1, 50, true);

		setupComponet(new JLabel("供应商："), 2, 0, 1, 0, false);
		gys.setPreferredSize(new Dimension(150, 21));
		gys.setFocusable(false);
		// 供应商下拉选择框的选择事件
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		
		setupComponet(gys, 3, 0, 1, 50, true);

		setupComponet(new JLabel("联系人："), 4, 0, 1, 0, false);
		lian.setFocusable(false);
		setupComponet(lian, 5, 0, 1, 80, true);

		setupComponet(new JLabel("结算方式："), 0, 1, 1, 0, false);
		jsfs.addItem("现金");
		jsfs.addItem("支票");
		jsfs.setFocusable(false);
		setupComponet(jsfs, 1, 1, 1, 1, true);

		setupComponet(new JLabel("进货时间："), 2, 1, 1, 0, false);
		jhsj.setFocusable(false);
		setupComponet(jhsj, 3, 1, 1, 1, true);

		setupComponet(new JLabel("经手人："), 4, 1, 1, 0, false);
		setupComponet(jsr, 5, 1, 1, 1, true);

		sp = new JComboBox<Spinfo>();
		
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Spinfo info = (Spinfo) sp.getSelectedItem();
				// 如果选择有效就更新表格
				if (info != null) {
					updateTable();
				}
			}
		});

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// 添加事件完成品种数量、货品总数、合计金额的计算
		table.addContainerListener(new ComputeInfo());
		JScrollPane scrollPanel = new JScrollPane(table);
		scrollPanel.setPreferredSize(new Dimension(380, 200));
		setupComponet(scrollPanel, 0, 2, 6, 1, true);

		setupComponet(new JLabel("品种数量："), 0, 3, 1, 0, false);
		pzs.setFocusable(false);
		setupComponet(pzs, 1, 3, 1, 1, true);

		setupComponet(new JLabel("货品总数："), 2, 3, 1, 0, false);
		hpzs.setFocusable(false);
		setupComponet(hpzs, 3, 3, 1, 1, true);

		setupComponet(new JLabel("合计金额："), 4, 3, 1, 0, false);
		hjje.setFocusable(false);
		setupComponet(hjje, 5, 3, 1, 1, true);

		setupComponet(new JLabel("验收结论："), 0, 4, 1, 0, false);
		setupComponet(ysjl, 1, 4, 1, 1, true);

		setupComponet(new JLabel("操作人员："), 2, 4, 1, 0, false);
		czy.setFocusable(false);
		setupComponet(czy, 3, 4, 1, 1, true);

		// 单击添加按钮在表格中添加新的一行
		JButton tjButton = new JButton("添加");
		tjButton.addActionListener(new TjActionListener());
		setupComponet(tjButton, 4, 4, 1, 1, false);

		// 单击入库按钮保存进货信息
		JButton rkButton = new JButton("入库");
		rkButton.addActionListener(new RkActionListener());
		setupComponet(rkButton, 5, 4, 1, 1, false);
		
		addInternalFrameListener(new InitTasks());
		
	}
	
	private final class ComputeInfo implements ContainerListener{

		@Override
		public void componentAdded(ContainerEvent e) {
		}

		@Override
		public void componentRemoved(ContainerEvent e) {
			// 清除空行
			clearEmptyRow();
			// 计算代码
			int rows = table.getRowCount();
			int count = 0;
			double money = 0.0;
			// 计算品种数量
			Spinfo column = null;
			if (rows > 0)
				column = (Spinfo) table.getValueAt(rows - 1, 0);
			if (rows > 0 && (column == null || column.getId().isEmpty()))
				rows--;
			// 计算货品总数和金额
			for (int i = 0; i < rows; i++) {
				String column7 = (String) table.getValueAt(i, 7);
				String column6 = (String) table.getValueAt(i, 6);
				int c7 = (column7 == null || column7.isEmpty()) ? 0 : Integer
						.parseInt(column7);
				float c6 = (column6 == null || column6.isEmpty()) ? 0 : Float
						.parseFloat(column6);
				count += c7;
				money += c6 * c7;
			}

			pzs.setText(rows + "");
			hpzs.setText(count + "");
			hjje.setText(money + "");
			// /////////////////////////////////////////////////////////////////
		}
	}
	
	// 窗体的初始化任务
	private final class InitTasks extends InternalFrameAdapter {
		public void internalFrameActivated(InternalFrameEvent e) {
			super.internalFrameActivated(e);
			initPiaoHao();
			initTimeField();
			initGysField();
			initSpBox();
			initTable();
			pack();
		}
	}
	
	private class TjActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			stopTableCellEditing();
			
			for(int i=0;i<table.getRowCount();i++){
				Spinfo spinfo = (Spinfo)table.getValueAt(i, 0);
				if(spinfo == null){
					return;
				}
			}
			
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.addRow(new Object[table.getColumnCount()]);
		}
	}
	
	private class RkActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			// 结束表格中没有编写的单元
			stopTableCellEditing();
			// 清除空行
			clearEmptyRow();
			String pzsStr = pzs.getText().trim(); // 品种数
			String jeStr = hjje.getText().trim(); // 合计金额
			String jsfsStr = jsfs.getSelectedItem().toString().trim(); // 结算方式
			String jsrStr = jsr.getText().trim(); // 经手人
			String czyStr = czy.getText().trim(); // 操作员
			String rkDate = format.format(new Date()).trim(); // 入库时间
			String ysjlStr = ysjl.getText().trim(); // 验收结论
			String id = piaoHao.getText().trim(); // 票号
			String gysName = gys.getSelectedItem().toString();// 供应商名字
			if (jsrStr == null || jsrStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "请填写经手人");
				return;
			}
			if (ysjlStr == null || ysjlStr.isEmpty()) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "填写验收结论");
				return;
			}
			if (table.getRowCount() <= 0) {
				JOptionPane.showMessageDialog(JinHuoDan.this, "填加入库商品");
				return;
			}
			RukuMain rukuMain = new RukuMain(id, pzsStr, jeStr, ysjlStr,
					gysName, rkDate, czyStr, jsrStr, jsfsStr);
			Set<RukuDetail> rukuDetails = rukuMain.getRukuDetails();
			int rows = table.getRowCount();
			for (int i = 0; i < rows; i++) {
				Spinfo spinfo = (Spinfo) table.getValueAt(i, 0);
				String djStr = (String) table.getValueAt(i, 6);
				String slStr = (String) table.getValueAt(i, 7);
				Double dj = Double.valueOf(djStr);
				Integer sl = Integer.valueOf(slStr);
				RukuDetail detail = new RukuDetail();
				detail.setSpId(spinfo.getId());
				detail.setRkId(rukuMain.getRkId());
				detail.setDj(dj);
				detail.setSl(sl);
				rukuDetails.add(detail);
			}
			
			try {
				RukuMainDAO rukuMainDAO = DAOFactory.getRukuMainDAO();
				rukuMainDAO.save(rukuMain);
				JOptionPane.showMessageDialog(JinHuoDan.this, "入库完成");
				DefaultTableModel dftm = new DefaultTableModel();
				table.setModel(dftm);
				initTable();
				pzs.setText("0");
				hpzs.setText("0");
				hjje.setText("0");
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// 清除空行
	private synchronized void clearEmptyRow() {
		DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		for (int i = 0; i < table.getRowCount(); i++) {
			Spinfo info2 = (Spinfo) table.getValueAt(i, 0);
			if (info2 == null || info2.getId() == null
					|| info2.getId().isEmpty()) {
				dftm.removeRow(i);
			}
		}
	}
	
	// 停止表格单元的编辑
	private void stopTableCellEditing() {
		TableCellEditor cellEditor = table.getCellEditor();
		if (cellEditor != null)
			cellEditor.stopCellEditing();
	}
	
	private synchronized void updateTable(){
		Spinfo spinfo = (Spinfo)sp.getSelectedItem();
		int row = table.getSelectedRow();
		if(row>=0 && spinfo != null){
			table.setValueAt(spinfo.getId(), row, 1);
			table.setValueAt(spinfo.getCd(), row, 2);
			table.setValueAt(spinfo.getDw(), row, 3);
			table.setValueAt(spinfo.getGg(), row, 4);
			table.setValueAt(spinfo.getBz(), row, 5);
			table.setValueAt(spinfo.getMemo(), row, 6);
			table.setValueAt("1", row, 7);
			table.setValueAt(spinfo.getPh(), row, 8);
			table.setValueAt(spinfo.getPzwh(), row, 9);
			table.editCellAt(row, 7);
		}
	}
	
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	
	private void doGysSelectAction(){
		Item selectItem = (Item)gys.getSelectedItem();
		if(selectItem == null){
			return;
		}
		try {
			GysDAO gysDAO  = DAOFactory.getGysDAO();
			Gysinfo gysinfo = gysDAO.findByItem(selectItem);
			lian.setText(gysinfo.getLtel());
			initSpBox();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		gys.setToolTipText(selectItem.getName());
	}
	private void initGysField() {// 初始化供应商字段
		try {
			GysDAO gysDAO  = DAOFactory.getGysDAO();
			List<Item> items = gysDAO.getGysItems();
			for(Item item : items){
				gys.addItem(item);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		doGysSelectAction();
	}
	//初始化表头
	private void initTable() {
		String[] columnNames = {"商品名称", "商品编号", "产地", "单位", "规格", "包装", "单价",
				"数量", "批号", "批准文号"};
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.setColumnIdentifiers(columnNames);
		TableColumn column = table.getColumnModel().getColumn(0);
		DefaultCellEditor editor = new DefaultCellEditor(sp);
		column.setCellEditor(editor); 
	}
	private void initSpBox(){
		try {
			List<Spinfo> list = new ArrayList<Spinfo>();
			for(int i=0;table!=null && i<table.getRowCount();i++){
				list.add((Spinfo)table.getValueAt(i, 0));
			}
			SpDAO spDAO = DAOFactory.getSpDAO();
			List<Spinfo> spinfos = spDAO.findByGysname(gys.getSelectedItem().toString());
			sp.removeAllItems();
			for(Spinfo spinfo: spinfos){
				if(list.contains(spinfo)){
					continue;
				}
				sp.addItem(spinfo);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	private void initPiaoHao(){
		try {
			RukuMainDAO rukuMainDAO = DAOFactory.getRukuMainDAO();
			String maxId = rukuMainDAO.getMaxId();
			piaoHao.setText(maxId);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void initTimeField(){
		new Thread(){
			public void run(){
				while(true){
					jhsj.setText(format.format(new Date()));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	
}
