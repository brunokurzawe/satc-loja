package com.satc.satcloja.resource.representation;

import com.querydsl.core.types.Path;

import java.util.Map;

public abstract class AbstractDTO {
    public abstract Map<String, Path> expressions();
}
