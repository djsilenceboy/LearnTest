
package com.djs.test.hbt_sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ClassRoom")
public class ClassRoom
{
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	@Column(name = "RoomNumber")
	private String roomNumber;

	public Integer getId()
	{
		return this.id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getRoomNumber()
	{
		return this.roomNumber;
	}

	public void setRoomNumber( String roomNumber )
	{
		this.roomNumber = roomNumber;
	}
}
