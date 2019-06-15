package mailApp;

import mailApp.repository.JPARepository;

public class Mail {
	
	private MailItem[] items;
	private final JPARepository repo = new JPARepository();
	private int index;

	public Mail() {
		items = repo.getAll();
		index = 0;
		
	}
	
	public MailItem next() {
        return (items.length > 0) ? items[index++] : null;
    }
	
}
