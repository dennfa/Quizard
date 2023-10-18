package repos;

import models.MultipleChoiceQuiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultipleChoiceRepo extends MongoRepository<MultipleChoiceQuiz, String> {
}
