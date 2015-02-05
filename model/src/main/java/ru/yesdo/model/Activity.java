package ru.yesdo.model;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lameroot on 13.12.14.
 * Активити - род занятий, так как например спорт, парашютный спорт, танцы, боевые искусства итд.
 * Активити могут в себя вклчать любое кол-во дочерних элементов, и так же могут иметь любое кол-во родителей
 * Так например: если мы берём родителя: спорт, то его дочерними элементами являются н-р танцы : [брэкданс, танго: [спортивное танго, латинское танго]],
 * боевые исскуства : [карате: [карате-до, кькушенкай], бокс, самбо] итд. То есть в данном примере активити спорт имеет
 * два дочерних элемента, которые имеют под собой своих детей. Но эти дети имеют помимо своих основных родителей ещ> также и спорт,
 * то есть н-р карате-до имеет родителя как карате, так и боевые искусства, так и спорт. Такая связь необходима для поиска.
 * Также построена система и с мерчантами и с продуктами. Каждый мерчант может находится в нескольких активити, это опять таки
 * надо для поиска. С продуктами такая же система.
 * Данная сущность должна хранится в БД, но поиск должен быть построен на основе графов, используем neo4j . то есть помимо данных,
 * которые храним в БД, необходимо также сохранять граф узлов. Используем для этого spring-data-neo4j .
 * !!!На данном этапе делаем только сохранение в БД с помощью хибернайта и spring-data .
 */
//http://docs.spring.io/spring-data/data-neo4j/docs/3.2.1.RELEASE/reference/html/#reference_cross-store
@NodeEntity(partial = true)
//@Entity
//@Table(name = "activity")
public class Activity {

    public final static String ROOT_TITLE = "root_activity";

    @GraphId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;//
    @GraphProperty
    @Indexed
    private String title;//название
    private Set<Activity> parents;//список родителей
    private Set<Activity> child;//список дочерних
    private Set<Merchant> merchants;//список мерчантов, которые находятся в этой активити
    private Set<Product> products;//список продуктов, которые включены в эту активити


    public Activity() {
        //this.uniqueIndex = UUID.randomUUID().toString();
    }

    public Activity(String title) {
        this.title = title;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Activity> getParents() {
        return parents;
    }

    public void setParents(Set<Activity> parents) {
        this.parents = parents;
    }

    public Set<Activity> getChild() {
        return child;
    }

    public void setChild(Set<Activity> child) {
        this.child = child;
    }

    public Set<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(Set<Merchant> merchants) {
        this.merchants = merchants;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
