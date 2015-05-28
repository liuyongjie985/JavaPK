package Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Entity.Courserecord;

public class RemindCourse {
	Timer t;
	int courseNum = 0;
	int state = 0;

	public Timer getT() {
		return t;
	}

	public void setT(Timer t) {
		this.t = t;
	}

	class Listner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			try {
				String temp = df.format(new Date());

				java.util.Date now = df.parse(temp);
				// ob[index][1].toString().toCharArray();
				// 现在时间被分割
				String[] str = temp.toString().split("-");

				// System.out.println("线程标识号" + index + "现在的时间是"
				// + now.toString());
				// System.out.println("线程标识号" + index + "新生成的时间是" +
				// newTime);

				// 得到上课时间
				Courserecord course = MessageControler.getCou().getAcc()
						.getCouD().findById(courseNum);
				String time = course.getTime();
				String completime = str[0] + "-" + str[1] + "-" + str[2] + "-"
						+ time;

				// 得到上课的时间
				java.util.Date date = df.parse(completime);
				// System.out.println("线程标识号" + index + "得到的考试时间是："
				// + date.toString());

				// DateTime dateTime = new DateTime(2012, 12, 13, 18, 23,
				// 55);

				long l = date.getTime() - now.getTime();

				long day = l / (24 * 60 * 60 * 1000);
				long hour = (l / (60 * 60 * 1000) - day * 24);
				long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
				long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
				String lasttime = "" + day + "天" + hour + "小时" + min + "分" + s
						+ "秒";
				// ob[index][6] = lasttime;
				// setTableModel(ob);

				// 提醒类型
				switch (course.getRemind()) {
				case 0:// 不提醒
					break;
				case 1:// 上课前15分钟提醒
					if (l > 0 && l < 15 * 60 * 1000 && state == 0) {
						state = 1;
						//System.out.println("上课前15分钟的提醒,课程号：" + courseNum);
						
						JOptionPane.showMessageDialog(null, "15分钟内有课程:"
								+ course.getName(), "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				}

				// 到时间则停
				if (l <= 0) {
					t.stop();
				}

			} catch (Exception re) {
				re.printStackTrace();
			}

		}
	}

	public RemindCourse(int num, int time) {
		this.courseNum = num + 1;
		Listner listener = new Listner();// i动作而不是监听
		// RemindTask timetask = new RemindTask();
		t = new Timer(time, listener);// 每隔1000毫秒重复
		// t = new Timer();
		// t.schedule(timetask, 0, time);
		t.start();
		// 给MyPanel添加时间触发器和监听
	}

}
