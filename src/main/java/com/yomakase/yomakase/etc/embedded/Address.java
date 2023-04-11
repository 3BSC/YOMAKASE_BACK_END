package com.yomakase.yomakase.etc.embedded;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    @Basic
    @Column(name = "1depth")
    private String depth1;

    @Basic
    @Column(name = "2depth")
    private String depth2;

    @Basic
    @Column(name = "3depth")
    private String depth3;

    @Basic
    @Column(name = "address_name")
    private String addressName;
}
