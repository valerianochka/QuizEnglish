package com.example.quizenglish.models;

public class CourseModel {

    String courseId, courseNum, courseTitle, courseDescription, courseList;

    public CourseModel(String courseId, String courseNum, String courseTitle, String courseDescription, String courseList) {
        this.courseId = courseId;
        this.courseNum = courseNum;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseList = courseList;
    }

    public CourseModel(){}

    public String getCourseList() {
        return courseList;
    }

    public void setCourseList(String courseList) {
        this.courseList = courseList;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
