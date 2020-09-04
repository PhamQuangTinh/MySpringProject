package ou.phamquangtinh.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass //Class cha
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<T> implements Serializable {
    protected static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreatedBy
    private T createdBy;

    @Column
    @CreatedDate
    private LocalDateTime createdDate;

    @Column
    @LastModifiedBy
    private T modifieBy;

    @Column
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}