package me.ronygomes.reference.rest_huc.service;

import me.ronygomes.reference.rest_huc.domain.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CommentServiceTest {

    private static final String[] POST_COMMENT_1_NAMES = {
            "Eliseo@gardner.biz",
            "Jayne_Kuhic@sydney.com",
            "Nikita@garfield.biz",
            "Lew@alysha.tv",
            "Hayden@althea.biz"
    };

    @Test
    void testFetchAllComments() {
        List<Comment> post1Comments = CommentService.fetchAllComments(1);
        Assertions.assertEquals(5, post1Comments.size());

        for (int i = 0; i < post1Comments.size(); i++) {
            Comment c = post1Comments.get(i);
            Assertions.assertNotNull(c);
            Assertions.assertEquals(i + 1, c.getId());
            Assertions.assertEquals(POST_COMMENT_1_NAMES[i], c.getEmail());
            Assertions.assertNotNull(c.getName());
            Assertions.assertNotNull(c.getBody());
        }

        List<Comment> post2Comments = CommentService.fetchAllComments(2);
        for (int i = 0; i < post2Comments.size(); i++) {
            Comment c = post2Comments.get(i);
            Assertions.assertNotNull(c);
            Assertions.assertEquals(i + 6, c.getId());
            Assertions.assertNotNull(c.getEmail());
            Assertions.assertNotNull(c.getName());
            Assertions.assertNotNull(c.getBody());
        }
    }
}
