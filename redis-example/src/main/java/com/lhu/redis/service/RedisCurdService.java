package com.lhu.redis.service;

import com.lhu.redis.entity.Student;
import com.lhu.redis.util.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import com.lhu.redis.repository.StudentRepository;

import java.util.List;

@Service
@EnableCaching
public class RedisCurdService {

  @Autowired private StudentRepository repository;

  @CachePut(value = AppConstant.HASH_KEY)
  // @CachePut(value = AppConstant.HASH_KEY, condition="#student.name=='Tom'")
  // @CachePut(value = AppConstant.HASH_KEY, unless="#result.length()<64")
  public Student saveStudent(Student product) {
    return repository.save(product);
  }

  public List<Student> getAllStudent() {
    return repository.findAll();
  }

  // Unless age > 10, then get it from cache
  @Cacheable(value = AppConstant.HASH_KEY, key = "#id", unless = "#result.age>10")
  public Student findStudent(int id) {
    return repository.findStudentById(id);
  }

  @CacheEvict(value = AppConstant.HASH_KEY, key = "#id")
  // @CacheEvict(value = AppConstant.HASH_KEY, allEntries=true)
  public String removeStudent(int id) {
    return repository.deleteStudent(id);
  }
}
