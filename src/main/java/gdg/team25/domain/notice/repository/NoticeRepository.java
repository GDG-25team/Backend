package gdg.team25.domain.notice.repository;

import gdg.team25.domain.notice.domain.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeRepository extends MongoRepository<Notice, Long> {
    Notice findById(String id);
}
