package cat.tecnocampus.fgcstations.persistence;

import cat.tecnocampus.fgcstations.application.DTOs.JourneyDTO;
import cat.tecnocampus.fgcstations.application.DTOs.StationDTO;
import cat.tecnocampus.fgcstations.domain.Journey;
import cat.tecnocampus.fgcstations.domain.JourneyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, JourneyId> {




    Optional<Journey> findByOrigin_NameAndDestination_Name(String originName, String destinationName);

    @Query("select new cat.tecnocampus.fgcstations.application.FgcJouneyService(j.id.originName , j.id.destinationName) FROM Journey j")
    List<JourneyDTO> findAllDTO();

}
