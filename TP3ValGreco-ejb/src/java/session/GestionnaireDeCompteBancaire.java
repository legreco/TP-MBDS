/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tp3.CompteBancaire;

/**
 *
 * @author MAC GRECO
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {
@PersistenceContext
  private EntityManager em;
    public void creerCompte(CompteBancaire c) 
    {
        persist(c);
    }
    
  public void persist(CompteBancaire compteBancaire) {
    em.persist(compteBancaire);
  }  
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<CompteBancaire> getAllComptes() {
        
        
     return (List<CompteBancaire>)em.createNamedQuery("CompteBancaire.findAll").getResultList();
    }
  public void update(CompteBancaire compteBancaire) {
    em.merge(compteBancaire);
  }
  public CompteBancaire findById(long id) {
    return em.find(CompteBancaire.class, id);
  }
  public void transferer(CompteBancaire source, CompteBancaire destination, 
          int montant) {
    int val = source.retirer(montant);
    if (val == 0) {
      // La source n'a plus assez d'argent !!
        
      // Il faudrait afficher un message d'erreur.
      return;
    }
    destination.deposer(montant);
    update(source);
    update(destination);
  }
   public void supprimer(CompteBancaire compteBancaire) {
    em.remove(em.merge(compteBancaire));
  }
    public void creerComptesTest() {
   creerCompte(new CompteBancaire("John Lennon", 150_000));
   creerCompte(new CompteBancaire("Paul McCartney", 950_000));
   creerCompte(new CompteBancaire("Ringo Starr", 20_000));
   creerCompte(new CompteBancaire("Georges Harrisson", 100_000));
}
    
}
