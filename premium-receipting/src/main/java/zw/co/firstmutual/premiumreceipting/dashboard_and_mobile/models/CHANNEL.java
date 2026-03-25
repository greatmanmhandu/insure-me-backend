package zw.co.firstmutual.premiumreceipting.dashboard_and_mobile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CHANNEL {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String color;
    private String vehicleNumberPlates;
    private String size;
    private String frontCarPicture;
    private String backCarPicture;
    private Long userId;
}




