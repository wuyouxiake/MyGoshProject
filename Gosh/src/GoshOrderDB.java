import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.GoshCart;
import model.GoshOrder;


public class GoshOrderDB {

	public static void insert(GoshOrder g) {
		EntityManager em = customTools.DBUtil.getEmFactory()
				.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(g);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(GoshOrder g) {
		EntityManager em = customTools.DBUtil.getEmFactory()
				.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(g);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(GoshOrder g) {
		EntityManager em = customTools.DBUtil.getEmFactory()
				.createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(g));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

}