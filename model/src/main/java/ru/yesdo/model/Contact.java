package ru.yesdo.model;

import java.util.Map;
import java.util.Set;

/**
 * Created by lameroot on 13.12.14.
 * Contact - контактная информация о пользователе или о компании
 * Пока многие параметры здесь указаны чисто приблизительно, поэтому можно использовать только имя и фио
 * Должен хранится в БД
 */
public class Contact {

    private Long id;
    private String firstName;//имя
    private String lastName;//фамилия
    private Set<String> phones;//список телефонов, просто текст через запятую
    private Set<String> emails;//список почтовых адресов, просто через запятую
    private Set<String> skypes;//список скайпов
    private Set<String> socials;//список данных на соцсети
    private Location location;//гео-данные
    private User user;//собственное пользователь, для которого делаем, может быть нулл, если это для мерчанта
    private Merchant merchant;//мерчант, если это контакт для него
    private Map<String,String> params;//список доп/ параметров
}
