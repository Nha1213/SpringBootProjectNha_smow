package com.nha.nha_smos.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    @NotEmpty(message = "Name is require!")
    private String name;
    @NotEmpty(message = "Code is require!")
    private String code;
    private String description;
    private Boolean status;
}
