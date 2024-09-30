package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.StationDTO;
import cat.tecnocampus.fgcstations.application.DTOs.StationTopFavoriteJourney;
import cat.tecnocampus.fgcstations.domain.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station, String> {

    @Query("""
    SELECT s.name as name, s.longitud as longitud, s.latitud as latitud FROM Station s""")
    List<StationDTO> findAllStationsAsDTO();

    Optional<Station> findByName(String name);
    @Query(""" 
        SELECT s FROM Station s Where s.name = :name
    """)
    Optional<StationDTO> findByNameDTO(String name);


    @Query(value = "SELECT station_name, COUNT(*) AS journey_count FROM (" +
            "SELECT origin_station AS station_name FROM Journeys " +
            "UNION ALL " +
            "SELECT destination_station AS station_name FROM Journeys) AS combined_stations " +
            "GROUP BY station_name " +
            "ORDER BY journey_count DESC",
            nativeQuery = true)
    List<StationTopFavoriteJourney> findStationsOrderedByFavoriteJourneys();



}
