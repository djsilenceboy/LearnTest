
package com.djs.test.hbt_sample;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

public class CourseManage
{
	private final Logger log = Logger.getLogger( CourseManage.class );

	Session session = null;
	Transaction tx = null;
	Statistics stats = null;

	public void setup()
	{
		stats = HibernateUtil.getSessionFactory().getStatistics();

		log.debug( "Before SessionFactory.openSession()." );

		session = HibernateUtil.getSessionFactory().openSession();

		log.debug( "Session = " + session );

		tx = session.beginTransaction();

		log.debug( "Transaction = " + tx );
	}

	public void clean()
	{
		log.debug( "Before Transaction.commit()." );

		tx.commit();

		/*
		log.debug( "Before Session.flush()." );

		session.flush();
		*/

		log.debug( "Before Session.close()." );

		session.close();

		log.debug( "Session closed." );

		log.debug( "Before Statistics.logSummary()." );

		stats.logSummary();

		HibernateUtil.getSessionFactory().close();

		log.debug( "SessionFactory closed." );
	}

	public void saveOrUpdate( Object obj )
	{
		log.debug( "saveOrUpdate object = " + obj );

		session.saveOrUpdate( obj );

		log.debug( "After Session.saveOrUpdate()." );

		// session.flush();
	}

	public void save( Object obj )
	{
		log.debug( "save object = " + obj );

		session.save( obj );

		log.debug( "After Session.save()." );

		// session.flush();
	}

	public ClassRoom newClassRoom( String roomNumber )
	{
		ClassRoom classroom = new ClassRoom();

		classroom.setRoomNumber( roomNumber );

		return classroom;
	}

	public ClassRoom getClassRoom( Integer id )
	{
		ClassRoom classroom = (ClassRoom)session.load( ClassRoom.class, id );

		return classroom;
	}

	public List getClassRooms( String name )
	{
		Query query = session.createQuery( "from ClassRoom where RoomNumber = :roomNumber" );

		query.setString( "roomNumber", "101" );

		log.debug( "Query = " + query.getQueryString() );

		List classrooms = query.list();

		return classrooms;
	}

	public List getAllClassRooms()
	{
		Query query = session.createQuery( "from ClassRoom" );

		log.debug( "Query = " + query.getQueryString() );

		List classrooms = query.list();

		return classrooms;
	}

	public Course newCourse( String name, ClassRoom classroom )
	{
		Course course = new Course();

		course.setName( name );
		course.setClassroom( classroom );

		return course;
	}

	public Course getCourse( Integer id )
	{
		Course course = (Course)session.load( Course.class, id );

		return course;
	}

	public Teacher newTeacher( String name, String sex, int age, Course course )
	{
		Teacher teacher = new Teacher();

		teacher.setName( name );
		teacher.setSex( sex );
		teacher.setAge( age );
		teacher.setCourse( course );

		return teacher;
	}

	public Teacher getTeacher( Integer id )
	{
		Teacher teacher = (Teacher)session.load( Teacher.class, id );

		return teacher;
	}

	public Student newStudent( String name, String sex, int age, Course course )
	{
		Student student = new Student();

		student.setName( name );
		student.setSex( sex );
		student.setAge( age );
		student.setCourse( course );

		return student;
	}

	public Student getStudent( Integer id )
	{
		log.debug( "Student: ID = " + id );

		Student student = (Student)session.load( Student.class, id );

		return student;
	}
}
