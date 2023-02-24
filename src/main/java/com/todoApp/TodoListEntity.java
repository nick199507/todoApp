package com.todoApp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "todo_list")
public class TodoListEntity {

	@Id
	@TableGenerator(name = "seqTable", table = "seq_table", pkColumnName = "seq_name", pkColumnValue = "user_seq", valueColumnName = "seq_value", initialValue = 0, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "seqTable")
	@Column(name = "todo_num")
	public Integer todoNum;

	@Column(name = "todo_name")
	public String todoName;

	@Column(name = "todo_detail")
	public String todoDetail;

	@Column(name = "done_flg")
	public boolean doneFlg;

	public Integer getTodoNum() {
		return todoNum;
	}

	public void setTodoNum(Integer todoNum) {
		this.todoNum = todoNum;
	}

	public String getTodoName() {
		return todoName;
	}

	public void setTodoName(String todoName) {
		this.todoName = todoName;
	}

	public String getTodoDetail() {
		return todoDetail;
	}

	public void setTodoDetail(String todoDetail) {
		this.todoDetail = todoDetail;
	}

	public boolean isDoneFlg() {
		return doneFlg;
	}

	public void setDoneFlg(boolean doneFlg) {
		this.doneFlg = doneFlg;
	}

}
