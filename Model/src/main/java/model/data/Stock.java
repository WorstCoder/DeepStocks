package model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.dao.CandleDAO;
import model.jdbc.resourceProcessor.ReverseResourceRelation;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"binaryData", "symbols", "stockCandles"})
@ToString(exclude = {"binaryData", "stockCandles"})
@Table(name = "stocks")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stock implements Serializable {
	
	@Id
	@SerializedName("id")
	@Expose
	private Long id;
	
	@Column(name = "name")
	@SerializedName("name")
	@Expose
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "stock")
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@SerializedName("binaryData")
	@Expose
	@JsonIgnore
	@ReverseResourceRelation
	private BinaryData binaryData;
	
	@OneToMany(mappedBy = "stock")
	@LazyCollection(LazyCollectionOption.FALSE)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@SerializedName("symbols")
	@Expose
	@JsonIgnore
	@ReverseResourceRelation
	private List<Symbol> symbols;
	
	@Transient
	@OneToMany(mappedBy = "stock")
	@SerializedName("stockCandles")
	@Expose
	@JsonIgnore
	@ReverseResourceRelation
	private List<Candle> stockCandles;
	
	@Transient
	@JsonIgnore
	private CandleDAO candleDAO;
	
	@Transient
	@JsonIgnore
	private Candle lastCandle;
	
	public Stock(CandleDAO candleDAO) {
		this.candleDAO = candleDAO;
	}
	
	public Stock() {
	}
	
	public Stock(String name) {
		setName(name);
		setSymbols(new ArrayList<>());
		setStockCandles(new ArrayList<>());
	}
	
	
	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@OrderBy("epoch")
	@BatchSize(size = 1000)
	//@Where(clause= "DATE_PART('day', to_timestamp((SELECT max(c.epoch) FROM candles c)) - to_timestamp(epoch)) <= 7)")
	//@Where(clause = "epoch in (SELECT epoch from candles order by epoch desc limit 1000)")
	private List<Candle> getStockCandlesByAnnotations() {
		return stockCandles;
	}
	
	public List<Candle> getStockCandles() {
		if (stockCandles != null)
			return stockCandles;
		
		if (candleDAO == null)
			return getStockCandlesByAnnotations();
		else {
			stockCandles = candleDAO.findTopLimitByStockidOrderByEpoch(id, 1440);
			Collections.reverse(stockCandles);
		}
		return stockCandles;
	}
	
	
	public void setBinaryData(BinaryData binaryData) {
		if (this.binaryData != null) {
			this.binaryData.replace(binaryData);
		} else {
			this.binaryData = binaryData;
		}
		binaryData.setStock(this);
		binaryData.setStockId(id);
	}
	
	public void addSymbol(Symbol symbol) {
		symbol.setStock(this);
		symbol.setStockId(id);
		symbols.add(symbol);
	}
	
	public void addCandle(Candle candle) {
		candle.setStock(this);
		stockCandles.add(candle);
	}
	
	public Candle getLastCandle() {
		if (lastCandle == null) {
			List<Candle> candles = getStockCandles();
			lastCandle = candles.isEmpty() ? null : candles.get(candles.size() - 1);
		}
		return lastCandle;
	}
	
	public void setLastCandle(Candle candle) {
		lastCandle = candle;
		lastCandle.setStock(this);
	}
	
	public void setName(String name) {
		this.name = name;
		id = (long) name.hashCode();
	}
}
