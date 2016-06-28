
package com.djs.test.hbt_sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course
{
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	@Column(name = "Name")
	private String name;
	@Column(name = "ClassRoomId")
	private Integer classRoomId;

	public Integer getId()
	{
		return this.id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Integer getClassroom()
	{
		return this.classRoomId;
	}

	public void setClassroom( Integer classRoomId )
	{
		this.classRoomId = classRoomId;
	}
}
