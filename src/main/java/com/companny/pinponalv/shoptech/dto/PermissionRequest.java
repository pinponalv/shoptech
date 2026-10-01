package com.companny.pinponalv.shoptech.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissionRequest {
    @Schema(example = "READ", description = "read for view documents")
    @NotBlank(message = "cannot be empty")
    private String permission;
}
