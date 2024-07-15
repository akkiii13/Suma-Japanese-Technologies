package com.sjtev.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sjtev.entities.helper.ServicingPrimaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//
//@Entity
//@Table(name = "servicings")
public class Servicing {

//	@EmbeddedId
//	private ServicingPrimaryKey servicingId;
//
//	@Column
//	private Integer numberOfFreeServicing;
//
//	@Column
//	private LocalDate dueDate;
//
//	@Column
//	private Boolean isServicingDone;
//
//	@ManyToOne
//	@MapsId("vehicleIdPK")
//	@JoinColumn(name = "vehicleId")
//	@JsonIgnore
//	private Vehicle vehicle;

}
