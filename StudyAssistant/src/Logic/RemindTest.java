package Logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Entity.Testrecord;

public class RemindTest {
	Timer t;
	Object[][] ob;
	JTable table;

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public Object[][] getOb() {
		return ob;
	}

	public void setOb(Object[][] ob) {
		this.ob = ob;
	}

	public Timer getT() {
		return t;
	}

	public void setT(Timer t) {
		this.t = t;
	}

	int index;
	int state = 0;
	List<Testrecord> test = MessageControler.getTes().getAcc().getTesD()
			.findAll();

	class Listner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			try {
				String temp = df.format(new Date());

				java.util.Date now = df.parse(temp);
				// ob[index][1].toString().toCharArray();
				// 考试时间被分割
				String[] str = ob[index][1].toString().split("-");
				String newTime = str[0] + "-" + str[1] + "-" + str[2]
						+ "-7-00-00";
				// System.out.println("线程标识号" + index + "现在的时间是"
				// + now.toString());
				// System.out.println("线程标识号" + index + "新生成的时间是" +
				// newTime);

				java.util.Date date = df.parse(ob[index][1].toString());
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
				ob[index][6] = lasttime;
				setTableModel(ob);

				// 提醒类型
				switch (test.get(index).getRemind()) {
				case 0:
					break;
				case 1:// 当天7点
					if (now.after(df.parse(newTime)) && state == 0 && l > 0) {
						state = 1;
						JOptionPane.showMessageDialog(null, "今天会有考试:"
								+ ob[index][0], "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				case 2:// 提前一天
					if (l > 0 && l <= (1000 * 60 * 60 * 24) && state == 0) {// 提前一天

						state = 1;
						JOptionPane.showMessageDialog(null, "明天此时会有考试:"
								+ ob[index][0], "系统信息",
								JOptionPane.INFORMATION_MESSAGE);
					}
					break;
				case 3:// 提前5天
					if (l <= 1000 * 60 * 60 * 24 * 5 && state == 0 && l > 0) {
						state = 1;
						JOptionPane.showMessageDialog(null, "五天后此时会有考试:"
								+ ob[index][0], "系统信息",
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

	// class RemindTask extends TimerTask {
	// public void run() {
	// System.out.println("线程" + index);
	//
	// SimpleDateFormat df = new SimpleDateFormat(
	// "yyyy-MM-dd-HH-mm-ss");
	// try {
	// String temp = df.format(new Date());
	// // df = new SimpleDateFormat("yyyy-MM-DD-HH-mm-ss");
	// java.util.Date now = df.parse(temp);
	// // ob[index][1].toString().toCharArray();
	// // 考试时间被分割
	// temp = new String(ob[index][1].toString());
	// String[] str = temp.split("-");
	// String newTime = str[0] + "-" + str[1] + "-" + str[2]
	// + "-00-02-00";
	// System.out.println("现在的时间是" + now.toString());
	// System.out.println("新生成的时间是" + newTime);
	// // df = new SimpleDateFormat("yyyy-MM-DD-HH-mm-ss");
	// java.util.Date date = df.parse(ob[index][1].toString()
	// + "-00");
	// System.out.println("得到的考试时间是：" + date.toString());
	//
	// // DateTime dateTime=new DateTime(2012, 12, 13, 18, 23,55);
	//
	// long l = date.getTime() - now.getTime();
	// long day = l / (24 * 60 * 60 * 1000);
	// long hour = (l / (60 * 60 * 1000) - day * 24);
	// long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
	// long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
	// System.out.println("" + day + "天" + hour + "小时" + min + "分"
	// + s + "秒");
	// // ob[index][6] = s + "秒";
	// table.setModel(new DefaultTableModel(ob,
	// new String[] { "考试名", "考试时间", "考试地点", "是否提醒", "备注",
	// "考试编号", "剩余天数" }));
	//
	// // 提醒类型
	// switch (test.get(index).getRemind()) {
	// case 0:
	// break;
	// case 1:// 当天7点
	// if (now.after(df.parse(newTime)) && state == 0) {
	// state = 1;
	// System.out.println("当天7点的提醒");
	// }
	// break;
	// case 2:// 提前一天
	// if (l <= (1000 * 60 * 60 * 24) && state == 0) {// 提前一天
	//
	// state = 1;
	// System.out.println("提前一天的提醒");
	// }
	// break;
	// case 3:// 提前5天
	// if (l <= 1000 * 60 * 60 * 24 * 5 && state == 0) {
	// state = 1;
	// System.out.println("提前五天的提醒");
	// }
	// break;
	//
	// }
	//
	// // 到时间则停
	// if (l <= 0) {
	// t = null;
	// }
	//
	// } catch (Exception re) {
	// re.printStackTrace();
	// }
	//
	// // DateTimeFormatter format =
	// // DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss");
	// // DateTime now = DateTime.parse(new
	// // DateTime().toString("yyyy-MM-dd-HH-mm-ss"), format);
	// }
	// }
	public void setTableModel(Object[][] ob) {
		DefaultTableModel m = new DefaultTableModel(ob, new String[] { "考试名",
				"考试时间", "考试地点", "是否提醒", "备注", "考试编号", "剩余时间" });
		TableRowSorter sorter = new TableRowSorter(m);
		table.setRowSorter(sorter);
		table.setModel(m);
	}

	public RemindTest(int index, int time) {
		this.index = index;
		Listner listener = new Listner();// i动作而不是监听
		// RemindTask timetask = new RemindTask();
		t = new Timer(time, listener);// 每隔1000毫秒重复
		// t = new Timer();
		// t.schedule(timetask, 0, time);
		t.start();
		// 给MyPanel添加时间触发器和监听
	}

}
