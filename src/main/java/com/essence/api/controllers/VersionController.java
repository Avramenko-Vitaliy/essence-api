package com.essence.api.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Api.ROOT_PATH)
public class VersionController {

    @Value("${app.version:unknown}")
    private String version;

    @Value("${build.time:unknown}")
    private String buildTime;

    @GetMapping(Api.Build.VERSION)
    public String getVersion() {
        return this.version;
    }

    @GetMapping(Api.Build.BUILD_TIME)
    public String getTime() {
        return this.buildTime;
    }
}
