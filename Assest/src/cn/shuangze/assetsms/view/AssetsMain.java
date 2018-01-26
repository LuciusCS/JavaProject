/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.shuangze.assetsms.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

/**
 *
 */
public class AssetsMain extends JFrame implements ActionListener{
	//框架的大小
	Dimension faceSize = new Dimension(1024, 600);
	//程序图标
	Image icon;

	//建立菜单栏
	JMenuBar mainMenu = new JMenuBar();
	//建立“系统管理”菜单组
	JMenu menuSystem=new JMenu();
	JMenuItem itemTypeMan=new JMenuItem();
	JMenuItem itemExit=new JMenuItem();
	//建立“资产信息管理”菜单组
	JMenu menuAssets=new JMenu();
	JMenuItem itemAddAssets=new JMenuItem();
	JMenuItem itemModifyAssets=new JMenuItem();
	JMenuItem itemDeleteAssets=new JMenuItem();
	JMenu itemSelectAssets=new JMenu();
	//用于查看所有资产
	JMenuItem itemSelectAssetsAll = new JMenuItem();
	//建立“人员信息管理”菜单组
	JMenu menuPerson=new JMenu();
	JMenuItem itemAddPerson=new JMenuItem();
	JMenuItem itemModifyPerson=new JMenuItem();
	JMenuItem itemDeletePerson=new JMenuItem();
	JMenu itemSelectPerson=new JMenu();
	JMenuItem itemSelectPersonAll = new JMenuItem();
	//JMenuItem itemSelectPersonID=new JMenuItem();
	//建立“资产领用”菜单组
	JMenu menuUsing=new JMenu();
	//用于资产领用
	JMenuItem itemUsing=new JMenuItem();
	//用于资产归还
	JMenuItem itemSelectUsing=new JMenuItem();
	//建立“资产归还”菜单组
	JMenu menuBack=new JMenu();
	
	JMenuItem itemBack=new JMenuItem();
	//JMenuItem itemSelectBack=new JMenuItem();
	//建立“资产报废”菜单组
	JMenu menuInvalid=new JMenu();
	JMenuItem itemInvalid=new JMenuItem();
	JMenuItem itemSelectInvalid=new JMenuItem();

	/**
	 * 程序初始化函数
	 */
	 public AssetsMain() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		//添加框架的关闭事件处理
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		//设置框架的大小
		this.setSize(faceSize);
		//设置标题
		this.setTitle("资产管理系统");
		
		try {
			Init();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 程序初始化函数
	 */
	private void Init() throws Exception {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());

		//添加菜单组
		menuSystem.setText("系统管理");
		menuSystem.setFont(new Font("Dialog", 0, 12));
		menuAssets.setText("资产信息管理");
		menuAssets.setFont(new Font("Dialog", 0, 12));
		menuPerson.setText("人员信息管理");
		menuPerson.setFont(new Font("Dialog", 0, 12));
		menuUsing.setText("资产领用") ;
		menuUsing.setFont(new Font("Dialog", 0, 12));
		menuBack.setText("资产归还");
		menuBack.setFont(new Font("Dialog", 0, 12));
		menuInvalid.setText("资产报废");
		menuInvalid.setFont(new Font("Dialog", 0, 12));


		//生成“系统管理”菜单组的选项
		itemTypeMan.setText("类别管理");
		itemTypeMan.setFont(new Font("Dialog",0,12));
		itemExit.setText("退出");
		itemExit.setFont(new Font("Dialog",0,12));
		//生成“资产信息管理”菜单组的选项
		itemAddAssets.setText("资产管理");
		itemAddAssets.setFont(new Font("Dialog",0,12));
//		itemModifyAssets.setText("修改");
//		itemModifyAssets.setFont(new Font("Dialog",0,12));
//		itemDeleteAssets.setText("删除");
//		itemDeleteAssets.setFont(new Font("Dialog",0,12));
		itemSelectAssets.setText("查询");
		itemSelectAssets.setFont(new Font("Dialog",0,12));
		itemSelectAssetsAll.setText("查询所有");
		itemSelectAssetsAll.setFont(new Font("Dialog",0,12));
//		itemSelectAssetsID.setText("按编号查询");
//		itemSelectAssetsID.setFont(new Font("Dialog",0,12));
		//生成“人员信息管理”菜单组的选项
		itemAddPerson.setText("人员管理");
		itemAddPerson.setFont(new Font("Dialog",0,12));
//		itemModifyPerson.setText("人员信息修改");
//		itemModifyPerson.setFont(new Font("Dialog",0,12));
//		itemDeletePerson.setText("人员信息删除");
//		itemDeletePerson.setFont(new Font("Dialog",0,12));
		itemSelectPerson.setText("查询人员信息");
		itemSelectPerson.setFont(new Font("Dialog",0,12));
		itemSelectPersonAll.setText("查询所有");
		itemSelectPersonAll.setFont(new Font("Dialog",0,12));
//		itemSelectPersonID.setText("按编号查询");
//		itemSelectPersonID.setFont(new Font("Dialog",0,12));
		//生成“资产领用”菜单组的选项
		itemUsing.setText("资产领用");
		itemUsing.setFont(new Font("Dialog",0,12));
		itemSelectUsing.setText("已领用资产管理");
		itemSelectUsing.setFont(new Font("Dialog",0,12));
		//生成“资产归还”菜单组的选项
		itemBack.setText("资产归还管理");
		itemBack.setFont(new Font("Dialog",0,12));
//		itemSelectBack.setText("归还信息查询");
//		itemSelectBack.setFont(new Font("Dialog",0,12));
		//生成“资产报废”菜单组的选项
		itemInvalid.setText("资产报废管理");
		itemInvalid.setFont(new Font("Dialog",0,12));
		itemSelectInvalid.setText("报废信息查询");
		itemSelectInvalid.setFont(new Font("Dialog",0,12));

		//添加“系统管理”菜单组
		menuSystem.add(itemTypeMan);
		menuSystem.add(itemExit);
		//添加“资产信息管理”菜单组
		menuAssets.add(itemAddAssets);
//		menuAssets.add(itemModifyAssets);
//		menuAssets.add(itemDeleteAssets);
		menuAssets.addSeparator();
		menuAssets.add(itemSelectAssets);
		itemSelectAssets.add(itemSelectAssetsAll);
//		itemSelectAssets.add(itemSelectAssetsID);
		//添加“人员信息管理”菜单组
		menuPerson.add(itemAddPerson);
//		menuPerson.add(itemModifyPerson);
//		menuPerson.add(itemDeletePerson);
		menuPerson.addSeparator();
		menuPerson.add(itemSelectPerson);
		itemSelectPerson.add(itemSelectPersonAll);
		//itemSelectPerson.add(itemSelectPersonID);
		//添加“资产领用”菜单组
		menuUsing.add(itemUsing);
		menuUsing.add(itemSelectUsing);
		//添加“资产归还”菜单组
		menuBack.add(itemBack);
	//	menuBack.add(itemSelectBack);
		//添加“资产报废”菜单组
		menuInvalid.add(itemInvalid);
		menuInvalid.add(itemSelectInvalid);

		//添加所有的菜单组
		mainMenu.add(menuSystem);
		mainMenu.add(menuAssets);
		mainMenu.add(menuPerson);
		mainMenu.add(menuUsing);
		mainMenu.add(menuBack);
		mainMenu.add(menuInvalid);
		this.setJMenuBar(mainMenu);

		//添加事件侦听
		itemTypeMan.addActionListener(this);
		itemExit.addActionListener(this);
		itemAddAssets.addActionListener(this);
//		itemModifyAssets.addActionListener(this);
//		itemDeleteAssets.addActionListener(this);
		itemSelectAssetsAll.addActionListener(this);
//		itemSelectAssetsID.addActionListener(this);
		itemAddPerson.addActionListener(this);
//		itemModifyPerson.addActionListener(this);
//		itemDeletePerson.addActionListener(this);
		itemSelectPersonAll.addActionListener(this);
		//itemSelectPersonID.addActionListener(this);
		itemUsing.addActionListener(this);
		itemSelectUsing.addActionListener(this);
		itemBack.addActionListener(this);
	//	itemSelectBack.addActionListener(this);
		itemInvalid.addActionListener(this);
		itemSelectInvalid.addActionListener(this);


		//关闭程序时的操作
		this.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			}
		);
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == itemExit) { //退出
			System.exit(0);
		}
		else if (obj == itemTypeMan) { //资产类别管理
			TypeInfo typeMan = new TypeInfo();
		 	typeMan.downInit();
	     	typeMan.pack();
	        typeMan.setVisible(true);
		}
		else if (obj==itemAddPerson) {
			PersonView typeMan = new PersonView();
		 	typeMan.downInit();
	     	typeMan.pack();
	        typeMan.setVisible(true);
		}else if (obj==itemAddAssets) {
			AssetsView assetsView=new AssetsView();
			assetsView.downInit();
			assetsView.pack();
			assetsView.setVisible(true);
		}else if (obj==itemSelectAssetsAll) {
			//用于查看所有资产
			AssetsShowAllView assetsShowAllView=new AssetsShowAllView();
			assetsShowAllView.downInit();
			assetsShowAllView.pack();
			assetsShowAllView.setVisible(true);
		}
		else if (obj==itemBack) {
			//用于已归还资产查看
			AssetReturnedView assetReturnView=new AssetReturnedView();
			assetReturnView.downInit();
			assetReturnView.pack();
			assetReturnView.setVisible(true);	
		}else if (obj==itemSelectPersonAll) {
			//查询所有人员信息;
			PersonAllView personAllView=new PersonAllView();
			personAllView.downInit();
			personAllView.pack();
			personAllView.setVisible(true);
		}
		
		
		else if (obj==itemUsing) {
			//用于资产领用
			AssetsAllowBorrowView assetsAllowBorrowView=new AssetsAllowBorrowView();
			assetsAllowBorrowView.downInit();
			assetsAllowBorrowView.pack();
			assetsAllowBorrowView.setVisible(true);
		}else if(obj==itemSelectUsing){
			//用于资产归还（已经领用的资产）
			AssetsBorrowed assetsBorrowed=new AssetsBorrowed();
			assetsBorrowed.downInit();
			assetsBorrowed.pack();
			assetsBorrowed.setVisible(true);
			
		}else if (obj==itemSelectInvalid) {
			AssetRuinedView assetRuinedView=new AssetRuinedView();
			assetRuinedView.downInit();
			assetRuinedView.pack();
			assetRuinedView.setVisible(true);
		}else if (obj==itemInvalid) {
			AssetAllowRuinedView allowRuined=new AssetAllowRuinedView();
			allowRuined.downInit();
			allowRuined.pack();
			allowRuined.setVisible(true);
			
		}
		
		
		
//		itemInvalid.setText("资产报废管理");
//		itemInvalid.setFont(new Font("Dialog",0,12));
//		itemSelectInvalid.setText("报废信息查询");
//		JMenuItem itemInvalid=new JMenuItem();
//		JMenuItem itemSelectInvalid=new JMenuItem();
		
//		
//		JMenuItem itemUsing=new JMenuItem();
//	
//		JMenuItem itemSelectUsing=new JMenuItem();
		
	}

	
}

