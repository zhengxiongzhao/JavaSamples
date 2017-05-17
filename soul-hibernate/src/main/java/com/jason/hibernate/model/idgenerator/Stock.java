package com.jason.hibernate.model.idgenerator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;


@Entity
@javax.persistence.TableGenerator(
		name="TableGaneratorName",
		table="seq_table",
		pkColumnName="pk_key",
		valueColumnName="pk_value",
		pkColumnValue="Stock",
		allocationSize=1
)
//������ΨһԼ��
@Table(name = "stock", catalog = "jasondata", uniqueConstraints = {
		@UniqueConstraint(columnNames = "STOCK_NAME"),
		@UniqueConstraint(columnNames = "STOCK_CODE") })
@DynamicUpdate(value=true)
public class Stock implements java.io.Serializable {
	private static final long serialVersionUID = 2668918601104964153L;
	private Integer stockId;
	private String stockCode;
	private String stockName;
	@Temporal(TemporalType.DATE)
	@Column
	private Date oneDate;
	@Temporal(TemporalType.TIME)
	private Date towDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date threeDate;
	public enum Yno{
		YES,NO
	}
	@Enumerated(EnumType.STRING)
	private Yno yn;
	 
	public Yno getYn() {
		return yn;
	}

	public void setYn(Yno yn) {
		this.yn = yn;
	}

	
	
	public Date getOneDate() {
		return oneDate;
	}

	public void setOneDate(Date oneDate) {
		this.oneDate = oneDate;
	}

	public Date getTowDate() {
		return towDate;
	}

	public void setTowDate(Date towDate) {
		this.towDate = towDate;
	}

	public Date getThreeDate() {
		return threeDate;
	}

	public void setThreeDate(Date threeDate) {
		this.threeDate = threeDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="TableGaneratorName")
	@Column(name = "STOCK_ID", unique = true, nullable = false)
	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	@Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
	public String getStockCode() {
		return this.stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20)
	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}