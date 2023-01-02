package com.genie.ludalantern.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "lantern")
public class LanternEntity {
    @Id
    @Column(name = "lantern_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lanternId;

    @Column(name = "lantern_number")
    int lantetnNumber;


    @Column(name = "wish",length = 300)
    String wish;

    @Column(name="isPublic")
    boolean isPublic;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="connectId")
    @ToString.Exclude
    private ConnectEntity connectEntity;


    @Builder
    public LanternEntity(int lantetnNumber, String wish,boolean isPublic, ConnectEntity connectEntity){
        this.isPublic = isPublic;
        this.lantetnNumber = lantetnNumber;
        this.wish = wish;

        this.connectEntity = connectEntity;
        this.connectEntity.getLanternEntities().add(this);

    }


}


