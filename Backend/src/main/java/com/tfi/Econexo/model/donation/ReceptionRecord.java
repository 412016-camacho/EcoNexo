package com.tfi.Econexo.model.donation;

import com.tfi.Econexo.model.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "received_donations")
@Builder
public class ReceptionRecord extends BaseEntity {

    @OneToOne
    private Donation donation;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ReceivedItem> items;
}
