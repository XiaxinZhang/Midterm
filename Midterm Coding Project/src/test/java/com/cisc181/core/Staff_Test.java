package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eTitle;

import antlr.collections.List;

public class Staff_Test {
	
	static ArrayList<Staff> StaffList = new ArrayList<Staff>();

	@BeforeClass
	public static void setup() throws PersonException {
		Date DOB = new Date(1);
		Date hireDate = new Date();
		
		Staff A = new Staff("First","Middle", "Last", DOB, "address", "222222222", "email@gmail.com", "office hr", 1, 100000, hireDate, eTitle.MS);
		Staff B = new Staff("First","Middle", "Last", DOB, "address", "222222222", "email@gmail.com", "office hr", 1, 150000, hireDate, eTitle.MS);
		Staff C = new Staff("First","Middle", "Last", DOB, "address", "222222222", "email@gmail.com", "office hr", 1, 200000, hireDate, eTitle.MS);
		Staff D = new Staff("First","Middle", "Last", DOB, "address", "222222222", "email@gmail.com", "office hr", 1, 250000, hireDate, eTitle.MS);
		Staff E = new Staff("First","Middle", "Last", DOB, "address", "222222222", "email@gmail.com", "office hr", 1, 300000, hireDate, eTitle.MS);
		
		StaffList.add(A);
		StaffList.add(B);
		StaffList.add(C);
		StaffList.add(D);
		StaffList.add(E);
	}
	
	@Test
	public void test1() {
		double sum = 0;
		for (Staff i: StaffList)
			sum += i.getSalary();
		
		assertEquals(200000,(sum/5.0), 0);
	}
	
	@Test
	public void test2() throws PersonException {
		Date DOB = new Date(1);
		Date hireDate = new Date();
		Staff F = new Staff("First", "Middle", "Last", DOB, "address", "01234567", "email@gmail.com","office hr", 1, 100000, hireDate, eTitle.MS);
		System.out.println(F.getPhone());
	}
	
	@Test
	public void test3() throws PersonException {
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1900, 2, 30);
		Date DOB = birthDate.getTime();
		Date hireDate = new Date();
		Staff G = new Staff("First", "Middle", "Last", DOB, "address", "1023456789", "email@gmail.com", "office hr", 1, 100000, hireDate, eTitle.MS);
		System.out.println(G.getDOB());
	}

}
