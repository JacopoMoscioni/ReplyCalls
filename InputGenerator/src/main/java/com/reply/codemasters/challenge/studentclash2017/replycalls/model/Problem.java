package com.reply.codemasters.challenge.studentclash2017.replycalls.model;

import lombok.Value;

@Value
public class Problem {

    private final CustomerOffice issuer;

    private final int softwareSkills;

    private final int hardwareSkills;

    private final int budget;

    private final int estimatedEffort;
}
