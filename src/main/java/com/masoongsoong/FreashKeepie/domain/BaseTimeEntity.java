package com.masoongsoong.FreashKeepie.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity들이 BaseTimeEntity를 상속할 경우 이 클래스의 필드들도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) //Auditing(누가 언제 변경했는지 감시)기능 포함
public abstract class BaseTimeEntity { //Entity들의 생성시간 및 수정 시간을 자동으로 관리하는 역할
    @CreatedDate
    @Column(updatable = false) // 수정 불가능
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
