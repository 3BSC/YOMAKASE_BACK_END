package com.yomakase.restaurant.entity;

import com.yomakase.etc.embedded.Address;
import com.yomakase.etc.entity.BaseEntity;
import com.yomakase.restaurant.enums.RestaurantCategory;
import com.yomakase.restaurant.embedded.ServiceTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE review SET is_deleted = 1 WHERE id=?")
@Where(clause = "is_deleted = 0")
@Table(name = "restaurant")
public class RestaurantEntity extends BaseEntity {

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "registration")
    private String registration;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private RestaurantCategory category;

    @Basic
    @Column(name = "summary")
    private String summary;

    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private Address address;

    @Embedded
    private ServiceTime serviceTime;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<PriceEntity> price;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<FacilitiesEntity> facilities;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<ClosedDaysEntity> closedDays;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantEntity)) {
            return false;
        }

        RestaurantEntity other = (RestaurantEntity) o;
        return Objects.equals(getId(), other.getId()) && Objects.equals(getName(), other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
