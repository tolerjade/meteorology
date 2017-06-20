package cn.ac.iscas.dao;

import cn.ac.iscas.entity.MeteorologicalStation;
import cn.ac.iscas.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by admin on 2017/5/24.
 */
public class MeteorologicalStationDao {
    public List<MeteorologicalStation> getAll() {
        Session session = null;
        List list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createQuery("from MeteorologicalStation").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return list;
        }
    }

    public List<MeteorologicalStation> getAllByIdLike(String ids) {
        Session session = null;
        List list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createQuery("from MeteorologicalStation where id like '" + ids + "'").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return list;
        }
    }

    public List getAllByAlt() {
        Session session = null;
        List list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createQuery("from MeteorologicalStation where alt is not null").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return list;
        }
    }

    public List getAttributeByidLike(String attribute, String idlike) {
        Session session = null;
        List list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createQuery("select " + attribute + " from MeteorologicalStation where id like '" + idlike + "'").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return list;
        }
    }

    public List getAttribute(String attribute) {
        Session session = null;
        List list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createQuery("select " + attribute + " from MeteorologicalStation where alt is not null").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return list;
        }
    }

    public MeteorologicalStation getByStationId(int id) {
        Session session = null;
        MeteorologicalStation meteorologicalStation = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            meteorologicalStation = (MeteorologicalStation) session.get(MeteorologicalStation.class, id);
            //session.createQuery("from MeteorologicalStation  where 'ID' = " + id).list();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return meteorologicalStation;
        }
    }

    public void saveEntity(MeteorologicalStation meteorologicalStation) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(meteorologicalStation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
        }
    }
}
