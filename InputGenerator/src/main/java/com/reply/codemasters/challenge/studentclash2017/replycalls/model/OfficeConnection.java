package com.reply.codemasters.challenge.studentclash2017.replycalls.model;

import lombok.Value;

@Value
public class OfficeConnection {

    private final ReplyOffice replyOffice;

    private final CustomerOffice customerOffice;

    private final int networkHiccups;

    private final int concurrentConnections;
}
