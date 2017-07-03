package com.reply.codemasters.challenge.studentclash2017.replycalls.model;

import lombok.Value;

@Value
public class ReplyOffice implements Office {

    private final int id;

    private final Employee[] employees;
}
