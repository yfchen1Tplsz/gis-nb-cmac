package com.projecty.core.output.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationTreeOutput {
    private UUID id;
    private String name;
    private String attribution;
    private List<OrganizationTreeOutput> children;
}
