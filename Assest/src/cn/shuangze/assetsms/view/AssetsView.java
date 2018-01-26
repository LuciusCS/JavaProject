package cn.shuangze.assetsms.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import cn.shuangze.assetsms.dao.AssestDao;
import cn.shuangze.assetsms.dao.AssetsDaoMpl;
import cn.shuangze.assetsms.dao.AssetsTypeDao;
import cn.shuangze.assetsms.dao.ConnectionFactory;
import cn.shuangze.assetsms.dao.PersonDaoMpl;
import cn.shuangze.assetsms.entity.Assets;
import cn.shuangze.assetsms.entity.AssetsType;
import cn.shuangze.assetsms.entity.Person;

public class AssetsView extends JFrame implements ActionListener, ListSelectionListener {

	
	  private DefaultTableModel tableModel;   //表格模型对象
	
	AssetsTypeDao assetsTypeDao;
	Container contentPane;
	// 定义所用的面板
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel downPanel = new JPanel();

	// 框架的大小
	Dimension faceSize = new Dimension(400, 400);

	// 定义图形界面元素
	JLabel jLabel1 = new JLabel();
	JLabel jLabel2 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();

	JTextField jTextField1 = new JTextField(15);
	JTextField jTextField2 = new JTextField(15);
	JTextField jTextField3 = new JTextField(15);
	JTextField jTextField4 = new JTextField(15);
	JTextField jTextField5 = new JTextField(15);

	JButton addInfo = new JButton();
	JButton modifyInfo = new JButton();
	JButton deleteInfo = new JButton();
	JButton clearInfo = new JButton();
	JButton saveInfo = new JButton();
	JButton eixtInfo = new JButton();

	// 定义表格
	JScrollPane jScrollPane1;
	JTable jTable;
	ListSelectionModel listSelectionModel = null;
	String[] colName = { "名称", "型号", "价格", "购买日期", "备注" };

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public AssetsView() {

	//	assetsTypeDao = new AssetsTypeDao();
		// 设置框架的大小
		this.setSize(faceSize);
		// 设置标题
		this.setTitle("资产信息");
		this.setResizable(true);

		// 设置运行时窗口的位置
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - 400) / 2, (screenSize.height - 300) / 2 + 45);
		try {
			Init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "窗口初始化错误，错误原因：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void Init() throws Exception {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		Connection connection = ConnectionFactory.getInstance().makeConnection();
		AssetsDaoMpl assetsDaoMpl=new AssetsDaoMpl();
		ResultSet rSet=assetsDaoMpl.getInfo(connection);
		ArrayList<Assets> assetss = new ArrayList();
		while (rSet.next()) {
		    Assets assets=new Assets(rSet.getString("name"), rSet.getString("model"), rSet.getString("price"), rSet.getInt("status"),rSet.getString("other"), rSet.getString("buydate"), rSet.getInt("typeid"));
		    assetss.add(assets);
		}

		    String[][] colValue= {};;
		
			tableModel = new DefaultTableModel(colValue,colName);
			for(int i=0;i<assetss.size();i++) {
				String[] ss=new String[]{assetss.get(i).getName(), assetss.get(i).getModel(), assetss.get(i).getPrice(),assetss.get(i).getDate(), assetss.get(i).getOther()};
				tableModel.addRow(ss);
			}
	    jTable=new JTable(tableModel);
		jTable.setPreferredScrollableViewportSize(new Dimension(400, 300));
		listSelectionModel = jTable.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(this);
		jScrollPane1 = new JScrollPane(jTable);
		jScrollPane1.setPreferredSize(new Dimension(400, 300));

		upPanel.add(jScrollPane1);
		contentPane.add(upPanel, BorderLayout.NORTH);

		jLabel1.setText("名称");
		jLabel1.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel1);
		centerPanel.add(jTextField1);

		jLabel2.setText("型号");
		jLabel2.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel2);
		centerPanel.add(jTextField2);

		jLabel3.setText("价格");
		jLabel3.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel3);
		centerPanel.add(jTextField3);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		jLabel4.setText("购买日期");
		jLabel4.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel4);
		centerPanel.add(jTextField4);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		jLabel5.setText("备注");
		jLabel5.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel5);
		centerPanel.add(jTextField5);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		jTextField1.setEditable(true);
		jTextField2.setEditable(true);
		jTextField3.setEditable(true);
		jTextField4.setEditable(true);
		jTextField5.setEditable(true);

	}

	/**
	 * 下部面板的布局
	 */
	public void downInit() {

		addInfo.setText("增加");
		addInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(addInfo);
		modifyInfo.setText("修改");
		modifyInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(modifyInfo);
		deleteInfo.setText("删除");
		deleteInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(deleteInfo);
		clearInfo.setText("清空");
		clearInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(clearInfo);
		eixtInfo.setText("退出");
		eixtInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(eixtInfo);

		contentPane.add(downPanel, BorderLayout.SOUTH);

		// 添加事件侦听
		addInfo.addActionListener(this);
		modifyInfo.addActionListener(this);
		deleteInfo.addActionListener(this);
		clearInfo.addActionListener(this);
		eixtInfo.addActionListener(this);

		addInfo.setEnabled(true);
		modifyInfo.setEnabled(false);
		deleteInfo.setEnabled(false);
		clearInfo.setEnabled(true);
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == addInfo) {
			try {
				// 增加
				Connection connection = ConnectionFactory.getInstance().makeConnection();
				AssetsDaoMpl assetsDaoMpl=new AssetsDaoMpl();
				Assets assets=new Assets(jTextField1.getText(), jTextField2.getText(),jTextField3.getText(), 1, jTextField5.getText(), jTextField4.getText(),1);
				
				assetsDaoMpl.save(connection, assets);
				JOptionPane.showMessageDialog(null, "资产添加成功！", "资产添加", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		} else if (obj == modifyInfo) {
			try {
				// 修改
				Connection connection = ConnectionFactory.getInstance().makeConnection();
				AssetsDaoMpl assetsDaoMpl=new AssetsDaoMpl();
				Assets assets=new Assets(jTextField1.getText(), jTextField2.getText(),jTextField3.getText(), 1, jTextField5.getText(), jTextField4.getText(),1);
				
				assetsDaoMpl.update(connection, assets);
				JOptionPane.showMessageDialog(null, "资产修改成功！", "资产修改", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		} else if (obj == deleteInfo) {
			try {
				// 删除

				Connection connection = ConnectionFactory.getInstance().makeConnection();
				AssetsDaoMpl assetsDaoMpl=new AssetsDaoMpl();
				Assets assets=new Assets(jTextField1.getText(), jTextField2.getText(),jTextField3.getText(), 1, jTextField5.getText(), jTextField4.getText(),1);
				
				assetsDaoMpl.delete(connection, assets);
				JOptionPane.showMessageDialog(null, "资产删除成功！", "资产删除", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "数据库访问错误，错误原因：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();

		} else if (obj == clearInfo) { // 清空
			setNull();
		} else if (obj == eixtInfo) { // 退出
			this.dispose();
		}
		jTable.revalidate();
	}

	/**
	 * 将文本框清空
	 */
	void setNull() {
		
		jTextField1.setText(null);
		jTextField2.setText(null);
		jTextField3.setText(null);
		jTextField4.setText(null);
		jTextField5.setText(null);
		
		jTextField1.setEditable(true);
		jTextField2.setEditable(true);
		jTextField3.setEditable(true);
		jTextField4.setEditable(true);
		jTextField5.setEditable(true);
		addInfo.setEnabled(true);
		modifyInfo.setEnabled(true);
		deleteInfo.setEnabled(true);
		clearInfo.setEnabled(true);
	}

	/**
	 * 当表格被选中时的操作
	 */
	public void valueChanged(ListSelectionEvent lse) {
		int selectedRow = jTable.getSelectedRow();
		jTextField1.setText((String) jTable.getValueAt(selectedRow, 0));
		jTextField2.setText((String) jTable.getValueAt(selectedRow, 1));
		jTextField3.setText((String) jTable.getValueAt(selectedRow, 2));
		jTextField4.setText((String) jTable.getValueAt(selectedRow, 2));
		jTextField5.setText((String) jTable.getValueAt(selectedRow, 2));

		// 设置是否可操作
		jTextField1.setEditable(true);
		jTextField2.setEditable(true);
		jTextField3.setEditable(true);
		jTextField4.setEditable(true);
		jTextField5.setEditable(true);

		addInfo.setEnabled(true);
		modifyInfo.setEnabled(true);
		deleteInfo.setEnabled(true);
		clearInfo.setEnabled(true);
	}

}
