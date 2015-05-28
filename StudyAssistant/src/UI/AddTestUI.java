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
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import Entity.*;
import Logic.*;

public class AddTestUI extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField time;
	private JTextField location;
	private JTextField remark;
	JComboBox box;
	String str[] = new String[4];
	JTextField[] text = new JTextField[4];
	JButton save;
	JButton cancel;
	int model = 0;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// try {
	// AddTestUI dialog = new AddTestUI();
	// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	// dialog.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * Create the dialog.
	 */
	public AddTestUI(int model) {
		this.model = model;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 45, 414, 174);
			contentPanel.add(panel);
			panel.setBorder(new EtchedBorder(EtchedBorder.RAISED));// 风化边界
			panel.setLayout(new GridLayout(0, 2, 0, 0));

			JLabel lblNewLabel_1 = new JLabel("考试名");
			panel.add(lblNewLabel_1);

			name = new JTextField();
			name.setText("软件工程");
			panel.add(name);
			name.setColumns(10);

			JLabel lblNewLabel_2 = new JLabel("考试时间");
			panel.add(lblNewLabel_2);

			time = new JTextField();
			time.setText("2015-7-20-17-30-00");
			panel.add(time);
			time.setColumns(10);

			JLabel lblNewLabel_3 = new JLabel("考试地点");
			panel.add(lblNewLabel_3);

			location = new JTextField();
			location.setText("M楼201");
			panel.add(location);
			location.setColumns(10);

			JLabel lblNewLabel_4 = new JLabel("备注");
			panel.add(lblNewLabel_4);

			remark = new JTextField();
			remark.setText("千万不要迟到哦");
			panel.add(remark);
			remark.setColumns(10);

			JLabel lblNewLabel_5 = new JLabel("提醒类型");
			panel.add(lblNewLabel_5);

			box = new JComboBox();
			box.setModel(new DefaultComboBoxModel(new String[] { "无提醒", "当天7点",
					"提前一天", "提前5天" }));
			panel.add(box);
		}

		JLabel lblNewLabel = new JLabel("考试信息添加");
		lblNewLabel.setBounds(178, 20, 91, 15);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 221, 414, 35);
			contentPanel.add(buttonPane);
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			buttonPane.setLayout(null);
			{
				save = new JButton("保存");
				save.addActionListener(this);
				save.setBounds(216, 7, 92, 23);
				save.setActionCommand("OK");
				buttonPane.add(save);
				getRootPane().setDefaultButton(save);
			}
			{
				cancel = new JButton("取消");
				cancel.addActionListener(this);
				cancel.setBounds(318, 7, 86, 23);
				cancel.setActionCommand("Cancel");
				buttonPane.add(cancel);
			}
		}

		text[0] = name;
		text[1] = time;
		text[2] = location;
		text[3] = remark;
		for (int i = 0; i < 4; i++) {
			text[i].getDocument().addDocumentListener(new TextLis(i));
			str[i] = text[i].getText();
		}

		if (model != 0) {
			Testrecord test = MessageControler.getTes().getAcc().getTesD()
					.findById(model);
			text[0].setText(test.getName());
			text[1].setText(test.getTime());
			text[2].setText(test.getLocation());
			text[3].setText(test.getRemark());
			box.setSelectedIndex(test.getRemind());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == save) {
			if (model == 0) {
				int id = MessageControler.getTes().getTestID();

				Testrecord testRecord = new Testrecord(id, str[0], str[1],
						str[2], box.getSelectedIndex(), str[3]);

				MessageControler.getTes().getAcc().getTesD().save(testRecord);
				JOptionPane.showMessageDialog(null, "保存成功", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
				MessageControler.getInstance().notifyObserver();
				this.dispose();
			}
			else
			{
				Testrecord testRecord = MessageControler.getTes().getAcc().getTesD().findById(model);
				testRecord.setLocation(str[2]);
				testRecord.setTime(str[1]);
				testRecord.setName(str[0]);
				testRecord.setRemark(str[3]);
				testRecord.setRemind(box.getSelectedIndex());
				MessageControler.getTes().getAcc().getTesD().merge(testRecord);
				JOptionPane.showMessageDialog(null, "保存成功", "系统信息",
						JOptionPane.INFORMATION_MESSAGE);
				MessageControler.getInstance().notifyObserver();
				this.dispose();
			}
		}
		if (e.getSource() == cancel) {
			this.dispose();
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

}
