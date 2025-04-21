package med.vol.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.dto.AddressData;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adress {
    private String adress;

    private String postalCode;

    private String city;

    private String province;

    public Adress(AddressData adress) {
        this.adress = adress.address();
        this.postalCode = adress.postalCode();
        this.city = adress.city();
        this.province = adress.province();
    }

    public void updateAddress(AddressData updatedAddress) {
        if (updatedAddress.address() != null) {
            this.adress = updatedAddress.address();
        }
        if (updatedAddress.postalCode() != null) {
            this.postalCode = updatedAddress.postalCode();
        }
        if (updatedAddress.city() != null) {
            this.city = updatedAddress.city();
        }

        if (updatedAddress.province() != null) {
            this.province = updatedAddress.province();
        }
    }
}
