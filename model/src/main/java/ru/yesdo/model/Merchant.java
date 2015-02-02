package ru.yesdo.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lameroot on 13.12.14.
 * Класс мерчанта, компаниие. Это один из основных классов, который будет использоваться.
 * Должна быть отдельная вкладка для управления мерчантами. Должно быть разграничение по пермиссиям.
 * По сути управление мерчантами должно сводится к созданию и редактированию. Примерно тоже самое что в нашей админке.
 */
@Entity
@Table(name = "merchant")
public class Merchant {
	@Id
	@SequenceGenerator(name = "merchant_id_gen", sequenceName = "merchant_seq")
	@GeneratedValue(generator = "merchant_id_gen", strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<User> users;

//    private Set<Activity> activities;//список активити в которые может вступать мерчант. кол-во активити должно ограничиваться пермиссией
//    private Set<Product> products;//кол-во продуктов, которые производит мерчант
//    private Set<User> users;//список пользоватлей для этого мерчанта
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
}
