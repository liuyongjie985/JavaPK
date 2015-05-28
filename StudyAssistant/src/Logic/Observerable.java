package Logic;

public interface Observerable {
	public void registerObserver(SystemObserver ob);

	public void removeObserver(SystemObserver ob);

	public void notifyObserver();
}
