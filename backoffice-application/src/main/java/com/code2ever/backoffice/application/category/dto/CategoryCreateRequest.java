package com.code2ever.backoffice.application.category.dto;

public record CategoryCreateRequest(
        String name,
        Boolean active
) {}