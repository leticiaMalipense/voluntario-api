package com.ifsp.api.queroservoluntario.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MANAGER")
@PrimaryKeyJoinColumn(name="ID_MANAGER")
public class ManagerEntity extends UserEntity {

}
