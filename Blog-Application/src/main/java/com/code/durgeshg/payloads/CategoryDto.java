package com.code.durgeshg.payloads;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
	
  private int categoreId;
  
  @NotEmpty
  @Size(min=4, message="min size is 4")
  private String categoryName;
  
  @NotEmpty
  @Size(min=10)
  private String categoryDescription;
}
