package com.icwd.career;

import com.icwd.career.dao.JobPostingDAO;
import com.icwd.career.entity.JobPosting;

import java.time.LocalDate;
import java.util.List;

public class App {
    public static void main(String[] args) {

        JobPostingDAO dao = new JobPostingDAO();

        // ✅ CREATE
        JobPosting job1 = new JobPosting(
                "Java Developer",
                "Develop Spring Boot APIs",
                LocalDate.now(),
                LocalDate.now().plusDays(30)
        );
        dao.saveJob(job1);

        // ✅ READ
        List<JobPosting> jobs = dao.getAllJobs();
        System.out.println("📋 All Jobs:");
        jobs.forEach(job -> System.out.println(job.getId() + ": " + job.getTitle()));

        // ✅ UPDATE
        if (!jobs.isEmpty()) {
            JobPosting jobToUpdate = jobs.get(0);
            jobToUpdate.setTitle("Senior Java Developer");
            dao.updateJob(jobToUpdate);
        }

        // ✅ DELETE
        if (jobs.size() > 1) {
            int deleteId = jobs.get(1).getId();  // delete 2nd job if exists
            dao.deleteJob(deleteId);
        }
        dao.deleteExpiredJobs();

        System.out.println("🏁 CRUD operations completed!");


    }
}
