
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
	public MyFrame() {
		Container c = getContentPane();
		JPanel panel1 = new JPanel(new GridLayout(1, 5));
		// add the main buttons
		JButton jload = new JButton("LOAD");
		JButton jrun = new JButton("RUN");
		JButton jinterrupt = new JButton("INTERRUPT");
		JButton jclear = new JButton("CLEAR");
		JButton jquit = new JButton("QUIT");

		panel1.add(jload);
		panel1.add(jrun);
		panel1.add(jinterrupt);
		panel1.add(jclear);
		panel1.add(jquit);

		add(panel1, BorderLayout.NORTH);

		// setting the 9 squares (3*3)
		Grids g = new Grids();
		g.setLayout(new GridLayout(3, 3, 10, 10));
		add(g, BorderLayout.CENTER);

		JTextArea display = new JTextArea(1, 20);
		display.setEditable(false);
		display.setPreferredSize(new Dimension(100, 100));
		display.setSize(600, 500);
		display.setBackground(Color.PINK);
		add(display, BorderLayout.SOUTH);
		
		
		//用于记录输入的数
	    int puzzleNum[]=new int[81];
		

		/*
		 * A method to load a specific file cover all cells to ensure every figure cover
		 * right cell, use the number of sequence to divide 9 *
		 */
		jload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loadst = "load begin";
				display.setText(loadst);
				List<String> list = Solve.load();
				String loadin = display.getText() + "\r\n" + "load in...";
				display.setText(loadin);
				
				//做了修改
				char[] ch = null;
				for (int index = 0; index < 81; index++) {
					int r = index / 9;
					int c = index % 9;
					ch = list.get(r).toCharArray();
					JPanel jPanel = (JPanel) g.getComponent((r / 3) * 3 + c / 3);
					Object o = jPanel.getComponent(c % 3 + (r % 3) * 3);
					String text = String.valueOf(ch[c]);
					if (!"_".equals(text)) {
						((JTextField) o).setText(text);
					}
				}
				
				
				//新添加的
				int numIndex=0;
				for (int index = 0; index < 81; index++) {
					int r = index / 9;
					int c = index % 9;
					ch = list.get(r).toCharArray();
					String text = String.valueOf(ch[c]);
					if (text.equals("_")) {
						puzzleNum[numIndex]=0;
						numIndex++;
					}
					else {
						puzzleNum[numIndex]=Integer.parseInt(text);
						numIndex++;
					}
				}
				
				String end = display.getText() + "\r\n" + "load over....";
				display.setText(end);
			}
		});

		jrun.addActionListener(new ActionListener() {
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			String[][] candidate = new String[9][9];

			public void actionPerformed(ActionEvent e) {
				String runb = display.getText() + "\r\n" + "start run begin";
				display.setText(runb);
				for (int i = 0; i < 9; i++) {
					list.add(new ArrayList());
				}
				for (int index = 0; index < 81; index++) {
					int r = index / 9;
					int c = index % 9;
					JPanel jPanel = (JPanel) g.getComponent((r / 3) * 3 + c / 3);
					Object o = jPanel.getComponent(c % 3 + (r % 3) * 3);
					String text = ((JTextField) o).getText();
					if ("".equals(text)) {
						list.get(r).add(0);
					} else {
						list.get(r).add(Integer.valueOf(text));
					}
				}
				String rune = display.getText() + "\r\n" + "run end";
				display.setText(rune);
				
				
				//用于求解
				String str=""; //puzzleNum.toString();
				for(int i=0;i<81;i++) {
					str=str+String.valueOf(puzzleNum[i]);
				}
				int result[]=new int[81];
				result=new Solver(new Sudoku().StrToForm(str)).getResult();
				int resultIndex=0;
				for (int index = 0; index < 81; index++) {
					int r = index / 9;
					int c = index % 9;
					JPanel jPanel = (JPanel) g.getComponent((r / 3) * 3 + c / 3);
					Object o = jPanel.getComponent(c % 3 + (r % 3) * 3);
					((JTextField) o).setText(result[resultIndex++]+"");
				}
			}

//			private void solve() {
//				int in = 0;
//				while (in < 15) {
//					for (int r = 0; r < 9; r++) {
//						for (int c = 0; c < 9; c++) {
//							int s = (r / 3) * 3 + c / 3;
//							if (list.get(r).get(c) == 0) {
//								List<Integer> l = new ArrayList();
//								for (int i = 0; i < 9; i++) {
//									if ((int) list.get(r).get(i) != 0 && !l.contains(list.get(r).get(i))) {
//										l.add(list.get(r).get(i));
//									}
//									if ((int) list.get(i).get(c) != 0 && !l.contains(list.get(i).get(c))) {
//										l.add(list.get(i).get(c));
//									}
//									int rs = s / 3 * 3 + i / 3;
//									int cs = s % 3 * 3 + i % 3;
//									if ((int) list.get(rs).get(cs) != 0 && !l.contains(list.get(rs).get(cs))) {
//										l.add(list.get(rs).get(cs));
//									}
//								}
//								String text = "";
//								for (int v = 1; v <= 9; v++) {
//									if (!l.contains(v)) {
//										text += v;
//									}
//								}
//								if ("".equals(text)) {
//									System.out.println();
//								}
//								if (text.length() == 1) {
//									JPanel jPanel = (JPanel) g.getComponent((r / 3) * 3 + c / 3);
//									Object o = jPanel.getComponent(c % 3 + (r % 3) * 3);
//									((JTextField) o).setText(text);
//									((JTextField) o).setBackground(Color.yellow);
//									list.get(r).set(c, Integer.valueOf(text));
//								} else {
//									candidate[r][c] = text;
//									JPanel jPanel = (JPanel) g.getComponent((r / 3) * 3 + c / 3);
//									Object o = jPanel.getComponent(c % 3 + (r % 3) * 3);
//									((JTextField) o).setText(text);
//								}
//							}
//						}
//					}
//					// rule2
//					for (int r = 0; r < 9; r++) {
//						Map<Integer, Integer> m = new HashMap();
//						for (int i = 1; i <= 9; i++) {
//							m.put(i, 0);
//						}
//						for (int c = 0; c < 9; c++) {
//							if (candidate[r][c] != null) {
//								String[] s = candidate[r][c].split("");
//								if (s.length > 0) {
//									for (int i = 0; i < s.length; i++) {
//										if (!"".equals(s[i])) {
//											Integer v = Integer.valueOf(s[i]);
//											m.put(v, m.get(v) + 1);
//										}
//									}
//								}
//							}
//						}
//						// candidate show once
//						for (Integer key : m.keySet()) {
//							if (m.get(key) == 1) {
//								for (int j = 0; j < 9; j++) {
//									if (candidate[r][j] != null && candidate[r][j].contains(String.valueOf(key))) {
//										candidate[r][j] = null;
//										JPanel jPanel = (JPanel) g.getComponent((r / 3) * 3 + j / 3);
//										Object o = jPanel.getComponent(j % 3 + (r % 3) * 3);
//										((JTextField) o).setText(String.valueOf(key));
//										((JTextField) o).setBackground(Color.yellow);
//										list.get(r).set(j, key);
//										break;
//									}
//								}
//							}
//						}
//
//					}
//
//					for (int c = 0; c < 9; c++) {
//						Map<Integer, Integer> m = new HashMap();
//						for (int i = 1; i <= 9; i++) {
//							m.put(i, 0);
//						}
//						for (int r = 0; r < 9; r++) {
//							if (candidate[c][r] != null) {
//								String[] s = candidate[c][r].split("");
//								if (s.length > 0) {
//									for (int i = 0; i < s.length; i++) {
//										if (!"".equals(s[i])) {
//											Integer v = Integer.valueOf(s[i]);
//											m.put(v, m.get(v) + 1);
//										}
//									}
//								}
//							}
//						}
//						// candidate show once
//						for (Integer key : m.keySet()) {
//							if (m.get(key) == 1) {
//								for (int j = 0; j < 9; j++) {
//									if (candidate[j][c] != null && candidate[j][c].contains(String.valueOf(key))) {
//										candidate[j][c] = null;
//										JPanel jPanel = (JPanel) g.getComponent((j / 3) * 3 + c / 3);
//										Object o = jPanel.getComponent(c % 3 + (j % 3) * 3);
//										((JTextField) o).setText(String.valueOf(key));
//										((JTextField) o).setBackground(Color.yellow);
//										list.get(j).set(c, key);
//										break;
//									}
//								}
//							}
//						}
//
//					}
//					in++;
//				}
//
//			}
		});

		interruptListenerClass listener3 = new interruptListenerClass();
		jinterrupt.addActionListener(listener3);

		// A method to clean all values of cells
		jclear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int index = 0; index < 81; index++) {
					int r = index / 9;
					int c = index % 9;
					JPanel jPanel = (JPanel) g.getComponent((r / 3) * 3 + c / 3);
					Object o = jPanel.getComponent(c % 3 + (r % 3) * 3);
					((JTextField) o).setText("");
					((JTextField) o).setBackground(Color.WHITE);
				}
				String text = display.getText() + "\r\n" + "clear over";
				display.setText(text);
			}
		});

		quitListenerClass listener5 = new quitListenerClass();
		jquit.addActionListener(listener5);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
		JFrame frame = new MyFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		frame.setBounds((int) width / 4, (int) height / 4, (int) width / 2, (int) height / 2);
		frame.setTitle("SuDoku");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class interruptListenerClass implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		Thread myThread = new Thread(new Runnable() {
			   public void run() {
			   }
			});
		int sleepTime = 8;
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}

class clearListenerClass implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	}
}

class quitListenerClass implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}

class Grids extends JPanel {
	/*
	 * initializing the small square, setting 9 cells(3*3) to every square, and
	 * setting distance between different cells setting 81 cells
	 */
	public Grids() {

		for (int i = 0; i < 9; i++) {
			JPanel jPanel = new JPanel(new GridLayout(3, 3, 2, 2));
			for (int j = 0; j < 9; j++) {
				JTextField jt = new JTextField("");
				jPanel.add(jt);

			}
			add(jPanel);
		}

	}
}
