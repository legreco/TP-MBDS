/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author MAC GRECO
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll",
    query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findById",
    query = "SELECT c FROM CompteBancaire c WHERE c.id = :id"),
    @NamedQuery(name = "CompteBancaire.findByNom",
    query = "SELECT c FROM CompteBancaire c WHERE c.nomProprio = :nomProprio")})
public class CompteBancaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tp3.CompteBancaire[ id=" + id + " ]";
    }
    
        private double solde;

    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public double getSolde() {
        return solde;
    }

    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(double solde) {
        this.solde = solde;
    }

        private String nom;

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    public CompteBancaire(String nom,double solde ) {
        this.solde = solde;
        this.nom = nom;
    }

      public void deposer(int montant) {
    solde += montant;
  }
  
  public int retirer(int montant) {
    if (montant < solde) {
      solde -= montant;
      return montant;
    } else {
      return 0;
    }
  }
}
