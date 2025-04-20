package med.vol.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.AdressData;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    private String adress;
    private String postalCode;
    private String city;
    private String province;

    public Adress(AdressData adress) {
        this.adress = adress.adress();
        this.postalCode = adress.postalCode();
        this.city = adress.city();
        this.province = adress.province();
    }
}
