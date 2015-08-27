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
import seisus.model.ZDirVector;
import seisus.model.ctrl.exceptions.NonexistentEntityException;
import seisus.model.ctrl.exceptions.PreexistingEntityException;

/**
 *
 * @author moroz
 */
public class ZDirVectorJpaController implements Serializable {

    public ZDirVectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ZDirVector ZDirVector) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ZDirVector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findZDirVector(ZDirVector.getFrequency()) != null) {
                throw new PreexistingEntityException("ZDirVector " + ZDirVector + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ZDirVector ZDirVector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ZDirVector = em.merge(ZDirVector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Frequency id = ZDirVector.getFrequency();
                if (findZDirVector(id) == null) {
                    throw new NonexistentEntityException("The zDirVector with id " + id + " no longer exists.");
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
            ZDirVector ZDirVector;
            try {
                ZDirVector = em.getReference(ZDirVector.class, id);
                ZDirVector.getFrequency();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ZDirVector with id " + id + " no longer exists.", enfe);
            }
            em.remove(ZDirVector);
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

    public List<ZDirVector> findZDirVectorEntities() {
        return findZDirVectorEntities(true, -1, -1);
    }

    public List<ZDirVector> findZDirVectorEntities(int maxResults, int firstResult) {
        return findZDirVectorEntities(false, maxResults, firstResult);
    }

    private List<ZDirVector> findZDirVectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ZDirVector.class));
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

    public ZDirVector findZDirVector(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ZDirVector.class, id);
        } finally {
            em.close();
        }
    }

    public ZDirVector findZDirVector(Frequency id) {
        return findZDirVector(id.getId());
    }

    public int getZDirVectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ZDirVector> rt = cq.from(ZDirVector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
