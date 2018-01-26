/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.shuangze.assetsms.view;

import cn.shuangze.assetsms.dao.AssetTypeDaoMpl;
import cn.shuangze.assetsms.dao.AssetsTypeDao;
import cn.shuangze.assetsms.dao.ConnectionFactory;
import cn.shuangze.assetsms.dao.PersonDaoMpl;
import cn.shuangze.assetsms.entity.AssetsType;
import cn.shuangze.assetsms.entity.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Jinyu
 */
/**
 * �ʲ������Ϣ�ۺϹ�����
 */
public class TypeInfo extends JFrame implements ActionListener, ListSelectionListener {

	
	  private DefaultTableModel tableModel;   //����ģ�Ͷ���
	
    AssetsTypeDao assetsTypeDao;
    Container contentPane;
    //�������õ����
    JPanel upPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel downPanel = new JPanel();

    //��ܵĴ�С
    Dimension faceSize = new Dimension(400, 400);

    //����ͼ�ν���Ԫ��
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();

    JTextField jTextField1 = new JTextField(15);
    JTextField jTextField2 = new JTextField(15);
    JTextField jTextField3 = new JTextField(15);

    JButton addInfo = new JButton();
    JButton modifyInfo = new JButton();
    JButton deleteInfo = new JButton();
    JButton clearInfo = new JButton();
    JButton saveInfo = new JButton();
    JButton eixtInfo = new JButton();

    //�������
    JScrollPane jScrollPane1;
    JTable jTable;
    ListSelectionModel listSelectionModel = null;
    String[] colName = {"�ʲ������", "�ʲ�����", "�ʲ�С��"};
   

    GridBagLayout girdBag = new GridBagLayout();
    GridBagConstraints girdBagCon;

    public TypeInfo() {
        assetsTypeDao = new AssetTypeDaoMpl();
        //���ÿ�ܵĴ�С
        this.setSize(faceSize);
        //���ñ���
        this.setTitle("�ʲ�������");
        this.setResizable(true);

        //��������ʱ���ڵ�λ��
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - 400) / 2,
                (screenSize.height - 300) / 2 + 45);
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
		
		AssetTypeDaoMpl assetTypeDaoMpl=new AssetTypeDaoMpl();
		
		ResultSet rs =assetTypeDaoMpl.getInfo(connection);
		
		
		ArrayList<AssetsType> assetsTypes = new ArrayList();
		while (rs.next()) {
			AssetsType assetsType=new AssetsType(rs.getInt("typeid"),rs.getString("b_type"),rs.getString("s_type"));
			assetsTypes.add(assetsType);
		}

		    String[][] colValue= {};;
		
			tableModel = new DefaultTableModel(colValue,colName);
			for(int i=0;i<assetsTypes.size();i++) {
				String[] ss=new String[]{assetsTypes.get(i).getTypeID()+"",assetsTypes.get(i).getBigType(),assetsTypes.get(i).getSmallType()};
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

        jLabel1.setText("���");
        jLabel1.setFont(new Font("Dialog", 0, 12));
        centerPanel.add(jLabel1);
        centerPanel.add(jTextField1);

        jLabel2.setText("����");
        jLabel2.setFont(new Font("Dialog", 0, 12));
        centerPanel.add(jLabel2);
        centerPanel.add(jTextField2);

        jLabel3.setText("С��");
        jLabel3.setFont(new Font("Dialog", 0, 12));
        centerPanel.add(jLabel3);
        centerPanel.add(jTextField3);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        jTextField1.setEditable(false);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
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

        //�����¼�����
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
                //����
            	Connection connection = ConnectionFactory.getInstance().makeConnection();            	
                AssetTypeDaoMpl assetTypeDaoMpl=new AssetTypeDaoMpl(); 
            	AssetsType assetsType=new AssetsType(jTextField2.getText(), jTextField3.getText());
            	assetTypeDaoMpl.save(connection, assetsType);
                JOptionPane.showMessageDialog(null, "�ʲ�������ӳɹ���", "�ʲ��������", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "���ݿ���ʴ��󣬴���ԭ��" + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        } else if (obj == modifyInfo) {
            try {
            	Connection connection = ConnectionFactory.getInstance().makeConnection();            	
                AssetTypeDaoMpl assetTypeDaoMpl=new AssetTypeDaoMpl(); 
            	AssetsType assetsType=new AssetsType(jTextField2.getText(), jTextField3.getText());
            	assetTypeDaoMpl.update(connection, assetsType);
                JOptionPane.showMessageDialog(null, "�ʲ�����޸ĳɹ���", "�ʲ�����޸�", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "���ݿ���ʴ��󣬴���ԭ��" + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();

        } else if (obj == deleteInfo) {
            try {
            	Connection connection = ConnectionFactory.getInstance().makeConnection();            	
                AssetTypeDaoMpl assetTypeDaoMpl=new AssetTypeDaoMpl(); 
            	AssetsType assetsType=new AssetsType(jTextField2.getText(), jTextField3.getText());
            	assetTypeDaoMpl.delete(connection, assetsType);
                JOptionPane.showMessageDialog(null, "�ʲ����ɾ���ɹ���", "�ʲ����ɾ��", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "���ݿ���ʴ��󣬴���ԭ��" + ex.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
            }
            this.dispose();
        } else if (obj == clearInfo) { //���
            setNull();
        } else if (obj == eixtInfo) { //�˳�
            this.dispose();
        }
        jTable.revalidate();
    }

   
    void setNull() {
        jTextField1.setText(null);
        jTextField2.setText(null);
        jTextField3.setText(null);
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        addInfo.setEnabled(true);
        modifyInfo.setEnabled(true);
        deleteInfo.setEnabled(true);
        clearInfo.setEnabled(true);
    }

    /**
     * ������ѡ��ʱ�Ĳ���
     */
    public void valueChanged(ListSelectionEvent lse) {
        int selectedRow = jTable.getSelectedRow();
        jTextField1.setText((String) jTable.getValueAt(selectedRow, 0));
        jTextField2.setText((String) jTable.getValueAt(selectedRow, 1));
        jTextField3.setText((String) jTable.getValueAt(selectedRow, 2));
        //�����Ƿ�ɲ���
        jTextField2.setEditable(true);
        jTextField3.setEditable(true);

        addInfo.setEnabled(true);
        modifyInfo.setEnabled(true);
        deleteInfo.setEnabled(true);
        clearInfo.setEnabled(true);
    }

}
