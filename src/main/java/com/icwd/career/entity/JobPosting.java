package com.icwd.career.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_postings")
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "posted_date")
    private LocalDate postedDate;

    @Column(name = "deadline")
    private LocalDate deadline;

    public JobPosting() {
    }

    // ✅ Constructor with all 4 fields
    public JobPosting(String title, String description, LocalDate postedDate, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.postedDate = postedDate;
        this.deadline = deadline;
    }

    // 🔄 Getters and Setters

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "JobPosting{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", postedDate=" + postedDate +
                ", deadline=" + deadline +
                '}';
    }


}
