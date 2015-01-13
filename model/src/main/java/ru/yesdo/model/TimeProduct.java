package ru.yesdo.model;

import java.util.Date;

/**
 * Created by lameroot on 13.12.14.
 * Время в которое действует продукт. То есть его время начало и конца
 */
public class TimeProduct {

    private Date start;//начало
    private Date finish;//окончание
    private Date autoProlong;//производить авто-пролонгирование продукта на определённую дату
}
