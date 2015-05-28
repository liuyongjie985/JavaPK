package UI;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;

import javax.swing.JTextField;

import java.awt.CardLayout;

import javax.swing.JCheckBox;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import factory.HibernateSessionFactory;
import Entity.*;
import Logic.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.util.Timer;
import java.util.TimerTask;

public class MainWindowUI implements ActionListener, SystemObserver {

	private JFrame frame;
	private JTable table;
	JPanel panel;
	private JTextField textField;
	String path;
	JButton ButtonArray[] = new JButton[9];
	JComboBox box;
	JButton add;
	JButton delete;
	JButton search;
	JButton modify;
	Object[][] ob;
	String[] str = new String[2];
	JTextField text[] = new JTextField[2];
	RemindTest remind[];
	RemindCourse remind2[];
	RemindEveryday remind3;
	JCheckBox check;

	int Sign = 0;
	private int array[] = new int[9];// indicate the state of the room

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowUI window = new MainWindowUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindowUI() {
		MessageControler.getInstance().registerObserver(this);
		path = System.getProperty("user.dir");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 906, 381);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 870, 323);
		frame.getContentPane().add(tabbedPane);

		JPanel Test = new JPanel();
		tabbedPane.addTab("考试信息", null, Test, null);
		Test.setLayout(null);

		JPanel Course = new JPanel();
		tabbedPane.addTab("课程信息", null, Course, null);
		Course.setLayout(null);

		panel = new JPanel();
		panel.setBounds(125, 0, 740, 296);
		Course.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));// 风化边界

		check = new JCheckBox("每日提醒");
		check.setBounds(23, 30, 85, 23);
		Course.add(check);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 845, 220);
		Test.add(scrollPane);

		table = new JTable();

		scrollPane.setViewportView(table);

		add = new JButton("+");
		add.addActionListener(this);
		add.setBounds(10, 23, 105, 23);
		Test.add(add);

		delete = new JButton("-");
		delete.setBounds(139, 23, 93, 23);
		Test.add(delete);
		delete.addActionListener(this);

		search = new JButton("查询");
		search.setBounds(762, 23, 93, 23);
		Test.add(search);
		search.addActionListener(this);

		box = new JComboBox();
		box.setBounds(508, 24, 59, 21);
		box.setModel(new DefaultComboBoxModel(new String[] { "全部", "考试名",
				"考试时间", "考试地点", "是否提醒", "备注", "考试编号" }));
		Test.add(box);

		textField = new JTextField();
		textField.setBounds(577, 24, 175, 21);
		Test.add(textField);
		textField.setColumns(10);
		text[0] = textField;

		modify = new JButton("修改");

		modify.setBounds(260, 23, 93, 23);
		Test.add(modify);
		modify.addActionListener(this);

		for (int i = 0; i < 1; i++) {
			text[i].getDocument().addDocumentListener(new TextLis(i));
			str[i] = text[i].getText();
		}

		update();

		// 每天提醒线程开启

		remind3 = new RemindEveryday(10000);
		remind3.setCheck(check);
		Sign = 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == add) {
			AddTestUI addTest = new AddTestUI(0);
			addTest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			addTest.setModal(true);
			addTest.setVisible(true);
		}
		if (e.getSource() == delete) {
			Object[] options = { "是！(大声地)", "让我再想想" };
			int n = JOptionPane.showOptionDialog(null, "大声告诉我你是不是真的要删除", "？",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
			if (n == JOptionPane.YES_OPTION) {
				// ......
				int row = table.getSelectedRow();
				if (row != -1) {
					int id = Integer.parseInt(ob[row][5].toString());
					// 确定删除吗对话框

					// 确定要删除
					// res is impossible to be null
					// Reservation res =
					// BookingSystem.getInstance().getHotel().getInstance()
					// .getCompleteReservationByRoomNum(roomnum);
					HibernateSessionFactory.getSession().clear();
					try {
						MessageControler.getTes().getAcc().getTesD()
								.delete(new Testrecord(id));
						JOptionPane.showMessageDialog(null, "删除成功", "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
						MessageControler.getInstance().notifyObserver();
					} catch (RuntimeException re) {
						throw re;
						// System.out.printf(re);
					}

				} else {
					// System.out.print("请选中要删除的订单！");
					JOptionPane.showMessageDialog(null, "请选中要删除的考试！", "系统信息",
							JOptionPane.ERROR_MESSAGE);
				}
			} else if (n == JOptionPane.NO_OPTION) {
				// ......
			}
		}

		if (e.getSource() == modify) {
			int row = table.getSelectedRow();
			if (row != -1) {
				int id = Integer.parseInt(ob[row][5].toString());
				AddTestUI addTest = new AddTestUI(id);
				addTest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				addTest.setModal(true);
				addTest.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(null, "请选中要修改的考试！", "系统信息",
						JOptionPane.ERROR_MESSAGE);
			}

		}

		if (e.getSource() == search) {
			int state = box.getSelectedIndex();
			int count = ob.length;
			List<Object[]> list = new ArrayList<Object[]>();
			switch (state) {
			// "全部", "考试名",
			// "考试时间", "考试地点", "是否提醒", "备注", "考试编号"

			case 1:
				search(list, count, 0);

				break;
			case 2:
				search(list, count, 1);
				break;
			case 3:
				search(list, count, 2);
				break;
			case 4:
				search(list, count, 3);
				break;
			case 5:
				search(list, count, 4);
				break;
			case 6:
				search(list, count, 5);
				break;
			case 7:
				search(list, count, 6);
				break;
			case 8:
				search(list, count, 7);
				break;
			case 9:
				search(list, count, 8);
				break;
			default:// 0 is on the case
				update();
				break;
			}
		}

		if (e.getSource() == ButtonArray[0]) {
			AddCourseUI courseui = new AddCourseUI(1);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[1]) {
			AddCourseUI courseui = new AddCourseUI(2);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[2]) {
			AddCourseUI courseui = new AddCourseUI(3);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[3]) {
			AddCourseUI courseui = new AddCourseUI(4);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[4]) {
			AddCourseUI courseui = new AddCourseUI(5);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[5]) {
			AddCourseUI courseui = new AddCourseUI(6);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[6]) {
			AddCourseUI courseui = new AddCourseUI(7);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[7]) {
			AddCourseUI courseui = new AddCourseUI(8);
			courseui.setModal(true);
			courseui.setVisible(true);
		}
		if (e.getSource() == ButtonArray[8]) {
			AddCourseUI courseui = new AddCourseUI(9);
			courseui.setModal(true);
			courseui.setVisible(true);
		}

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		// 考试面板的更新
		makeTestTimeStop();
		ob = getOB();
		setTableModel(ob);
		makeTestTimeStart();

		// 课程面板的更新
		makeCourseTimeStop();
		array = MessageControler.getCou().getCourseState(array);
		// 将有课的按钮状态变为1

		ButtonInto(ButtonArray, array, 9, panel, 0);
		makeCourseTimeStart();
	}

	void makeCourseTimeStop() {

		if (Sign != 0) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < 9; i++) {
				if (array[i] == 1) {
					list.add(i);
				}
			}

			for (int i = 0; i < list.size(); i++) {
				remind2[i].getT().stop();
			}
		}
	}

	void makeCourseTimeStart() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			if (array[i] == 1) {
				list.add(i);
			}
		}
		remind2 = new RemindCourse[list.size()];
		for (int i = 0; i < list.size(); i++) {
			remind2[i] = new RemindCourse(list.get(i), 10000);
		}
	}

	// 将button加入面板
	public void ButtonInto(JButton[] array_B, int[] array_I, int number,
			JPanel panel, int offset) {
		// JButton PR[] = new JButton[number_president];
		// array_B = new JButton[number];

		for (int i = 0; i < number; i++) {
			if (Sign != 0) {
				panel.remove(array_B[i]);
			}
			array_B[i] = new JButton();
			array_B[i].setContentAreaFilled(false);
			array_B[i].setBorderPainted(false);
			array_B[i].setText(i + offset + 1 + "号课程");
			array_B[i].setVerticalTextPosition(JButton.BOTTOM);
			array_B[i].setHorizontalTextPosition(JButton.CENTER);
			// 添加监听
			// array_B[i].addMouseListener(new MouseHandle(i + 1 + offset));
			array_B[i].addActionListener(this);
			// 实现拖动
			// array_B[i].addMouseMotionListener(new MouseMotionAdapter() {
			// public void mouseDragged(MouseEvent e) {
			// // Point p = getLocation();
			// // //针对整个屏幕的位置
			// // //System.out.println("左上角X坐标为：" + p.x);
			// // setLocation(p.x + e.getX() - origin.x, p.y + e.getY()
			// // - origin.y);
			//
			// // 得到具体的JButton
			// // 转换坐标系统
			// // Point newPoint =
			// // SwingUtilities.convertPoint(dragPicLabel, e
			// // .getPoint(), dragPicLabel.getParent());
			// // // 设置标签的新位置
			// // dragPicLabel.setLocation(dragPicLabel.getX()
			// // + (newPoint.x - origin.x), dragPicLabel.getY()
			// // + (newPoint.y - origin.y));
			// // // 更改坐标点
			// // origin = newPoint;
			//
			// }
			// });

			switch (array_I[i + offset]) {
			case 0:// be able to provide
				array_B[i].setIcon(new ImageIcon(path + "\\ima\\1.png"));
				break;
			case 1:// be booked
				array_B[i].setIcon(new ImageIcon(path + "\\ima\\1.jpg"));
				Courserecord course = MessageControler.getCou().getAcc()
						.getCouD().findById(i + 1);
				array_B[i].setText(course.getName());
				break;
			case 2:// be in
				array_B[i].setIcon(new ImageIcon(path + "\\ima\\pree.gif"));
				break;
			default:
				break;
			}

			panel.add(array_B[i]);

		}
	}

	public void makeTestTimeStop() {
		if (Sign != 0) {
			for (int i = 0; i < ob.length; i++) {
				remind[i].getT().stop();
			}
		}
	}

	public void makeTestTimeStart() {
		remind = new RemindTest[ob.length];
		for (int i = 0; i < ob.length; i++) {
			remind[i] = new RemindTest(i, 10000);
			remind[i].setOb(ob);
			remind[i].setTable(table);
		}
	}

	public void setTableModel(Object[][] ob) {
		DefaultTableModel m = new DefaultTableModel(ob, new String[] { "考试名",
				"考试时间", "考试地点", "是否提醒", "备注", "考试编号", "剩余时间" });
		TableRowSorter sorter = new TableRowSorter(m);
		table.setRowSorter(sorter);
		table.setModel(m);
	}

	private Object[][] getOB() {
		List list = new ArrayList();

		list = MessageControler.getTes().getAcc().getTesD().findAll();
		Object[][] ob = new Object[list.size()][7];
		for (int i = 0; i < list.size(); i++) {
			Testrecord test = (Testrecord) (list.get(i));

			// the number of offset is at least 0
			// int offset = room.size() - 1;
			// int j;
			// for (int j = 0; j < number; j++) {
			// // for ( int k = 0; k <= offset; k++)
			// {
			ob[i][0] = test.getName().toString();
			ob[i][1] = test.getTime();
			ob[i][2] = test.getLocation();
			ob[i][3] = getRemindType(test.getRemind());
			ob[i][4] = test.getRemark();
			ob[i][5] = test.getId() + "";
			// }
			// }
		}
		return ob;
	}

	// void timeSupplement(int index) {
	// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	// try {
	// String temp = df.format(new Date());
	//
	// java.util.Date now = df.parse(temp);
	//
	// // temp = new String(ob[index][1].toString());
	// // String[] str = temp.split("-");
	// // String newTime = str[0] + "-" + str[1] + "-" + str[2] +
	// // "-00-02-00";
	// // System.out.println("现在的时间是" + now.toString());
	// // System.out.println("新生成的时间是" + newTime);
	// // df = new SimpleDateFormat("yyyy-MM-DD-HH-mm-ss");
	// java.util.Date date = df.parse(ob[index][1].toString());
	// // System.out.println("得到的考试时间是：" + date.toString());
	//
	// // DateTime dateTime=new DateTime(2012, 12, 13, 18, 23,55);
	//
	// long l = date.getTime() - now.getTime();
	// long day = l / (24 * 60 * 60 * 1000);
	// long hour = (l / (60 * 60 * 1000) - day * 24);
	// long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
	// long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
	// String lasttime = "" + day + "天" + hour + "小时" + min + "分" + s
	// + "秒";
	// ob[index][6] = lasttime;
	// setTableModel(ob);
	//
	// // 提醒类型
	//
	// // 到时间则停
	//
	// } catch (Exception re) {
	// re.printStackTrace();
	// }
	//
	// }

	String getRemindType(int remind) {
		String str = null;
		switch (remind) {
		case 0:
			str = "无提醒";
			break;
		case 1:
			str = "当天7点";
			break;
		case 2:
			str = "提前1天";
			break;
		case 3:
			str = "提前5天";
			break;
		}
		return str;
	}

	public void search(List list, int count, int model) {
		for (int i = 0; i < count; i++) {
			if (str[0].equalsIgnoreCase(ob[i][model].toString())) {
				list.add(ob[i]);
			}
		}
		Object[][] ob_new = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < 6; j++) {
				ob_new[i][j] = new String(
						(((Object[]) (list.get(i)))[j]).toString());
			}
		}
		makeTestTimeStop();
		setTableModel(ob_new);
		ob = ob_new;
		makeTestTimeStart();
	}

	class TextLis implements javax.swing.event.DocumentListener {
		int i;

		public TextLis(int i) {
			this.i = i;
		}

		public void insertUpdate(DocumentEvent e) {
			str[i] = text[i].getText();
		}

		/*
		 * �ı����ݼ���ʱ�����������
		 */
		public void removeUpdate(DocumentEvent e) {
			str[i] = text[i].getText();
		}

		public void changedUpdate(DocumentEvent e) {
			str[i] = text[i].getText();
		}
	}

}
