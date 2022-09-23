package example.elasticsearch;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "service")
public class ESMService {
	
	@Id
	@Field(type = FieldType.Keyword)
	private Long id;
	
	@Field(type = FieldType.Text)
	private String name;
	
	@Field(type = FieldType.Text)
	private String description;
	
	@Field(type = FieldType.Text)
	private String image;
	
	@Field(type = FieldType.Nested, includeInParent = true)
	private List<ESMTicket> ticket;
	
	@Field(type = FieldType.Integer)
	private int reviews;
	
	@Field(type = FieldType.Integer)
	private int orders;
	
	@Field(type = FieldType.Date)
	private Date createDate;
	
	@Field(type = FieldType.Date)
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<ESMTicket> getTicket() {
		return ticket;
	}

	public void setTicket(List<ESMTicket> ticket) {
		this.ticket = ticket;
	}

	public int getReviews() {
		return reviews;
	}

	public void setReviews(int reviews) {
		this.reviews = reviews;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}
}
