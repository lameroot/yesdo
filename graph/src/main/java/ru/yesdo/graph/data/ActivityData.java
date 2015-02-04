package ru.yesdo.graph.data;

import ru.yesdo.model.Activity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by lameroot on 25.01.15.
 */
public class ActivityData {

    private String title;

    private Set<Activity> parents;

    public String getTitle() {
        return title;
    }

    public ActivityData setTitle(String title) {
        this.title = title;
        return this;
    }



    public Set<Activity> getParents() {
        return parents;
    }

    public ActivityData setParents(Set<Activity> parents) {
        this.parents = parents;
        return this;
    }

    public ActivityData addParent(Activity parent) {
        if ( null == this.parents ) this.parents = new HashSet<>();
        this.parents.add(parent);
        return this;
    }
}