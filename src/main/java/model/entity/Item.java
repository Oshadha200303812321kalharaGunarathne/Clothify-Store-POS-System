package model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Item")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String itemCode;
    private String description;
    private String quantity;
    private String price;
}