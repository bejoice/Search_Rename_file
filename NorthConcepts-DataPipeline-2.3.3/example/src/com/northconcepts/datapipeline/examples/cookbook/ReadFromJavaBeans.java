/*
 * Copyright (c) 2006-2012 North Concepts Inc.  All rights reserved.
 * Proprietary and Confidential.  Use is subject to license terms.
 *
 * http://northconcepts.com/data-pipeline/licensing/
 *
 */
package com.northconcepts.datapipeline.examples.cookbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.northconcepts.datapipeline.core.DataEndpoint;
import com.northconcepts.datapipeline.core.Record;
import com.northconcepts.datapipeline.javabean.JavaBeanReader;

public class ReadFromJavaBeans {
    
    public static final Logger log = DataEndpoint.log; 

    public static void main(String[] args) {
		Department department = new Department().setEmployees(new Employee(), new Manager());
		
		JavaBeanReader reader = new JavaBeanReader("department", department);
		
		reader.addField("id", "//id");
		reader.addField("firstName", "//firstName");
		reader.addField("lastName", "//lastName");
		reader.addField("budget", "//budget");

		reader.addRecordBreak("//Employee");
		reader.addRecordBreak("//Manager");
		
        reader.open();
        try {
            Record record;
            while ((record = reader.read()) != null) {
            	log.info(record);
            }
        } finally {
            reader.close();
        }
    }
    
    
    //============================================
    // Employee Bean
    //============================================
    public static class Employee {

    	private long id = System.nanoTime();
    	private String firstName = "FirstName-" + getClass().getSimpleName();
    	private String lastName = "LastName-" + getClass().getSimpleName();

    	public long getId() {
    		return id;
    	}

    	public Employee setId(long id) {
    		this.id = id;
    		return this;
    	}

    	public String getFirstName() {
    		return firstName;
    	}

    	public Employee setFirstName(String firstName) {
    		this.firstName = firstName;
    		return this;
    	}

    	public String getLastName() {
    		return lastName;
    	}

    	public Employee setLastName(String lastName) {
    		this.lastName = lastName;
    		return this;
    	}

    }
    
    
    //============================================
    // Manager Bean
    //============================================
    public static class Manager extends Employee {

    	private double budget = 900000.00;
    	
    	public double getBudget() {
    		return budget;
    	}
    	
    	public Manager setBudget(double budget) {
    		this.budget = budget;
    		return this;
    	}

    }

    
    //============================================
    // Department Bean
    //============================================
    public static class Department {

    	private long deptId;
    	private Employee[] employees;
    	private final List<Employee> employeeList = new ArrayList<Employee>();
    	private final Set<Employee> employeeSet = new LinkedHashSet<Employee>();
    	private final Map<Long,Employee> employeeMap = new LinkedHashMap<Long, Employee>();
    	private int[] deptFloors = {5, 9, 17};

    	public long getDeptId() {
    		return deptId;
    	}
    	
    	public Department setDeptId(long deptId) {
    		this.deptId = deptId;
    		return this;
    	}

    	public Employee[] getEmployees() {
    		return employees;
    	}

    	public Department setEmployees(Employee ... employees) {
    		this.employees = employees;
    		employeeList.addAll(Arrays.asList(employees));
    		employeeSet.addAll(Arrays.asList(employees));
    		for (Employee employee : employees) {
    			employeeMap.put(employee.getId(), employee);
    		}
    		return this;
    	}
    	
    	public List<Employee> getEmployeeList() {
    		return employeeList;
    	}
    	
    	public Set<Employee> getEmployeeSet() {
    		return employeeSet;
    	}

    	public Map<Long, Employee> getEmployeeMap() {
    		return employeeMap;
    	}
    	
    	public int[] getDeptFloors() {
    		return deptFloors;
    	}
    	
    	public Department setDeptFloors(int[] deptFloors) {
    		this.deptFloors = deptFloors;
    		return this;
    	}

    }

}
