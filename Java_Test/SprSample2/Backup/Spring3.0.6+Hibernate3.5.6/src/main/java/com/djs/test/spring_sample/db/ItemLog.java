
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
@Table(name = "TEST_ITEM_LOG")
// This is Hibernate way to set Ehcache.
// For Spring 2.5 + Hibernate 3.5, use ehcache-core 2.2.0.
// For > 2.2.0, it has exception "invalid LOC header (bad signature)".
// For < 2.0.0, it cannot recognize @Cache(region).
// @Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "localCache")
public class ItemLog
{
	// HibernateTemplate does not use default value in Java codes for field which has set default value in SQL definition. 

	@Id
	// Set "hibernate.hbm2ddl.auto=update" in Spring xml to enable auto GeneratedValue.
	// @GeneratedValue
	// If use own sequence, and not set allocationSize, the default value is 50.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "SEQ_TEST_ITEM_LOG", allocationSize = 1)
	@Column(name = "SEQ_ID", nullable = false)
	private Integer seqId;
	@Column(name = "MESSAGE")
	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOG_TIME")
	private Date logTime;

	public Integer getSeqId()
	{
		return this.seqId;
	}

	public void setSeqId( Integer seqId )
	{
		this.seqId = seqId;
	}

	public String getMessage()
	{
		return this.message;
	}

	public void setMessage( String message )
	{
		this.message = message;
	}

	public Date getLogTime()
	{
		return this.logTime;
	}

	public void setLogTime( Date logTime )
	{
		this.logTime = logTime;
	}

	@Override
	public String toString()
	{
		return "<" + seqId + "," + message + "," + logTime + ">";
	}
}
