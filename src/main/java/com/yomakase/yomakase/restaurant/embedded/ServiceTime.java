package com.yomakase.yomakase.restaurant.embedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;

@Getter
@Embeddable
public class ServiceTime {

    @Temporal(TemporalType.TIME)
    @Column(name = "service_start")
    private Date serviceStart;

    @Temporal(TemporalType.TIME)
    @Column(name = "service_end")
    private Date serviceEnd;

    @Temporal(TemporalType.TIME)
    @Column(name = "break_start")
    private Date breakStart;

    @Temporal(TemporalType.TIME)
    @Column(name = "break_end")
    private Date breakEnd;
}
