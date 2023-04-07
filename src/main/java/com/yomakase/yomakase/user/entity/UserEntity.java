package com.yomakase.yomakase.user.entity;

import com.yomakase.yomakase.etc.entity.BaseEntity;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "nickname")
    private String nickname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_day")
    private Date birthDay;

    @Basic
    @Column(name = "is_owner")
    private Boolean isOwner;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }

        UserEntity other = (UserEntity) o;
        return Objects.equals(getId(), other.getId()) && Objects.equals(getEmail(), other.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPassword(), getEmail(), getNickname());
    }
}
