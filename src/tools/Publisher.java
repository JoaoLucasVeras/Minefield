package tools;

import java.util.HashSet;
import java.util.Set;

public class Publisher {
	
	private Set<Subscriber> subscribers = new HashSet<>();
	
	public void notifySubscribers() {
		for (Subscriber subscriber : subscribers) {
			subscriber.update();
		}
	}
	
	public void subscribe(Subscriber s) {
		subscribers.add(s);
	}
	
	public void unSubscribe(Subscriber s) {
		subscribers.remove(s);
	}
	
}
