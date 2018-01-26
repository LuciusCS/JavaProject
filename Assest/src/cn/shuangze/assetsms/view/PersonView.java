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
import cn.shuangze.assetsms.entity.AssetsType;
import cn.shuangze.assetsms.entity.Person;

public class PersonView extends JFrame implements ActionListener, ListSelectionListener {

	
	  private DefaultTableModel tableModel;   //���ģ�Ͷ���
	
	AssetsTypeDao assetsTypeDao;
	Container contentPane;
	// �������õ����
	JPanel upPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel downPanel = new JPanel();

	// ��ܵĴ�С
	Dimension faceSize = new Dimension(400, 400);

	// ����ͼ�ν���Ԫ��
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

	// ������
	JScrollPane jScrollPane1;
	JTable jTable;
	ListSelectionModel listSelectionModel = null;
	String[] colName = { "����", "�Ա�", "����", "ְλ", "����" };

	GridBagLayout girdBag = new GridBagLayout();
	GridBagConstraints girdBagCon;

	public PersonView() {

	//	assetsTypeDao = new AssetsTypeDao();
		// ���ÿ�ܵĴ�С
		this.setSize(faceSize);
		// ���ñ���
		this.setTitle("��Ա��Ϣ����");
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

		jLabel1.setText("����");
		jLabel1.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel1);
		centerPanel.add(jTextField1);

		jLabel2.setText("�Ա�");
		jLabel2.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel2);
		centerPanel.add(jTextField2);

		jLabel3.setText("����");
		jLabel3.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel3);
		centerPanel.add(jTextField3);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		jLabel4.setText("ְλ");
		jLabel4.setFont(new Font("Dialog", 0, 12));
		centerPanel.add(jLabel4);
		centerPanel.add(jTextField4);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		jLabel5.setText("����");
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
	 * �²����Ĳ���
	 */
	public void downInit() {

		addInfo.setText("����");
		addInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(addInfo);
		modifyInfo.setText("�޸�");
		modifyInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(modifyInfo);
		deleteInfo.setText("ɾ��");
		deleteInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(deleteInfo);
		clearInfo.setText("���");
		clearInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(clearInfo);
		eixtInfo.setText("�˳�");
		eixtInfo.setFont(new Font("Dialog", 0, 12));
		downPanel.add(eixtInfo);

		contentPane.add(downPanel, BorderLayout.SOUTH);

		// ����¼�����
		addInfo.addActionListener(this);
		modifyInfo.addActionListener(this);
		deleteInfo.addActionListener(this);
		clearInfo.addActionListener(this);
		eixtInfo.addActionListener(this);

		addInfo.setEnabled(true);
		modifyInfo.setEnabled(true);
		deleteInfo.setEnabled(true);
		clearInfo.setEnabled(true);
	}

	/**
	 * �¼�����
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == addInfo) {
			try {
				// ����
				Connection connection = ConnectionFactory.getInstance().makeConnection();
				PersonDaoMpl personDaoMpl = new PersonDaoMpl();
				Person person=new Person(jTextField1.getText(), jTextField2.getText(),jTextField3.getText(), jTextField4.getText(),jTextField5.getText());
				personDaoMpl.save(connection, person);
				JOptionPane.showMessageDialog(null, "��Ա��Ϣ��ӳɹ���", "��Ա��Ϣ������", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "���ݿ���ʴ��󣬴���ԭ��" + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();

		} else if (obj == modifyInfo) {
			try {
				// �޸�
				Connection connection = ConnectionFactory.getInstance().makeConnection();
				PersonDaoMpl personDaoMpl = new PersonDaoMpl();
				Person person=new Person(jTextField1.getText(), jTextField2.getText(),jTextField3.getText(), jTextField4.getText(),jTextField5.getText());		
				personDaoMpl.update(connection, person);
		
				JOptionPane.showMessageDialog(null, "��Ա��Ϣ�޸ĳɹ���", "��Ա��Ϣ�޸�", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "���ݿ���ʴ��󣬴���ԭ��" + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();

		} else if (obj == deleteInfo) {
			try {
				// ɾ��
				Connection connection = ConnectionFactory.getInstance().makeConnection();
				PersonDaoMpl personDaoMpl = new PersonDaoMpl();
				Person person=new Person(jTextField1.getText(), jTextField2.getText(),jTextField3.getText(), jTextField4.getText(),jTextField5.getText());
				personDaoMpl.delete(connection, person);
				JOptionPane.showMessageDialog(null, "��Ա��Ϣɾ���ɹ���", "��Ա��Ϣɾ��", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "���ݿ���ʴ��󣬴���ԭ��" + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		} else if (obj == clearInfo) { // ���
			setNull();
		} else if (obj == eixtInfo) { // �˳�
			this.dispose();
		}
		jTable.revalidate();
	}

	/**
	 * ���ı������
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
