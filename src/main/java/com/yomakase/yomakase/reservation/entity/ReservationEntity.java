package com.yomakase.yomakase.reservation.entity;

import com.yomakase.yomakase.etc.entity.BaseEntity;
import com.yomakase.yomakase.restaurant.entity.RestaurantEntity;
import com.yomakase.yomakase.user.entity.UserEntity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
@SQLDelete(sql = "UPDATE user SET is_deleted = 1 WHERE id = ?")
@Table(name = "reservation")
public class ReservationEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reserve_date")
    private Date reserveDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationEntity)) {
            return false;
        }

        ReservationEntity other = (ReservationEntity) o;
        return Objects.equals(getId(), other.getId()) && Objects.equals(getRestaurant(), other.getRestaurant())
                && Objects.equals(getUser(), other.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRestaurant(), getUser());
    }
}
