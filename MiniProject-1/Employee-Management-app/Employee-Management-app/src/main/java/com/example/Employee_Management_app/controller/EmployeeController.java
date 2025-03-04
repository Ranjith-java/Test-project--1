package com.example.Employee_Management_app.controller;

import com.example.Employee_Management_app.entity.EmployeeDetailStructure;
import com.example.Employee_Management_app.error.EmailAlreadyExistException;
import com.example.Employee_Management_app.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/* *
Controller part
 */

@Controller
/**
 * to interact with service layer created service as global variable and inject it as constructor dependency
 */
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * end point add to initally display the ADDemp.html using getmapping
     * main purpose to pre-populate the page and bind the fields...
     * @param employeeDetailStructure
     * @param model
     * @return Addemp page will be displayed
     */
    @GetMapping("/add")
public String createuser(@ModelAttribute("employees") EmployeeDetailStructure employeeDetailStructure, Model model){
    model.addAttribute("employees",employeeDetailStructure);
    return "Addemp";
}

    /**
     * Postmapping used to collect the values entered in addemp page
     * @param employeeDetailStructure-> Modelattribut populate the values in html page filed to database field
     * @param model -> model is used for sending the error message when user try to enter email which already exist
     * @return if the entered details are correct, the employee values will be sent to service layer where it process to repository layer.
     * the entered values will be stored in database and Addemp page will be redirect to Displayemp page
     */

    @PostMapping("/add")
    public String save(@ModelAttribute("employees") EmployeeDetailStructure employeeDetailStructure,Model model){
        try {
            Optional<EmployeeDetailStructure> email = employeeService.findByEmail(employeeDetailStructure.getEmail());
            if(email.isPresent()){
                throw new EmailAlreadyExistException("Email already registered");
            }
        } catch(EmailAlreadyExistException e){
            model.addAttribute("message",e.getMessage());
            return "Addemp";
        }
        employeeService.save(employeeDetailStructure);
            return "redirect:/display";
    }

    /**
     * Getmapping anootation used to display all the employee details via Displayemp.html page
     * @param model -> is to bind the value from database to respective html field to display the Employee details
     *              we collect the employee details as List by retriving the value through service later using function findall
     * @return Displayemp page will show all details of employee
     */
    @GetMapping("/display")
    public String displayall(Model model){
        List<EmployeeDetailStructure> employeeDetailStructureList= employeeService.findAll();
        model.addAttribute("employees",employeeDetailStructureList);
        return "Displayemp";
    }

    /**
     * Getmapping annotation for end point to view specific employee details using id
     * @param id -> get id form url path and passed to service layer function findbyid
     * @param model to bind the value model is used. if id matched and database return the value will be sent
     * @return we sent the model value to display in Viewemp.html page
     */
    @GetMapping("/viewemp/{id}")
    public String find(@PathVariable("id") Long id,Model model){
        Optional<EmployeeDetailStructure> employeeDetailStructure1=employeeService.findById(id);
      if(employeeDetailStructure1.isPresent()) {
          model.addAttribute("employees", employeeDetailStructure1.get());
      }
      else {
          System.out.println("no record");
      }
        return "Viewemp";
    }
    /**
     * before updating to view the existing employee value we use getmapping to retrieve the details and display in updateemp.html page
     * @param id -> get id form url path and passed to service layer function findbyid
     * @param model if the id is present the model layer will bind the value
     * @return the values will be displayed in Viewemp.html
     */
    @GetMapping("/updateemp/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Optional<EmployeeDetailStructure> employeeDetailStructure1 = employeeService.findById(id);
        if (employeeDetailStructure1.isPresent()) {
            model.addAttribute("employees", employeeDetailStructure1.get());
        } else {
            System.out.println("No record found");
        }
        return "Updateemp";
    }

    /**
     * to update the existing user detail Postmapping annotation is used
     * @param id -> get id form url path and passed to service layer function findbyid
     * @param employeeDetailStructure -> using modelattribute the values entered in html page will be populated in employee object will be sent to service layer for update
     * @return if the repository layer updated the return page will be redirect to diplay page were update value can be viewed
     */

    @PostMapping("/updateemp/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("employees") EmployeeDetailStructure employeeDetailStructure) {
        employeeDetailStructure.setId(id);
        EmployeeDetailStructure employeeDetailStructure1=  employeeService.update(employeeDetailStructure);
        return "redirect:/display";
    }

    /**
     * before deleting the employee we try to display details of employee
     * @param id-> we get id from url path to retrieve the details of specific employee
     * @param model once id is found the model will collect the value and render to html page
     * @return Deleteemp.html page will shows the employee detail based on id
     */
    @GetMapping("deleteemp/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        Optional<EmployeeDetailStructure> employeeDetailStructure1 = employeeService.findById(id);
        if (employeeDetailStructure1.isPresent()) {
            model.addAttribute("employees", employeeDetailStructure1.get());
          } else {
            System.out.println("No record found");
        }
        return "Deleteemp";
    }

    /**
     * after the getannotion(delete/id) display the employee details. Use postmapping annotation to confirm and  pass the id to be deleted
     * @param id-> from url the id  will be passed to service layer
     * @return if the repository layer deleted the employee details successfully we will be redirected to display page
     */
    @PostMapping("deleteemp/{id}")
    public String deleteByIdid(@PathVariable("id") Long id){

         employeeService.deleteById(id);
    return "redirect:/display";

    }
}
