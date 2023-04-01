package project.goorm.newswriteserver.business.core.member.entity;

import project.goorm.newswriteserver.business.core.common.date.CreatedAt;
import project.goorm.newswriteserver.business.core.common.date.LastModifiedAt;
import project.goorm.newswriteserver.business.core.common.deleted.Deleted;
import project.goorm.newswriteserver.business.core.member.entity.values.LastLoginIpAddress;
import project.goorm.newswriteserver.business.core.member.entity.values.LoginId;
import project.goorm.newswriteserver.business.core.member.entity.values.LoginPassword;
import project.goorm.newswriteserver.business.core.member.entity.values.Nickname;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Embedded
    private LoginId loginId;

    @Embedded
    private LoginPassword loginPassword;

    @Embedded
    private Nickname nickname;

    @Embedded
    private CreatedAt createdAt;

    @Embedded
    private LastModifiedAt lastModifiedAt;

    @Embedded
    private LastLoginIpAddress lastLoginIpAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('NORMAL', 'ADMIN')")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('TRUE', 'FALSE')")
    private Deleted deleted;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    protected Member() {
    }

    public Member(
            LoginId loginId,
            LoginPassword loginPassword,
            Nickname nickname
    ) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.nickname = nickname;
        this.lastLoginIpAddress = new LastLoginIpAddress("");
        this.createdAt = CreatedAt.initCreatedAt();
        this.lastModifiedAt = LastModifiedAt.initLastModifiedAt();
        this.role = Role.NORMAL;
        this.deleted = Deleted.FALSE;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LoginId getLoginIdAsValue() {
        return loginId;
    }

    public String getLoginId() {
        return loginId.getLoginId();
    }

    public LoginPassword getLoginPasswordAsValue() {
        return loginPassword;
    }

    public String getLoginPassword() {
        return loginPassword.getLoginPassword();
    }

    public Nickname getNicknameAsValue() {
        return nickname;
    }

    public String getNickname() {
        return nickname.getNickname();
    }

    public CreatedAt getCreatedAtAsValue() {
        return createdAt;
    }

    public Instant getCreatedAt() {
        return createdAt.getCreatedAt();
    }

    public LastModifiedAt getLastModifiedAtAsValue() {
        return lastModifiedAt;
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt == null ? null : lastModifiedAt.getLastModifiedAt();
    }

    public LastLoginIpAddress getLastLoginIpAddressAsValue() {
        return lastLoginIpAddress;
    }

    public String getLastLoginIpAddress() {
        return lastLoginIpAddress.getLastLoginIpAddress();
    }

    public Role getRole() {
        return role;
    }

    public Deleted getDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return getMemberId().equals(member.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMemberId());
    }

    @Override
    public String toString() {
        return memberId.toString();
    }
}
