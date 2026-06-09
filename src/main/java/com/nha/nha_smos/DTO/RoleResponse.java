package com.nha.nha_smos.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RoleResponse {
    private Long id;
    private String name;
    private String description;
    private String test;
}
