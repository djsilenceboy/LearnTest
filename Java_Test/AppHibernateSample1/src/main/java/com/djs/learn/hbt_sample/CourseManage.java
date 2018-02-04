
package com.djs.learn.hbt_sample;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public class CourseManage
{
	private final Logger log = Logger.getLogger( CourseManage.class );

	EntityManager em = null;
	EntityTransaction tx = null;

	public void setup()
	{
		log.debug( "Before EntityManagerFactory.createEntityManager()." );

		em = HibernateUtil.getEntityManagerFactory().createEntityManager();

		log.debug( "EntityManager = " + em );

		tx = em.getTransaction();

		log.debug( "EntityTransaction = " + tx );

		log.debug( "Before EntityTransaction.begin()." );

		tx.begin();
	}

	public void clean()
	{
		log.debug( "Before EntityTransaction.commit()." );

		tx.commit();

		log.debug( "Before EntityManager.close()." );

		em.close();

		log.debug( "EntityManager closed." );

		HibernateUtil.getEntityManagerFactory().close();

		log.debug( "EntityManagerFactory closed." );
	}

	public void persist( Object obj )
	{
		log.debug( "persist object = " + obj );

		em.persist( obj );

		log.debug( "After EntityManager.persist()." );
	}

	public ClassRoom newClassRoom( String roomNumber )
	{
		ClassRoom classroom = new ClassRoom();

		classroom.setRoomNumber( roomNumber );

		return classroom;
	}

	public ClassRoom getClassRoom( Integer id )
	{
		Query query = em.createQuery( "select a from ClassRoom a where ID = :id" );

		query.setParameter( "id", id );

		ClassRoom classroom = (ClassRoom)query.getSingleResult();

		return classroom;
	}

	public List getClassRooms( String name )
	{
		Query query = em.createQuery( "select a from ClassRoom a where RoomNumber = :roomNumber" );

		query.setParameter( "roomNumber", "101" );

		log.debug( "Query = " + query );

		List classrooms = query.getResultList();

		return classrooms;
	}

	public List getAllClassRooms()
	{
		Query query = em.createQuery( "select a from ClassRoom a" );

		log.debug( "Query = " + query );

		List classrooms = query.getResultList();

		return classrooms;
	}

	public Course newCourse( String name, ClassRoom classroom )
	{
		Course course = new Course();

		course.setName( name );

		return course;
	}

	public Course getCourse( Integer id )
	{
		Query query = em.createQuery( "select a from Course a where ID = :id" );

		query.setParameter( "id", id );

		Course course = (Course)query.getSingleResult();

		return course;
	}
}
