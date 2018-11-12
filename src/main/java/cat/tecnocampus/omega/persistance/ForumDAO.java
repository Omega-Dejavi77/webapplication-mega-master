package cat.tecnocampus.omega.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import cat.tecnocampus.omega.domain.post.Discussion;
import cat.tecnocampus.omega.domain.post.Comment;

public class ForumDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_DISCUSSION = "INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, user, hasBest, category,son_type) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
    private final String INSERT_COMMENT = "INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id, username,son_type) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
    private final String SELECT_DISCUSSION = "SELECT * FROM Posts WHERE post_id = ?";

    public ForumDAO (JdbcTemplate jdbcTemplate, ExerciseDAO exerciseDAO) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertDAODiscussion(Discussion discussion) {
        return jdbcTemplate.update(INSERT_DISCUSSION,discussion.getPostID(),discussion.getTitle(),discussion.getCreationDay(),discussion.getLikes(),discussion.isEnable(),discussion.getUser(),discussion.hasBest(),"Discussion");
    }

    public int insertDAOComment(Comment comment) {
        return jdbcTemplate.update(INSERT_COMMENT,comment.getCommentID(),comment.isBestComment(),comment.getCreationDay(),comment.getLikes(),comment.isEnable(),comment.getDiscussion(), comment.getUser(),"Comment");
    }

//    public Discussion getDiscussion(String id){
//        return jdbcTemplate.queryForObject(SELECT_DISCUSSION,id);
//    }

}
