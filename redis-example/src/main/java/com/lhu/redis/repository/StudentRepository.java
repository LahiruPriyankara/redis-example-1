package com.lhu.redis.repository;

import com.lhu.redis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.lhu.redis.util.AppConstant;

import java.util.List;

@Repository
public class StudentRepository {

  @Autowired private RedisTemplate template;

  public Student save(Student student) {
    System.out.println("Save in DB..");
    template.opsForHash().put(AppConstant.HASH_KEY, student.getId(), student);
    return student;
  }

  public List<Student> findAll() {
    System.out.println("Fetch all student from DB..");
    return template.opsForHash().values(AppConstant.HASH_KEY);
  }

  public Student findStudentById(int id) {
    System.out.println("Fetch student from DB..");
    return (Student) template.opsForHash().get(AppConstant.HASH_KEY, id);
  }

  public String deleteStudent(int id) {
    System.out.println("Delete from student from DB..");
    template.opsForHash().delete(AppConstant.HASH_KEY, id);
    return "Successfully student removed..";
  }
}
