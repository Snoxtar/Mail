package mailApp.repository;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import mailApp.MailItem;

public class JPARepository {

	private EntityManager em;

	public JPARepository() {
		em = Persistence.createEntityManagerFactory("mailDS").createEntityManager();

	}

	public void close() throws IOException {
		em.close();
	}

	public MailItem[] getAll() {
		TypedQuery<MailEntity> q = em.createQuery("SELECT m FROM MailEntity m", MailEntity.class);
		List<MailEntity> res = q.getResultList();
		MailItem[] all = new MailItem[res.size()];
		for (int i = 0; i < res.size(); i++) {
			all[i] = res.get(i).getMailItem();
		}
		return all;
	}

	public void add(MailEntity mail) {
		em.getTransaction().begin();
		em.persist(mail);
		em.getTransaction().commit();
	}

}
