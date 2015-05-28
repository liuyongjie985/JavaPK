package Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import Entity.Courserecord;

public class RemindEveryday {
	private Timer t;
	private JCheckBox check;
	private int[] array = new int[9];

	public JCheckBox getCheck() {
		return check;
	}

	public void setCheck(JCheckBox check) {
		this.check = check;
	}

	public Timer getT() {
		return t;
	}

	public void setT(Timer t) {
		this.t = t;
	}

	int state = 0;


	class Listner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			try {
				String temp = df.format(new Date());

				java.util.Date now = df.parse(temp);
				// ob[index][1].toString().toCharArray();
				// 现在时间被分割
				String[] str = temp.toString().split("-");

				// 晚上提醒的时间
				String newTime = str[0] + "-" + str[1] + "-" + str[2]
						+ "-19-00-00";
				// System.out.println("线程标识号" + index + "现在的时间是"
				// + now.toString());
				// System.out.println("线程标识号" + index + "新生成的时间是" +
				// newTime);

				// System.out.println("线程标识号" + index + "得到的考试时间是："
				// + date.toString());

				long c = df.parse(newTime).getTime() - now.getTime();

				// 晚上提醒时间
				if (check.isSelected()) {
					if (c <= 0 && state == 0) {
						state = 1;
						array = MessageControler.getCou().getCourseState(array);
						for (int i = 0; i < 9; i++) {
							if (array[i] == 1) {
								JOptionPane.showMessageDialog(null, "明天有" + (i
										+ 1) + "号课程", "系统信息",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				}
				// 到时间则停
				if (c <= 0) {
					t.stop();
				}

			} catch (Exception re) {
				re.printStackTrace();
			}

		}
	}

	public RemindEveryday(int time) {

		Listner listener = new Listner();// i动作而不是监听
		// RemindTask timetask = new RemindTask();
		t = new Timer(time, listener);// 每隔1000毫秒重复
		// t = new Timer();
		// t.schedule(timetask, 0, time);
		t.start();
		// 给MyPanel添加时间触发器和监听
	}

}
