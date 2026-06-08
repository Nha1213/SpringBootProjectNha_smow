package com.nha.nha_smos.DTO;

import lombok.*;
import tools.jackson.databind.node.StringNode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequest {
    private String name;
    private String description;
    private String code;
    private Boolean status;
    private String createdBy;
    private String updatedBy;
}
