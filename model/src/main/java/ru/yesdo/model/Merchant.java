package ru.yesdo.model;

import java.util.Set;

/**
 * Created by lameroot on 13.12.14.
 * Класс мерчанта, компаниие. Это один из основных классов, который будет использоваться.
 * Должна быть отдельная вкладка для управления мерчантами. Должно быть разграничение по пермиссиям.
 * По сути управление мерчантами должно сводится к созданию и редактированию. Примерно тоже самое что в нашей админке.
 */
public class Merchant {

    private Long id;
    private Set<Activity> activities;//список активити в которые может вступать мерчант. кол-во активити должно ограничиваться пермиссией
    private Set<Product> products;//кол-во продуктов, которые производит мерчант
    private Set<User> users;//список пользоватлей для этого мерчанта
    private Contact contact;//контактная информация для мерчанта
    private Set<Tag> tags;//список тэгов, по которым может осуществляться поиск н-р, должно ограничиваться пермиссией кол-во
    private Set<Media> medias;//список меди-ресурсов для данного мерчанта, это могут быть загружаемые видео или картинки
    private Blog description;//описание компании
    private Set<Blog> blogs;//список блогов, которые есть у мерчанта
    private Set<Option> options;//список пермиссий, которыми он обладает
    private boolean enabled;//доступен или нет
}