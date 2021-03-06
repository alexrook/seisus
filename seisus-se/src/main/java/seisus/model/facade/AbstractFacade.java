package seisus.model.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;

public abstract class AbstractFacade<T> {

    private final Class<T> entityClass;
    private final int[] lastRange = {0, 0};

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public T edit(T entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    public T save(T entity) {
        return edit(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T get(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public T get(Object id, boolean throwErrorIfNull) throws EntityNotFoundException {
        T result = get(id);

        if ((result != null) || (!throwErrorIfNull)) {
            return result;
        } else {

            throw new EntityNotFoundException("entity with class="
                    + entityClass.getName()
                    + ", and id=" + id + " not found");
        }

    }

    public <E> Collection addTo(Collection<E> collection, E member) {
        if (collection != null) {
            collection.add(member);
        } else {
            collection = new ArrayList<>();
            collection.add(member);
        }
        return collection;
    }

    public List<T> list() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public int[] getLastQueryRange() {
        return lastRange;
    }

    private void setLastQueryRange(Query query, List lastQueryResult) {
        lastRange[0] = query.getFirstResult();
        lastRange[1] = lastRange[0] + lastQueryResult.size();
    }

    public List<T> listByRange(int[] range) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        List<T> result = q.getResultList();
        setLastQueryRange(q,result);
        return result;
    }

    public int count() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
