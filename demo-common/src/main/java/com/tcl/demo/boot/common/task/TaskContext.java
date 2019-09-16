package com.tcl.demo.boot.common.task;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskContext<T> implements Serializable {

    private String taskNo;

    private T data;
}
