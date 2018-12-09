package com.javacourse2018.lw03.collector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class BaseCollector<T> {
    protected static final Log LOG = LogFactory.getLog(BaseCollector.class);

    protected Map<Integer, T> entities = new TreeMap<>();
    protected List<T> entityList = new ArrayList<>();

    public Integer size() {
        return this.entities.size();
    }

    public void setEntities(Map<Integer, T> products) {
        this.entities = products;
    }

    public Map<Integer, T> getEntities() {
        return entities;
    }

    public T getEntity(Integer id) throws IndexOutOfBoundsException {
        if (this.entities.containsKey(id)) {
            return this.entities.get(id);
        }
        throw new IndexOutOfBoundsException();
    }

    public void removeProduct(Integer id) throws IndexOutOfBoundsException {
        if (this.entities.containsKey(id)) {
            this.entities.remove(id);
        }
        throw new IndexOutOfBoundsException();
    }

    protected void collect() {
        this.fillEntityList();
        for (T entity: this.entityList) {
            this.entities.put(this.getId(entity), entity);
            this.printEntityInformation(entity);
        }

    }

    protected abstract Integer getId(T entity);

    protected abstract void printEntityInformation(T entity);

    protected abstract void fillEntityList();

}
