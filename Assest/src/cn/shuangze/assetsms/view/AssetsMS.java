/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.shuangze.assetsms.view;

/**
 *
 * @author Jinyu
 */
import javax.swing.UIManager;
import java.awt.*;

/**
 * �ʲ�����ϵͳ��������
 */
public class AssetsMS {
	boolean packFrame = false;

	/**
	 * ���캯��
	 */
	public AssetsMS() {
		AssetsMain frame = new AssetsMain();

		if (packFrame) {
			frame.pack();
		}
		else {
			frame.validate();
		}

		//��������ʱ���ڵ�λ��
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		//�������з��
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		new AssetsMS();
	}
}