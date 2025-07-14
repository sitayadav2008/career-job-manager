package com.icwd.career.dao;

import com.icwd.career.entity.JobPosting;
import com.icwd.career.util.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class JobPostingDAO {

    // 🔹 CREATE
    public void saveJob(JobPosting job) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(job);
            tx.commit();
            System.out.println("✅ Job saved successfully");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // 🔹 READ (ALL JOBS)
    public List<JobPosting> getAllJobs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from JobPosting", JobPosting.class).list();
        }
    }

    // 🔹 UPDATE
    public void updateJob(JobPosting job) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(job);
            tx.commit();
            System.out.println("✅ Job updated successfully");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }



    public void deleteExpiredJobs() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM JobPosting WHERE deadline < :today";
            Query query = session.createQuery(hql);
            query.setParameter("today", LocalDate.now());

            int deletedCount = query.executeUpdate();
            transaction.commit();

            System.out.println("🧹 Deleted " + deletedCount + " expired jobs.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }


    // 🔹 DELETE
    public void deleteJob(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            JobPosting job = session.get(JobPosting.class, id);
            if (job != null) {
                tx = session.beginTransaction();
                session.delete(job);
                tx.commit();
                System.out.println("❌ Job deleted successfully");
            } else {
                System.out.println("⚠️ Job not found!");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }


}
