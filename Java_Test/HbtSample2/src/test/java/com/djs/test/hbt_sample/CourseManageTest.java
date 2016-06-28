
package com.djs.test.hbt_sample;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CourseManageTest
{
	private final Logger log = Logger.getLogger( CourseManageTest.class );

	CourseManage cm = null;

	@Before
	public void setup()
	{
		try
		{
			cm = new CourseManage();
			cm.setup();
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@After
	public void clean()
	{
		try
		{
			cm.clean();
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void newClassRoom()
	{
		try
		{
			ClassRoom classroom = cm.newClassRoom( "101" );

			log.debug( "ClassRoom: RoomNumber = " + classroom.getRoomNumber() );

			cm.saveOrUpdate( classroom );

			log.debug( "ClassRoom: ID = " + classroom.getId() );
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	// @Ignore("")
	@Test
	public void getClassRoom()
	{
		try
		{
			Integer id = new Integer( 11 );

			log.debug( "ClassRoom: ID = " + id );

			ClassRoom classroom = cm.getClassRoom( id );

			log.debug( "ClassRoom: RoomNumber = " + classroom.getRoomNumber() );

			for (Object item : classroom.getCourses())
			{
				Course course = (Course)item;

				log.debug( "ClassRoom: Course: ID = " + course.getId() );
				log.debug( "ClassRoom: Course: Name = " + course.getName() );
			}
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void getAllClassRooms()
	{
		try
		{
			List classrooms = cm.getAllClassRooms();

			log.debug( "ClassRooms = " + classrooms.size() );

			for (Object item : classrooms)
			{
				ClassRoom classroom = (ClassRoom)item;

				log.debug( "ClassRoom: ID = " + classroom.getId() );
				log.debug( "ClassRoom: RoomNumber = " + classroom.getRoomNumber() );

				for (Object item2 : classroom.getCourses())
				{
					Course course = (Course)item2;

					log.debug( "ClassRoom: Course: ID = " + course.getId() );
					log.debug( "ClassRoom: Course: Name = " + course.getName() );
				}
			}
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void getClassRooms()
	{
		try
		{
			List classrooms = cm.getClassRooms( "101" );

			log.debug( "ClassRooms = " + classrooms.size() );

			for (Object item : classrooms)
			{
				ClassRoom classroom = (ClassRoom)item;

				log.debug( "ClassRoom: ID = " + classroom.getId() );
				log.debug( "ClassRoom: RoomNumber = " + classroom.getRoomNumber() );

				for (Object item2 : classroom.getCourses())
				{
					Course course = (Course)item2;

					log.debug( "ClassRoom: Course: ID = " + course.getId() );
					log.debug( "ClassRoom: Course: Name = " + course.getName() );
				}
			}
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void newCourseForClassRoom()
	{
		try
		{
			Integer id = new Integer( 13 );

			log.debug( "ClassRoom: ID = " + id );

			ClassRoom classroom = cm.getClassRoom( id );

			log.debug( "ClassRoom: RoomNumber = " + classroom.getRoomNumber() );

			Course course = cm.newCourse( "Chinese", classroom );

			log.debug( "Course: Name = " + course.getName() );

			classroom.getCourses().add( course );

			// Also insert new course.
			cm.saveOrUpdate( classroom );

			for (Object item : classroom.getCourses())
			{
				Course course2 = (Course)item;

				log.debug( "ClassRoom: Course: ID = " + course2.getId() );
				log.debug( "ClassRoom: Course: Name = " + course2.getName() );
			}
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void newCourse()
	{
		try
		{
			Integer id = new Integer( 11 );

			log.debug( "ClassRoom: ID = " + id );

			ClassRoom classroom = cm.getClassRoom( id );

			log.debug( "ClassRoom: RoomNumber = " + classroom.getRoomNumber() );

			Course course = cm.newCourse( "Math", classroom );

			log.debug( "Course: Name = " + course.getName() );
			log.debug( "Course: ClassRoom: ID = " + course.getClassroom().getId() );
			log.debug( "Course: ClassRoom: RoomNumber = " + course.getClassroom().getRoomNumber() );

			cm.saveOrUpdate( course );

			log.debug( "Course: ID = " + course.getId() );
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void getCourse()
	{
		try
		{
			Integer id = new Integer( 1 );

			log.debug( "Course: ID = " + id );

			Course course = cm.getCourse( id );

			log.debug( "Course: Name = " + course.getName() );
			log.debug( "Course: ClassRoom: ID = " + course.getClassroom().getId() );
			log.debug( "Course: ClassRoom: RoomNumber = " + course.getClassroom().getRoomNumber() );
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void newTeacher()
	{
		try
		{
			Integer id = new Integer( 1 );

			log.debug( "Course: ID = " + id );

			Course course = cm.getCourse( id );

			log.debug( "Course: Name = " + course.getName() );

			Teacher teacher = cm.newTeacher( "Sarah", "F", 39, course );

			log.debug( "Teacher: Name = " + teacher.getName() );
			log.debug( "Teacher: Sex = " + teacher.getSex() );
			log.debug( "Teacher: Age = " + teacher.getAge() );
			log.debug( "Teacher: Course: ID = " + teacher.getCourse().getId() );
			log.debug( "Teacher: Course: Name = " + teacher.getCourse().getName() );

			cm.saveOrUpdate( teacher );

			log.debug( "Teacher: ID = " + teacher.getId() );
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void getTeacher()
	{
		try
		{
			Integer id = new Integer( 1 );

			log.debug( "Teacher: ID = " + id );

			Teacher teacher = cm.getTeacher( id );

			log.debug( "Teacher: Name = " + teacher.getName() );
			log.debug( "Teacher: Sex = " + teacher.getSex() );
			log.debug( "Teacher: Age = " + teacher.getAge() );
			log.debug( "Teacher: Course: ID = " + teacher.getCourse().getId() );
			log.debug( "Teacher: Course: Name = " + teacher.getCourse().getName() );
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void newStudent()
	{
		try
		{
			Integer id = new Integer( 1 );

			log.debug( "Course: ID = " + id );

			Course course = cm.getCourse( id );

			log.debug( "Course: Name = " + course.getName() );

			Student student = cm.newStudent( "Peter", "M", 10, course );

			log.debug( "Student: Name = " + student.getName() );
			log.debug( "Student: Sex = " + student.getSex() );
			log.debug( "Student: Age = " + student.getAge() );
			log.debug( "Student: Course: ID = " + student.getCourse().getId() );
			log.debug( "Student: Course: Name = " + student.getCourse().getName() );

			cm.saveOrUpdate( student );

			log.debug( "Student: ID = " + student.getId() );
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}

	@Ignore("")
	@Test
	public void getStudent()
	{
		try
		{
			Integer id = new Integer( 1 );

			log.debug( "Student: ID = " + id );

			Student student = cm.getStudent( id );

			log.debug( "Student: Name = " + student.getName() );
			log.debug( "Student: Sex = " + student.getSex() );
			log.debug( "Student: Age = " + student.getAge() );
			log.debug( "Student: Score = " + student.getScore() );
			log.debug( "Student: Course: ID = " + student.getCourse().getId() );
			log.debug( "Student: Course: Name = " + student.getCourse().getName() );
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}
}
