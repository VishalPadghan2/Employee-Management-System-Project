package com.example.ems.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.ems.Entity.Employee;
import com.example.ems.Repository.EmpRepo;
import com.example.ems.helper.FileUploader;

@Controller
public class EmpController {
	
	@Autowired
	EmpRepo empRepo;
	@Autowired
	FileUploader fileUploader;
	
	String[] arrayDesig = {"Owner", "Manager", "Developer", "Analysis", "Tester","HR"};
	
	@GetMapping("/emp/")
	public String showData(Model model)
	{
		List<Employee> listEmp = empRepo.findAll();
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("arrayDesig", arrayDesig);
		return "index";
	}

	@PostMapping("/emp/save/")
	public String saveData(Employee emp, MultipartFile file) 
	{
		
		String fileName = file.getOriginalFilename();
		String extension = fileName.substring(fileName.indexOf(".") + 1);
		
		emp.setExtension(extension);
		Employee empSaved = empRepo.save(emp);
		
		String fileNameNew = empSaved.getId() + "." + extension;
		fileUploader.uploadFile(file, fileNameNew);
		
		return "redirect:/emp/";
	}
	
	@GetMapping("/emp/delete/{id}/")
	public String deleteData(@PathVariable Long id)
	{
		empRepo.deleteById(id);
		return "redirect:/emp/";
	}

}
