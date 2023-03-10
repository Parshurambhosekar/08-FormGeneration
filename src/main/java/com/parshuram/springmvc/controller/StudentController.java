package com.parshuram.springmvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.parshuram.springmvc.binding.Student;
import com.parshuram.springmvc.entity.StudentEntity;
import com.parshuram.springmvc.repository.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;

	
	@GetMapping("/")
	public String loadForm(Model model) {
		
		loadFormDetails(model);
		
		model.addAttribute("message", "Form is Loaded...........");
		
		return "index";
	}
	
	@PostMapping("/save")
	public String saveFormData(Student std,Model model) {
		
		System.out.println(std);
		
		StudentEntity entity=new StudentEntity();
		
		//this class which converts binding class into entity class
		//this class can only copy primitive data types not object references.
		BeanUtils.copyProperties(std, entity);
		
		//object ref.is not copied so we can set the data because timing store
		//string objects
		entity.setTimings(Arrays.toString(std.getTimings()));
		
		studentRepository.save(entity);
		
		model.addAttribute("message", "Student Saved...............");
		
		//after form submition blank form will be loaded...
		loadFormDetails(model);
		
		return "index";
	}
	
	
	public void loadFormDetails(Model model) {
		
		List<String> courseList=new ArrayList<String>();	
		courseList.add("Java");
		courseList.add("Python");
		courseList.add("DEVOPS");
		courseList.add("AWS");
		courseList.add("C++");
		courseList.add("C");
		
		List<String> timingList=new ArrayList<String>();
		timingList.add("Morning");
		timingList.add("Afternoon");
		timingList.add("Evening");
		
		Student student=new Student();
		
		model.addAttribute("courseList", courseList);//send couserList to UI
		model.addAttribute("timingList", timingList);//send timingList to UI
		model.addAttribute("student",student);//send studentData to UI;
	
	}
	
	//method to display the student data
	
	@GetMapping("/viewStudents")
	public String displayStudentDetails(Model model) {
		
		List<StudentEntity> studentList=studentRepository.findAll();
		
		model.addAttribute("students", studentList);
		
		return "data";
		
	}
	
}
