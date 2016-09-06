
package com.djs.learn.hbt_sample;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.djs.learn.hbt_sample.ClassRoom;
import com.djs.learn.hbt_sample.Course;
import com.djs.learn.hbt_sample.CourseManage;

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

			cm.persist( classroom );

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

			// Also insert new course.
			cm.persist( classroom );
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

			cm.persist( course );

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
		}
		catch (Exception e)
		{
			log.error( e, e );
		}
	}
}
