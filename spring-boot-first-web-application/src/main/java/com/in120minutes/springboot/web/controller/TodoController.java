package com.in120minutes.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in120minutes.springboot.web.model.Todo;
import com.in120minutes.springboot.web.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@RequestMapping(value="/list-todos",method=RequestMethod.GET)
	public String showTodos( ModelMap modelMap) {
		String name = getLoggedInUserName(modelMap);
		modelMap.put("todo", todoService.retrieveTodos(name));
		return "list-todos";
	}
	
	private String getLoggedInUserName(ModelMap model) {
		return (String) model.get("name");
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.GET)
	public String showAddTodoPage( ModelMap modelMap) {
		modelMap.addAttribute("todo", new Todo(0,  getLoggedInUserName(modelMap), "Default Desc",
				new Date(), false));
		return "todo";
	}
	
	@RequestMapping(value="/add-todo",method=RequestMethod.POST)
	public String addTodos( ModelMap modelMap, @Valid Todo todo,BindingResult result) {
		if(result.hasErrors()){
			return "todo";
		}
		
		todoService.addTodo(modelMap.get("name").toString(), todo.getDesc(), new Date(), false);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todo",method=RequestMethod.GET)
	public String deleteTodoPage(@RequestParam int id ) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/update-todo",method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id ,ModelMap modelMap) {
		Todo todo = todoService.retrieveTodos(id);
		modelMap.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="/update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap modelMap,@Valid Todo todo,BindingResult result) {
		if(result.hasErrors()){
			return "todo";
		}
		
		todo.setUser(getLoggedInUserName(modelMap));
		todoService.updateTodo(todo);
		
		return "redirect:/list-todos";
	}
   
}
