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
import seisus.model.XDirVector;
import seisus.model.ctrl.exceptions.NonexistentEntityException;
import seisus.model.ctrl.exceptions.PreexistingEntityException;

/**
 *
 * @author moroz
 */
public class XDirVectorJpaController implements Serializable {

    public XDirVectorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(XDirVector XDirVector) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(XDirVector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findXDirVector(XDirVector.getFrequency()) != null) {
                throw new PreexistingEntityException("XDirVector " + XDirVector + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(XDirVector XDirVector) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            XDirVector = em.merge(XDirVector);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Frequency id = XDirVector.getFrequency();
                if (findXDirVector(id) == null) {
                    throw new NonexistentEntityException("The xDirVector with id " + id + " no longer exists.");
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
            XDirVector XDirVector;
            try {
                XDirVector = em.getReference(XDirVector.class, id);
                XDirVector.getFrequency();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The XDirVector with id " + id + " no longer exists.", enfe);
            }
            em.remove(XDirVector);
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

    public List<XDirVector> findXDirVectorEntities() {
        return findXDirVectorEntities(true, -1, -1);
    }

    public List<XDirVector> findXDirVectorEntities(int maxResults, int firstResult) {
        return findXDirVectorEntities(false, maxResults, firstResult);
    }

    private List<XDirVector> findXDirVectorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(XDirVector.class));
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

    public XDirVector findXDirVector(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(XDirVector.class, id);
        } finally {
            em.close();
        }
    }

    public XDirVector findXDirVector(Frequency id) {
        return findXDirVector(id.getId());
    }

    public int getXDirVectorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<XDirVector> rt = cq.from(XDirVector.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
