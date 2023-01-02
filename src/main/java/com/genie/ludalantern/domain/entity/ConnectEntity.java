package com.genie.ludalantern.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "connect")
public class ConnectEntity {
    @Id
    @Column(name = "connect_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long connectId;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(name = "lantern_count")
    int lanternCount;


    
    //유저와 풍등 일대다 관계
    @JsonManagedReference
    @OneToMany(mappedBy ="connectEntity",fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<LanternEntity> lanternEntities = new ArrayList<>();







}
