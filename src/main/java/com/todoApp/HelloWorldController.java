package com.todoApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@Autowired
	HelloTodoListService helloTodoListService;

	@RequestMapping(value = "todoList", method = RequestMethod.GET)
	String showTodoList(Model model) {

		List<TodoListEntity> todoList = helloTodoListService.getAllTodoListRocord();

		model.addAttribute("todoList", todoList);

		return "index";
	}

	@RequestMapping(value = "todoList/new", method = RequestMethod.GET)
	ModelAndView newTodo(@ModelAttribute NewTodoForm newTodoForm, Model model, ModelAndView mav) {
		model.addAttribute("todo", newTodoForm);
		mav.setViewName("new");
		return mav;
	}

	@RequestMapping(value = "todoList", method = RequestMethod.POST)
	ModelAndView createTodo(@ModelAttribute NewTodoForm newTodoForm, ModelAndView mav) {

		Boolean insertFlg = helloTodoListService.insertNewTodo(newTodoForm);

		mav.setViewName("redirect:/todoList");

		return mav;
	}

	@RequestMapping(value = "todoList/{id}", method = RequestMethod.GET)
	ModelAndView showDetail(@ModelAttribute NewTodoForm newTodoForm, @PathVariable("id") Integer id, ModelAndView mav) {

		TodoListEntity outEntity = helloTodoListService.getDetailTodo(id);

		mav.addObject("todo", outEntity);

		mav.setViewName("show");

		return mav;
	}

	@RequestMapping(value = "todoList/{id}/edit", method = RequestMethod.GET)
	ModelAndView showEdit(@PathVariable("id") Integer id, @ModelAttribute NewTodoForm newTodoForm, ModelAndView mav) {

		TodoListEntity outEntity = new TodoListEntity();
		outEntity.setTodoNum(id);

		newTodoForm.setTodoNum(id);

		mav.addObject("todo", outEntity);

		mav.setViewName("edit");

		return mav;
	}

	@RequestMapping(value = "todoList/{id}", method = RequestMethod.PUT)
	ModelAndView update(@PathVariable("id") Integer id, @ModelAttribute NewTodoForm newTodoForm, ModelAndView mav) {

		newTodoForm.setTodoNum(id);
		TodoListEntity outEntity = helloTodoListService.updateTodoList(newTodoForm);

		mav.setViewName("redirect:/todoList");

		return mav;
	}

	@RequestMapping(value = "/todoList/{id}", method = RequestMethod.DELETE)
	ModelAndView delete(@PathVariable("id") Integer id, ModelAndView mav) {

		TodoListEntity outEntity = helloTodoListService.deleteTodoList(id);

		mav.setViewName("redirect:/todoList");

		return mav;
	}

}
