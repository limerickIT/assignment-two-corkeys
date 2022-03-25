/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sd4.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Beer implements Serializable   {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private long id;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private long brewery_id;
    
    @NotBlank(message = "{beer.nameNotEmpty}")    
    private String name;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cat_id;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer style_id;
    
    private Double abv;
    @NotBlank(message = "{beer.abvNotEmpty}")
    
    private Double ibu;
    @NotBlank(message = "{beer.ibuNotEmpty}")
    
    private Double srm;
    @NotBlank(message = "{beer.srmNotEmpty}")
    
    @Lob 
    @NotBlank(message = "{beer.descriptionNotEmpty}")
    private String description;  

    private Integer add_user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_mod;

    private String image;
    
    @NotNull(message = "{beer.buyNotNull}")
    private Double buy_price;
    
    @NotNull(message = "{beer.sellNotNull}")
    private Double sell_price;

}
