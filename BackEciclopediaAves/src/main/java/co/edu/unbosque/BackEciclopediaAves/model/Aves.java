package co.edu.unbosque.BackEciclopediaAves.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aves {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String biologo;
	private String nombrecientifico;
	private String familia;
	private String orden;
	private String clase;
	private String filo;
	private String reino;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the biologo
	 */
	public String getBiologo() {
		return biologo;
	}
	/**
	 * @param biologo the biologo to set
	 */
	public void setBiologo(String biologo) {
		this.biologo = biologo;
	}
	/**
	 * @return the nombrecientifico
	 */

	/**
	 * @return the familia
	 */
	public String getFamilia() {
		return familia;
	}
	/**
	 * @return the nombrecientifico
	 */
	public String getNombrecientifico() {
		return nombrecientifico;
	}
	/**
	 * @param nombrecientifico the nombrecientifico to set
	 */
	public void setNombrecientifico(String nombrecientifico) {
		this.nombrecientifico = nombrecientifico;
	}
	/**
	 * @param familia the familia to set
	 */
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}
	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}
	/**
	 * @return the clase
	 */
	public String getClase() {
		return clase;
	}
	/**
	 * @param clase the clase to set
	 */
	public void setClase(String clase) {
		this.clase = clase;
	}
	/**
	 * @return the filo
	 */
	public String getFilo() {
		return filo;
	}
	/**
	 * @param filo the filo to set
	 */
	public void setFilo(String filo) {
		this.filo = filo;
	}
	/**
	 * @return the reino
	 */
	public String getReino() {
		return reino;
	}
	/**
	 * @param reino the reino to set
	 */
	public void setReino(String reino) {
		this.reino = reino;
	}

	
	
}
