package com.example.student.baitapandroidsqlite;

public class SinhVien {
    private int id;
    private String name, class_name, subject;


    public SinhVien(int id, String name, String class_name, String subject) {
        this.id = id;
        this.name = name;
        this.class_name = class_name;
        this.subject = subject;
    }

    public SinhVien(String name, String class_name, String subject) {
        this.name = name;
        this.class_name = class_name;
        this.subject = subject;
    }

    public SinhVien(int id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", class_name=" + class_name + ", subject='" + subject;
    }
}
