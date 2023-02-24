package com.todoApp;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HelloTodoListService {

	@Autowired
	TodoListRepository todoListRepository;

	public List<TodoListEntity> getAllTodoListRocord() {

		List<TodoListEntity> outEntity = new ArrayList<TodoListEntity>();
		TodoListEntity inEntity = new TodoListEntity();

		inEntity.setDoneFlg(false);
		outEntity = todoListRepository.findAll(Example.of(inEntity));

		return outEntity;
	}

	public Boolean insertNewTodo(NewTodoForm todoForm) {

		TodoListEntity inEntity = new TodoListEntity();
		TodoListEntity outEntity = new TodoListEntity();

		Boolean insertFlg = true;

		inEntity.setTodoNum(todoForm.getTodoNum());
		inEntity.setTodoName(todoForm.getTodoName());
		inEntity.setTodoDetail(todoForm.getTodoDetail());
		inEntity.setDoneFlg(false);

		outEntity = todoListRepository.save(inEntity);

		if (outEntity == null) {
			insertFlg = false;
		}

		return insertFlg;
	}

	public TodoListEntity getDetailTodo(Integer todoNum) {

		TodoListEntity outEntity = todoListRepository.findByTodoNum(todoNum);

		return outEntity;

	}

	public TodoListEntity updateTodoList(NewTodoForm newTodoForm) {

		TodoListEntity inEntity = new TodoListEntity();
		TodoListEntity outEntity = new TodoListEntity();

		inEntity.setTodoNum(newTodoForm.getTodoNum());
		inEntity.setTodoName(newTodoForm.getTodoName());
		inEntity.setTodoDetail(newTodoForm.getTodoDetail());

		outEntity = todoListRepository.saveAndFlush(inEntity);

		return outEntity;

	}

	public TodoListEntity deleteTodoList(Integer id) {

		TodoListEntity inEntity = new TodoListEntity();
		TodoListEntity outEntity = new TodoListEntity();

		inEntity.setTodoNum(id);
		inEntity.setDoneFlg(true);

		outEntity = todoListRepository.saveAndFlush(inEntity);

		return outEntity;

	}

}
