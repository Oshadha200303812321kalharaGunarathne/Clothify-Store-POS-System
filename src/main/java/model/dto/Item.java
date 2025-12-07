package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Item {
    private String itemCode;
    private String description;
    private String quantity;
    private String price;
}
