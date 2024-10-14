package pl.edu.pjatk.MPR_Projekt.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPR_Projekt.Model.Cat;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat, Long> {
    public List<Cat> findByName(String name);
}
