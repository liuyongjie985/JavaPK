package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Entity.Courserecord;
import Logic.MessageControler;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCourseUI extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField time;
	private JTextField week;
	private JTextField location;
	private JTextField remark;
	private JTextField teacher;
	JComboBox remind;
	JButton ok;
	JButton cancel;
	JButton delete;
	int number;

	JTextField[] text = new JTextField[6];
	String[] str = new String[6];

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// AddCourseUI dialog = new AddCourseUI();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog.
	 */
	public AddCourseUI(int number) {
		this.number = number;
		setBounds(100, 100, 447, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 62, 414, 190);
		contentPanel.add(panel);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));// 风化边界
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblNewLabel = new JLabel("课程名");
		panel.add(lblNewLabel);

		name = new JTextField();
		name.setText("java编程线程同步问题");
		panel.add(name);
		name.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("上课时间");
		panel.add(lblNewLabel_1);

		time = new JTextField();
		time.setText("08-30-00");
		panel.add(time);
		time.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("上课周");
		panel.add(lblNewLabel_2);

		week = new JTextField();
		week.setText("4-16");
		panel.add(week);
		week.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("上课地点");
		panel.add(lblNewLabel_3);

		location = new JTextField();
		location.setText("M104");
		panel.add(location);
		location.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("备注");
		panel.add(lblNewLabel_4);

		remark = new JTextField();
		remark.setText("贼难搞");
		panel.add(remark);
		remark.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("授课教师");
		panel.add(lblNewLabel_5);

		teacher = new JTextField();
		teacher.setText("无师自通");
		panel.add(teacher);
		teacher.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("是否提醒");
		panel.add(lblNewLabel_6);

		remind = new JComboBox();
		remind.setModel(new DefaultComboBoxModel(new String[] { "无提醒", "提醒" }));
		panel.add(remind);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(10, 10, 414, 52);
		contentPanel.add(buttonPane);
		buttonPane.setBorder(new EtchedBorder(EtchedBorder.RAISED));// 风化边界
		buttonPane.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("基本课程信息");
		lblNewLabel_7.setBounds(179, 27, 92, 15);
		buttonPane.add(lblNewLabel_7);

		text[0] = name;
		text[1] = time;
		text[2] = week;
		text[3] = location;
		text[4] = remark;
		text[5] = teacher;

		ok = new JButton("OK");
		ok.setBounds(253, 283, 71, 23);
		contentPanel.add(ok);
		ok.addActionListener(this);
		getRootPane().setDefaultButton(ok);

		cancel = new JButton("Cancel");
		cancel.setBounds(347, 283, 77, 23);
		contentPanel.add(cancel);
		cancel.addActionListener(this);

		delete = new JButton("删除");
		delete.setBounds(154, 283, 64, 23);
		contentPanel.add(delete);
		delete.addActionListener(this);

		for (int i = 0; i < 6; i++) {
			text[i].getDocument().addDocumentListener(new TextLis(i));
			str[i] = text[i].getText();
		}

		update();

	}

	public void update() {
		Courserecord course = MessageControler.getCou().getAcc().getCouD()
				.findById(number);
		if (course == null)// it indicates that we should create a new course
		{
		} else {

			text[0].setText(course.getName());
			text[1].setText(course.getTime());
			text[2].setText(course.getWeek());
			text[3].setText(course.getLocation());
			text[4].setText(course.getRemark());
			text[5].setText(course.getTeacher());
			remind.setSelectedIndex(course.getRemind());
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == ok) {
			Courserecord course = MessageControler.getCou().getAcc().getCouD()
					.findById(number);
			if (course == null)// it indicates that we should create a new
								// course
			{
			} else {
				MessageControler.getCou().getAcc().getCouD().delete(course);
				// Integer id, String name, String time, String week,
				// String location, String teacher, Integer remind, String
				// remark

			}
			Courserecord newcourse = new Courserecord(number, str[0], str[1],
					str[2], str[3], str[5], remind.getSelectedIndex(), str[4]);
			MessageControler.getCou().getAcc().getCouD().save(newcourse);
			JOptionPane.showMessageDialog(null, "保存成功", "系统信息",
					JOptionPane.INFORMATION_MESSAGE);
			MessageControler.getInstance().notifyObserver();
			this.dispose();

		}

		if (e.getSource() == delete) {
			Object[] options = { "是！(大声地)", "让我再想想" };
			int n = JOptionPane.showOptionDialog(null, "大声告诉我你是不是真的要删除", "？",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
			if (n == JOptionPane.YES_OPTION) {
				// ......
				Courserecord course = MessageControler.getCou().getAcc()
						.getCouD().findById(number);
				if (course == null)// it indicates that there is no course to
									// delete
				// course
				{
				} else {
					MessageControler.getCou().getAcc().getCouD().delete(course);
					JOptionPane.showMessageDialog(null, "删除成功", "系统信息",
							JOptionPane.INFORMATION_MESSAGE);
					// Integer id, String name, String time, String week,
					// String location, String teacher, Integer remind, String
					// remark

				}
				MessageControler.getInstance().notifyObserver();
				this.dispose();
			} else if (n == JOptionPane.NO_OPTION) {
				// ......
			}
		}

		if (e.getSource() == cancel) {
			this.dispose();
		}
	}
}
