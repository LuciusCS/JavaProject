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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import cn.shuangze.assetsms.dao.AssetsDaoMpl;
import cn.shuangze.assetsms.dao.AssetsTypeDao;
import cn.shuangze.assetsms.dao.ConnectionFactory;
import cn.shuangze.assetsms.entity.Assets;

/*������ʾ�ѱ��ϵ��ʲ�*/

public class AssetRuinedView extends JFrame implements ActionListener, ListSelectionListener {

	
	  private DefaultTableModel tableModel; 
	
	AssetsTypeDao assetsTypeDao;
	Container contentPane;
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel downPanel = new JPanel();

	Dimension faceSize = new Dimension(400, 400);

	JButton borrowAsset = new JButton();
	JButton eixtInfo = new JButton();

	JScrollPane jScrollPane1;
	JTable jTable;
	ListSelectionModel listSelectionModel = null;
	String[] colName = { "����", "�ͺ�", "�۸�", "��������", "��ע" };

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public AssetRuinedView() {

	//	assetsTypeDao = new AssetsTypeDao();
		// ���ÿ�ܵĴ�С
		this.setSize(faceSize);
		// ���ñ���
		this.setTitle("�ѱ����ʲ�");
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
		ResultSet rSet=assetsDaoMpl.getRuinedInfo(connection);
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
	}

	public void downInit() {
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
	   if (obj == eixtInfo) { // �˳�
			this.dispose();
		}
		jTable.revalidate();
	}

	/**
	 * �����ѡ��ʱ�Ĳ���
	 */
	public void valueChanged(ListSelectionEvent lse) {
		int selectedRow = jTable.getSelectedRow();
	}
}
