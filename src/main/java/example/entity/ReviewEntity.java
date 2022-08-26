package example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Table(name = "review")
public class ReviewEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column
	private Date createDate;
	
	@Column
	private Date modifiedDate;
	
	@ManyToOne
	@JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_id_fk_review"))
    private ServiceEntity serviceReview;
	
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk_review"))
    private UserEntity userReview;
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
