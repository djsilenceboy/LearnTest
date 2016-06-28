
package com.djs.test.spring_sample.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TEST_ITEM")
// Used for Hibernate cache. Not for ehcache-spring-annotations. 
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "localCache")
public class Item
{
	// HibernateTemplate does not use default value in Java codes for field which has set default value in SQL definition. 

	@Id
	// Set "hibernate.hbm2ddl.auto=update" in Spring xml to enable auto GeneratedValue.
	// @GeneratedValue
	// If use own sequence, and not set allocationSize, the default value is 50.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_TEST_ITEM", allocationSize = 1)
	@Column(name = "SEQ_ID", nullable = false)
	private Integer seqId;
	@Column(name = "ITEM_NAME", nullable = false)
	private String itemName;
	@Column(name = "DESCRIPTION")
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ADD_TIME")
	private Date addTime;

	public Integer getSeqId()
	{
		return this.seqId;
	}

	public void setSeqId( Integer seqId )
	{
		this.seqId = seqId;
	}

	public String getItemName()
	{
		return this.itemName;
	}

	public void setItemName( String itemName )
	{
		this.itemName = itemName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription( String description )
	{
		this.description = description;
	}

	public Date getAddTime()
	{
		return this.addTime;
	}

	public void setAddTime( Date addTime )
	{
		this.addTime = addTime;
	}

	@Override
	public String toString()
	{
		return "<" + seqId + "," + itemName + "," + description + "," + addTime + ">";
	}
}
