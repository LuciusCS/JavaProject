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

import cn.shuangze.assetsms.dao.AssetsDaoMpl;
import cn.shuangze.assetsms.dao.AssetsTypeDao;
import cn.shuangze.assetsms.dao.ConnectionFactory;
import cn.shuangze.assetsms.entity.Assets;

public class AssetsBorrowed extends JFrame implements ActionListener, ListSelectionListener {

	
	  private DefaultTableModel tableModel; 
	
	AssetsTypeDao assetsTypeDao;
	Container contentPane;
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel downPanel = new JPanel();

	Dimension faceSize = new Dimension(400, 400);
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

	JButton borrowAsset = new JButton();
	JButton eixtInfo = new JButton();

	JScrollPane jScrollPane1;
	JTable jTable;
	ListSelectionModel listSelectionModel = null;
	String[] colName = { "����", "�ͺ�", "�۸�", "��������", "��ע" };

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

  public  AssetsBorrowed() {


	//	assetsTypeDao = new AssetsTypeDao();
		// ���ÿ�ܵĴ�С
		this.setSize(faceSize);
		// ���ñ���
		this.setTitle("�������ʲ�");
		this.setResizable(true);

		// ��������ʱ���ڵ�λ��
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - 400) / 2, (screenSize.height - 300) / 2 + 45);
		try {
			Init();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���ڳ�ʼ�����󣬴���ԭ��" + e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void Init() throws Exception {
		contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		Connection connection = ConnectionFactory.getInstance().makeConnection();
		AssetsDaoMpl assetsDaoMpl=new AssetsDaoMpl();
		ResultSet rSet=assetsDaoMpl.getBorrowedInfo(connection);
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

		jLabel1.setText("����");
		jLabel1.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel1);
		centerPanel.add(jTextField1);

		jLabel2.setText("�ͺ�");
		jLabel2.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel2);
		centerPanel.add(jTextField2);

		jLabel3.setText("�۸�");
		jLabel3.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel3);
		centerPanel.add(jTextField3);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		jLabel4.setText("��������");
		jLabel4.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel4);
		centerPanel.add(jTextField4);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		jLabel5.setText("��ע");
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

	public void downInit() {

		borrowAsset.setText("�黹");
		borrowAsset.setFont(new Font("Dialog", 0, 12));
		downPanel.add(borrowAsset);
		eixtInfo.setText("�˳�");
		eixtInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(eixtInfo);

		contentPane.add(downPanel, BorderLayout.SOUTH);

		// ����¼�����
		borrowAsset.addActionListener(this);

		eixtInfo.addActionListener(this);

		borrowAsset.setEnabled(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == borrowAsset) {
			try {
				
				Connection connection = ConnectionFactory.getInstance().makeConnection();
				AssetsDaoMpl assetsDaoMpl=new AssetsDaoMpl();
				Assets assets=new Assets(jTextField1.getText(), jTextField2.getText(),jTextField3.getText(), 3, jTextField5.getText(), jTextField4.getText(),1);
				assetsDaoMpl.borrow(connection, assets);
				JOptionPane.showMessageDialog(null, "�ʲ��黹�ɹ���", "�ʲ��黹", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "���ݿ���ʴ��󣬴���ԭ��" + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		}  else if (obj == eixtInfo) { // �˳�
			this.dispose();
		}
		jTable.revalidate();
	}
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
		borrowAsset.setEnabled(true);

	}

	/**
	 * �����ѡ��ʱ�Ĳ���
	 */
	public void valueChanged(ListSelectionEvent lse) {
		int selectedRow = jTable.getSelectedRow();
		jTextField1.setText((String) jTable.getValueAt(selectedRow, 0));
		jTextField2.setText((String) jTable.getValueAt(selectedRow, 1));
		jTextField3.setText((String) jTable.getValueAt(selectedRow, 2));
		jTextField4.setText((String) jTable.getValueAt(selectedRow, 2));
		jTextField5.setText((String) jTable.getValueAt(selectedRow, 2));

		// �����Ƿ�ɲ���
		jTextField1.setEditable(false);
		jTextField2.setEditable(false);
		jTextField3.setEditable(false);
		jTextField4.setEditable(false);
		jTextField5.setEditable(false);

		borrowAsset.setEnabled(true);
	}
}
