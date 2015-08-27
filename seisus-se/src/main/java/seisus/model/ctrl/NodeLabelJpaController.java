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
import seisus.model.NodeLabel;
import seisus.model.ctrl.exceptions.NonexistentEntityException;

/**
 *
 * @author moroz
 */
public class NodeLabelJpaController implements Serializable {

    public NodeLabelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NodeLabel nodeLabel) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nodeLabel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NodeLabel nodeLabel) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nodeLabel = em.merge(nodeLabel);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = nodeLabel.getId();
                if (findNodeLabel(id) == null) {
                    throw new NonexistentEntityException("The nodeLabel with id " + id + " no longer exists.");
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
            NodeLabel nodeLabel;
            try {
                nodeLabel = em.getReference(NodeLabel.class, id);
                nodeLabel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nodeLabel with id " + id + " no longer exists.", enfe);
            }
            em.remove(nodeLabel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NodeLabel> findNodeLabelEntities() {
        return findNodeLabelEntities(true, -1, -1);
    }

    public List<NodeLabel> findNodeLabelEntities(int maxResults, int firstResult) {
        return findNodeLabelEntities(false, maxResults, firstResult);
    }

    private List<NodeLabel> findNodeLabelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NodeLabel.class));
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

    public NodeLabel findNodeLabel(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NodeLabel.class, id);
        } finally {
            em.close();
        }
    }

    public int getNodeLabelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NodeLabel> rt = cq.from(NodeLabel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
