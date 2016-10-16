package com.cisc181.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.cisc181.eNums.eMajor;

public class Student_Test {
	
	static ArrayList<Course> ListCourse = new ArrayList<Course>();
	static ArrayList<Semester> ListSemester = new ArrayList<Semester>();
	static ArrayList<Section> ListSection = new ArrayList<Section>();
	static ArrayList<Student> ListStudent = new ArrayList<Student>();
	
	@BeforeClass
	public static void setup() throws PersonException {
		Date DOB = new Date(0);
		
		// three Course records
		Course Course1 = new Course("103", 4, eMajor.CHEM);
		Course Course2 = new Course("207", 4, eMajor.BUSINESS);
		Course Course3 = new Course("308", 4, eMajor.PHYSICS);
		ListCourse.add(Course1);
		ListCourse.add(Course2);
		ListCourse.add(Course3);
		
		// two Semesters
		Calendar fallBegin = Calendar.getInstance();
		Calendar fallEnd = Calendar.getInstance();
		Calendar springBegin = Calendar.getInstance();
		Calendar springEnd = Calendar.getInstance();
		fallBegin.set(2015, 9, 1);
		fallEnd.set(2015, 12, 20);
		springBegin.set(2016, 2, 10);
		springEnd.set(2016, 5, 20);
		Date fallBeginDate = fallBegin.getTime();
		Date fallEndDate = fallEnd.getTime();
		Date springBeginDate = springBegin.getTime();
		Date springEndDate = springEnd.getTime();
		Semester SemFall = new Semester(fallBeginDate, fallEndDate);
		Semester SemSpring = new Semester(springBeginDate, springEndDate);
		ListSemester.add(SemFall);
		ListSemester.add(SemSpring);
		
		// two Sections for each Course & Semester
		Section SectionFC1 = new Section(Course1.getCourseID(), SemFall.getSemesterID());
		Section SectionFC2 = new Section(Course1.getCourseID(), SemFall.getSemesterID());
		Section SectionFB1 = new Section(Course2.getCourseID(), SemFall.getSemesterID());
		Section SectionFB2 = new Section(Course2.getCourseID(), SemFall.getSemesterID());
		Section SectionFP1 = new Section(Course3.getCourseID(), SemFall.getSemesterID());
		Section SectionFP2 = new Section(Course3.getCourseID(), SemFall.getSemesterID());
		Section SectionSC1 = new Section(Course1.getCourseID(), SemSpring.getSemesterID());
		Section SectionSC2 = new Section(Course1.getCourseID(), SemSpring.getSemesterID());
		Section SectionSB1 = new Section(Course2.getCourseID(), SemSpring.getSemesterID());
		Section SectionSB2 = new Section(Course2.getCourseID(), SemSpring.getSemesterID());
		Section SectionSP1 = new Section(Course3.getCourseID(), SemSpring.getSemesterID());
		Section SectionSP2 = new Section(Course3.getCourseID(), SemSpring.getSemesterID());
		ListSection.add(SectionFC1);
		ListSection.add(SectionFC2);
		ListSection.add(SectionFB1);
		ListSection.add(SectionFB2);
		ListSection.add(SectionFP1);
		ListSection.add(SectionFP2);
		ListSection.add(SectionSC1);
		ListSection.add(SectionSC2);
		ListSection.add(SectionSB1);
		ListSection.add(SectionSB2);
		ListSection.add(SectionSP1);
		ListSection.add(SectionSP2);
		
		// ten Student records
		Student A = new Student("A", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student B = new Student("B", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student C = new Student("C", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student D = new Student("D", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student E = new Student("E", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student F = new Student("F", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student G = new Student("G", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student H = new Student("H", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student I = new Student("I", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		Student J = new Student("J", "Middle", "Last", DOB, eMajor.CHEM, "Address", "1023456789", "A@gmail.com");
		ListStudent.add(A);
		ListStudent.add(B);
		ListStudent.add(C);
		ListStudent.add(D);
		ListStudent.add(E);
		ListStudent.add(F);
		ListStudent.add(G);
		ListStudent.add(H);
		ListStudent.add(I);
		ListStudent.add(J);
	}
	
	@Test
	public void test() {
		ArrayList<Enrollment> ListEnroll = new ArrayList<Enrollment>();
		for (Student i : ListStudent) {
			for (Section j : ListSection) {
				Enrollment ijEnroll = new Enrollment(i.getStudentID(), j.getSectionID());
				ListEnroll.add(ijEnroll);
			}
		}

		for (Enrollment i : ListEnroll) {
			i.SetGrade(4.0);
		}
		
		// Test GPA for each student
		for (Student stud : ListStudent) {
			int count = 0;
			double GPA = 0;
			for (Enrollment enroll : ListEnroll) {
				if (count >= 12)
					break;
				else if (enroll.getStudentID().equals(stud.getStudentID())) {
					count++;
					GPA += enroll.getGrade();
				}
			}
			GPA = GPA / 12.0;
			assertEquals(4.0, GPA, 0);
		}

		// Test GPA for each course
		for (Course cou : ListCourse) {
			int count = 0;
			double GPA = 0;
			for (Section sec : ListSection) {
				if (cou.getCourseID().equals(sec.getCourseID())) {
					for (Enrollment enroll : ListEnroll) {
						if (count >= 40)
							break;
						else if (enroll.getSectionID().equals(sec.getSectionID())) {
							count++;
							GPA += enroll.getGrade();
						}
					}
				}
			}
			//10 students in each sections, each course has 4 sections
			GPA = GPA / 40.0;
			assertEquals(4.0, GPA, 0);
		}
	}

	@Test
	public void testChangeMajor() {
		assertEquals(ListStudent.get(0).getMajor(), eMajor.CHEM);
		ListStudent.get(0).setMajor(eMajor.COMPSI);
		assertEquals(ListStudent.get(0).getMajor(),eMajor.COMPSI);
	}
}