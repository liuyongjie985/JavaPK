package Logic;

import java.util.ArrayList;
import java.util.List;

public class MessageControler implements Observerable {
	static final private MessageControler instance = new MessageControler();
	static private CourseInf cou = CourseInf.getInstance();
	static private TestInf tes = TestInf.getInstance();
	static private ArrayList<SystemObserver> observer = new ArrayList<SystemObserver>();

	public static CourseInf getCou() {
		return cou;
	}

	public static void setCou(CourseInf cou) {
		MessageControler.cou = cou;
	}

	public static TestInf getTes() {
		return tes;
	}

	public static void setTes(TestInf tes) {
		MessageControler.tes = tes;
	}

	private MessageControler() {
	}

	static public MessageControler getInstance() {
		return instance;
	}

	public void registerObserver(SystemObserver ob) {
		// TODO Auto-generated method stub
		observer.add(ob);
	}

	@Override
	public void removeObserver(SystemObserver ob) {
		// TODO Auto-generated method stub
		observer.remove(ob);
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		//System.out.printf("现在有个" + observer.size() + "个观察者");
		for (int i = 0; i < observer.size(); i++) {
			observer.get(i).update();
		}
	}
}
