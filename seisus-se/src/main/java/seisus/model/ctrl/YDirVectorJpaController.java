/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seisus.model.ctrl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import seisus.model.Frequency;
import seisus.model.YDirVector;
import seisus.model.ctrl.exceptions.NonexistentEntityException;
import seisus.model.ctrl.exceptions.PreexistingEntityException;

/**
 *
 * @author moroz
 */
public class YDirVectorJpaController implements Serializable {

    public YDirVectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(YDirVector YDirVector) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(YDirVector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findYDirVector(YDirVector.getFrequency()) != null) {
                throw new PreexistingEntityException("YDirVector " + YDirVector + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(YDirVector YDirVector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            YDirVector = em.merge(YDirVector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Frequency id = YDirVector.getFrequency();
                if (findYDirVector(id) == null) {
                    throw new NonexistentEntityException("The yDirVector with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            YDirVector YDirVector;
            try {
                YDirVector = em.getReference(YDirVector.class, id);
                YDirVector.getFrequency();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The YDirVector with id " + id + " no longer exists.", enfe);
            }
            em.remove(YDirVector);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Frequency id) throws NonexistentEntityException {
        destroy(id.getId());
    }

    public List<YDirVector> findYDirVectorEntities() {
        return findYDirVectorEntities(true, -1, -1);
    }

    public List<YDirVector> findYDirVectorEntities(int maxResults, int firstResult) {
        return findYDirVectorEntities(false, maxResults, firstResult);
    }

    private List<YDirVector> findYDirVectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(YDirVector.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public YDirVector findYDirVector(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(YDirVector.class, id);
        } finally {
            em.close();
        }
    }

    public YDirVector findYDirVector(Frequency id) {
        return findYDirVector(id.getId());
    }

    public int getYDirVectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<YDirVector> rt = cq.from(YDirVector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
