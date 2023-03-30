package com.code.durgeshg.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  private int categoreId;
	
  @Column(name="title",length=100,nullable=false)
  private String categoryName;
  
  private String categoryDescription;
  
  @OneToMany(mappedBy="category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
  private List<Post> post=new ArrayList<>();
  
}
