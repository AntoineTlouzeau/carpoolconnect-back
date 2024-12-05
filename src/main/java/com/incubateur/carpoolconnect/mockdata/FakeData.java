package com.incubateur.carpoolconnect.mockdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class FakeData implements CommandLineRunner {

    private final DataSource dataSource;

    public FakeData(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            String sqlStatementAddress =
                    "INSERT INTO carpoolconnect.address (id, number, street, city, latitude, longitude, zipcode) VALUES " +
                            "(100, 12, 'Avenue Saint-Dominique', 'Dijon', 47.32158, 5.04147, 21000), " +
                            "(101, 24, 'Passage Monsieur-le-Prince', 'Villeneuve-d''Ascq', 50.6238, 3.13097, 59650), " +
                            "(102, 8, 'Place des Grands Augustins', 'Neuilly-sur-Seine', 48.88483, 2.26851, 92200), " +
                            "(103, 56, 'Rue Pastourelle', 'Saint-Denis', 48.93618, 2.35744, 93200), " +
                            "(104, 20, 'Passage de Paris', 'Le Tampon', -21.2786, 55.5178, 97430), " +
                            "(105, 17, 'Quai Mouffetard', 'Le Havre', 49.49437, 0.107929, 76600), " +
                            "(106, 33, 'Impasse Bonaparte', 'Pau', 43.2951, -0.370797, 64000), " +
                            "(107, 45, 'Boulevard Saint-Honoré', 'Villejuif', 48.7939, 2.35992, 94800), " +
                            "(108, 2, 'Boulevard du Faubourg Saint-Honoré', 'Troyes', 48.2973, 4.0744, 10000), " +
                            "(109, 61, 'Avenue Saint-Dominique', 'Lyon', 45.76404, 4.83566, 69000), " +
                            "(110, 29, 'Rue Delesseux', 'Levallois-Perret', 48.89389, 2.28864, 92300), " +
                            "(111, 9, 'Impasse Joubert', 'Aulnay-sous-Bois', 48.9385, 2.4907, 93600), " +
                            "(112, 31, 'Quai du Faubourg Saint-Honoré', 'Saint-Nazaire', 47.28549, -2.20242, 44600), " +
                            "(113, 67, 'Quai du Dahomey', 'Montauban', 44.01758, 1.35499, 82000), " +
                            "(114, 73, 'Quai de la Huchette', 'Dunkerque', 51.03437, 2.37725, 59140), " +
                            "(115, 15, 'Quai de Rivoli', 'Clermont-Ferrand', 45.77722, 3.087025, 63000), " +
                            "(116, 51, 'Quai Saint-Dominique', 'Hyères', 43.12031, 6.12888, 83400), " +
                            "(117, 37, 'Place de la Huchette', 'Nancy', 48.69205, 6.18442, 54000), " +
                            "(118, 88, 'Rue Mouffetard', 'Versailles', 48.80141, 2.130122, 78000), " +
                            "(119, 4, 'Quai d''Argenteuil', 'Cannes', 43.552847, 7.017369, 06400);" ;

            String sqlStatementCars =
                    "INSERT INTO carpoolconnect.cars (id, brand, model, color, user_id) VALUES " +
                            "(100, 'Peugeot', '208', 'Red', 1), " +
                            "(101, 'Renault', 'Clio', 'Blue', 1), " +
                            "(102, 'Citroen', 'C3', 'White', 1), " +
                            "(103, 'Volkswagen', 'Golf', 'Black', 1), " +
                            "(104, 'Toyota', 'Corolla', 'Silver', 1), " +
                            "(105, 'Honda', 'Civic', 'Grey', 1), " +
                            "(106, 'Ford', 'Focus', 'Yellow', 1), " +
                            "(107, 'Nissan', 'Qashqai', 'Green', 1), " +
                            "(108, 'BMW', '3 Series', 'Navy', 1), " +
                            "(109, 'Mercedes-Benz', 'C-Class', 'Maroon', 1), " +
                            "(110, 'Audi', 'A4', 'Beige', 1), " +
                            "(111, 'Hyundai', 'i30', 'Orange', 1), " +
                            "(112, 'Kia', 'Sportage', 'Brown', 1), " +
                            "(113, 'Mazda', 'CX-5', 'Magenta', 1), " +
                            "(114, 'Skoda', 'Octavia', 'Cyan', 1), " +
                            "(115, 'Fiat', '500', 'Pink', 1), " +
                            "(116, 'Seat', 'Leon', 'Purple', 1), " +
                            "(117, 'Volvo', 'XC40', 'Lime', 1), " +
                            "(118, 'Opel', 'Astra', 'Teal', 1), " +
                            "(119, 'Alfa Romeo', 'Giulia', 'Charcoal', 1);";

                            String sqlStatementRoutes =
                            "INSERT INTO carpoolconnect.routes (id, departure_date, seats, number_small_baggage, number_large_baggage, silence, music_genre, driver_id, car_id, departure_id, destination_id) VALUES " +
                            "(100, '2023-06-01 08:00:00', 4, 1, 2, false, 'Rock', 1, 101, 100, 101), " +
                            "(101, '2023-06-02 09:30:00', 3, 0, 1, true, 'Jazz', 1, 112, 102, 103), " +
                            "(102, '2023-06-03 07:45:00', 2, 2, 0, false, 'Pop', 1, 103, 104, 105), " +
                            "(103, '2023-06-04 18:00:00', 4, 1, 1, true, 'Classical', 1, 114, 106, 107), " +
                            "(104, '2023-06-05 15:30:00', 3, 0, 2, false, 'Hip Hop', 1, 105, 108, 109), " +
                            "(105, '2023-06-06 12:00:00', 2, 1, 0, true, 'Electronic', 1, 106, 110, 111), " +
                            "(106, '2023-06-07 10:15:00', 4, 2, 1, false, 'Reggae', 1, 117, 112, 113), " +
                            "(107, '2023-06-08 20:00:00', 3, 0, 2, true, 'Country', 1, 118, 114, 115), " +
                            "(108, '2023-06-09 05:30:00', 2, 1, 1, false, 'Blues', 1, 109, 116, 117), " +
                            "(109, '2023-06-10 22:45:00', 4, 2, 0, true, 'Soul', 1, 110, 118, 119), " +
                            "(110, '2023-06-11 11:00:00', 3, 1, 2, false, 'Funk', 1, 111, 100, 102), " +
                            "(111, '2023-06-12 16:30:00', 2, 0, 1, true, 'Metal', 1, 112, 103, 105), " +
                            "(112, '2023-06-13 08:15:00', 4, 2, 0, false, 'Punk', 1, 113, 106, 108), " +
                            "(113, '2023-06-14 19:00:00', 3, 1, 1, true, 'Disco', 1, 114, 109, 111), " +
                            "(114, '2023-06-15 14:45:00', 2, 0, 2, false, 'R&B', 1, 115, 112, 114), " +
                            "(115, '2023-06-16 21:30:00', 4, 1, 0, true, 'Gospel', 1, 116, 115, 117), " +
                            "(116, '2023-06-17 06:00:00', 3, 2, 1, false, 'Techno', 1, 117, 118, 100), " +
                            "(117, '2023-06-18 17:15:00', 2, 1, 2, true, 'Folk', 1, 118, 101, 103), " +
                            "(118, '2023-06-19 13:00:00', 4, 0, 1, false, 'Alternative', 1, 119, 104, 106), " +
                            "(119, '2023-06-20 22:30:00', 3, 2, 0, true, 'Opera', 1, 119, 107, 109);";

            //statement.executeUpdate(sqlStatementAddress);
            //statement.executeUpdate(sqlStatementCars);
            //statement.executeUpdate(sqlStatementRoutes);
        }
    }

}
