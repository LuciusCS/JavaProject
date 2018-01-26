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

import cn.shuangze.assetsms.dao.AssetsTypeDao;
import cn.shuangze.assetsms.dao.ConnectionFactory;
import cn.shuangze.assetsms.dao.PersonDaoMpl;
import cn.shuangze.assetsms.entity.Person;

public class PersonAllView extends JFrame implements ActionListener, ListSelectionListener {

	
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
	JButton eixtInfo = new JButton();

	// 定义表格
	JScrollPane jScrollPane1;
	JTable jTable;
	ListSelectionModel listSelectionModel = null;
	String[] colName = { "姓名", "性别", "部门", "职位", "其他" };

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public PersonAllView() {

	//	assetsTypeDao = new AssetsTypeDao();
		// 设置框架的大小
		this.setSize(faceSize);
		// 设置标题
		this.setTitle("人员信息管理");
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
		PersonDaoMpl personDaoMpl = new PersonDaoMpl();
		ResultSet rs = personDaoMpl.getInfo(connection);
		ArrayList<Person> persons = new ArrayList();
		while (rs.next()) {
			Person person = new Person(rs.getString("name"), rs.getString("sex"), rs.getString("dept"),
					rs.getString("job"), rs.getString("other"));
			persons.add(person);
		}

		    String[][] colValue= {};;
		
			tableModel = new DefaultTableModel(colValue,colName);
			for(int i=0;i<persons.size();i++) {
				String[] ss=new String[]{persons.get(i).getName(), persons.get(i).getSex(), persons.get(i).getDept(),persons.get(i).getJob(), persons.get(i).getOther()};
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

	}

	/**
	 * 下部面板的布局
	 */
	public void downInit() {


		eixtInfo.setText("退出");
		eixtInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(eixtInfo);

		contentPane.add(downPanel, BorderLayout.SOUTH);

		eixtInfo.addActionListener(this);

	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
	if (obj == eixtInfo) { // 退出
			this.dispose();
		}
		jTable.revalidate();
	}

	/**
	 * 将文本框清空
	 */
	void setNull() {

	}

	/**
	 * 当表格被选中时的操作
	 */
	public void valueChanged(ListSelectionEvent lse) {
		int selectedRow = jTable.getSelectedRow();
	}

}
