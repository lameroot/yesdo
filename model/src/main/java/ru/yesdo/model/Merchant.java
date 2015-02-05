package ru.yesdo.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lameroot on 13.12.14.
 * Класс мерчанта, компаниие. Это один из основных классов, который будет использоваться.
 * Должна быть отдельная вкладка для управления мерчантами. Должно быть разграничение по пермиссиям.
 * По сути управление мерчантами должно сводится к созданию и редактированию. Примерно тоже самое что в нашей админке.
 */

@Entity
@Table(name = "merchant")
@NodeEntity
public class Merchant {
	@Id
	@SequenceGenerator(name = "merchant_id_gen", sequenceName = "merchant_seq")
	@GeneratedValue(generator = "merchant_id_gen", strategy = GenerationType.SEQUENCE)
    @GraphId
	private Long id;

	private String name;
    private String title;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<User> users;

    @RelatedTo(type = "MERCHANT", direction = Direction.INCOMING)
    @Transient
    private Set<Activity> activities = new HashSet<>();//список активити в которые может вступать мерчант. кол-во активити должно ограничиваться пермиссией
    @RelatedToVia
    @Transient
    private Set<MerchantProductRelationship> merchantProducts = new HashSet<>();
    
    public MerchantProductRelationship addMerchantProduct(Product product, Long amount) {
        MerchantProductRelationship merchantProductRelationship = new MerchantProductRelationship(this,product, amount);
        merchantProducts.add(merchantProductRelationship);
        return merchantProductRelationship;
    }

    public Set<MerchantProductRelationship> getMerchantProducts() {
        return merchantProducts;
    }

    public void setMerchantProducts(Set<MerchantProductRelationship> merchantProducts) {
        this.merchantProducts = merchantProducts;
    }

//    private Contact contact;//контактная информация для мерчанта
//    private Set<Tag> tags;//список тэгов, по которым может осуществляться поиск н-р, должно ограничиваться пермиссией кол-во
//    private Set<Media> medias;//список меди-ресурсов для данного мерчанта, это могут быть загружаемые видео или картинки
//    private Blog description;//описание компании
//    private Set<Blog> blogs;//список блогов, которые есть у мерчанта
//    private Set<Option> options;//список пермиссий, которыми он обладает
//    private boolean enabled;//доступен или нет


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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
