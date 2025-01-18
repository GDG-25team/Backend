package gdg.team25;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@SpringBootTest
public class MongoConnectionTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongoConnectionAndDataInsert() {
        // given
        String testData = "테스트" + LocalDateTime.now();
        TestDocument testDocument = new TestDocument();
        testDocument.setName(testData);

        mongoTemplate.save(testDocument);

        // when
        TestDocument retrievedDoc = mongoTemplate.findOne(
                new Query(Criteria.where("name").is(testData)),
                TestDocument.class
        );

        // then
        assertNotNull(retrievedDoc, "MongoDB 연결 및 데이터 삽입 실패");
        assertEquals(testData, retrievedDoc.getName(), "데이터 값이 예상과 다릅니다.");
    }

    @Document(collection = "testCollection")
    public class TestDocument {

        @Id
        private String id;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
