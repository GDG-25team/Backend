package gdg.team25.domain.resume.repository;


import gdg.team25.domain.resume.domain.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResumeRepository extends MongoRepository<Resume, Long> {
    List<Resume> findAllByNoticeId(String noticeId);
}
