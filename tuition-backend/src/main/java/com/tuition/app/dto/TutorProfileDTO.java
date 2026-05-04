package com.tuition.app.dto;

public class TutorProfileDTO {
    private Long id;
    private Long userId;
    private String userName;
    private String subjects;
    private String classLevel;
    private String city;
    private Double hourlyRate;
    private String bio;
    private Integer experience;
    private String teachingMode;
    private Double rating;
    private Integer reviewsCount;

    // Constructors
    public TutorProfileDTO() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getSubjects() { return subjects; }
    public void setSubjects(String subjects) { this.subjects = subjects; }
    public String getClassLevel() { return classLevel; }
    public void setClassLevel(String classLevel) { this.classLevel = classLevel; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public Double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(Double hourlyRate) { this.hourlyRate = hourlyRate; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }
    public String getTeachingMode() { return teachingMode; }
    public void setTeachingMode(String teachingMode) { this.teachingMode = teachingMode; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public Integer getReviewsCount() { return reviewsCount; }
    public void setReviewsCount(Integer reviewsCount) { this.reviewsCount = reviewsCount; }
}
