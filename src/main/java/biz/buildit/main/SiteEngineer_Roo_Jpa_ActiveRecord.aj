// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package biz.buildit.main;

import biz.buildit.main.SiteEngineer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SiteEngineer_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager SiteEngineer.entityManager;
    
    public static final EntityManager SiteEngineer.entityManager() {
        EntityManager em = new SiteEngineer().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long SiteEngineer.countSiteEngineers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SiteEngineer o", Long.class).getSingleResult();
    }
    
    public static List<SiteEngineer> SiteEngineer.findAllSiteEngineers() {
        return entityManager().createQuery("SELECT o FROM SiteEngineer o", SiteEngineer.class).getResultList();
    }
    
    public static SiteEngineer SiteEngineer.findSiteEngineer(Long id) {
        if (id == null) return null;
        return entityManager().find(SiteEngineer.class, id);
    }
    
    public static List<SiteEngineer> SiteEngineer.findSiteEngineerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SiteEngineer o", SiteEngineer.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void SiteEngineer.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void SiteEngineer.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            SiteEngineer attached = SiteEngineer.findSiteEngineer(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void SiteEngineer.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void SiteEngineer.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public SiteEngineer SiteEngineer.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SiteEngineer merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
