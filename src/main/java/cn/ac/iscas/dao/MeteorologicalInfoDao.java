package cn.ac.iscas.dao;

import cn.ac.iscas.entity.MeteorologicalInfo;
import cn.ac.iscas.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by admin on 2017/5/24.
 */
public class MeteorologicalInfoDao {

    public List<MeteorologicalInfo> getAll() {
        Session session = null;
        List list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = session.createQuery("from MeteorologicalInfo").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return list;
        }
    }

    public MeteorologicalInfo getByDateaAndId(MeteorologicalInfo meteorologicalInfo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            meteorologicalInfo = (MeteorologicalInfo) session.get(MeteorologicalInfo.class, meteorologicalInfo);
            //session.createQuery("from MeteorologicalStation  where 'ID' = " + id).list();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
            return meteorologicalInfo;
        }
    }

    public void saveEntity(MeteorologicalInfo meteorologicalInfo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(meteorologicalInfo);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            HibernateUtil.shutdown(session);
        }
    }
}
