package cat.tecnocampus.omega.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import cat.tecnocampus.omega.domain.post.Discussion;
import cat.tecnocampus.omega.domain.post.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ForumDAO {

    private JdbcTemplate jdbcTemplate;
    private UserDAO userDAO;
    private Map<String, Comment> commentMap;
    private final String INSERT_DISCUSSION = "INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, hasBest,son_type,username) VALUES (?, ?, ?, ?,?,?,?,?,?)";
    private final String INSERT_COMMENT = "INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id,username) VALUES (?, ?, ?, ?, ?,?,?,?)";
    private final String INSERT_COMMENT_REPLY = "INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id,username,comment_id_fk) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
    private final String SELECT_DISCUSSION = "SELECT * FROM Posts WHERE post_id = ?";
    private final String SELECT_DISCUSSIONS = "SELECT * FROM Posts WHERE son_type = 'Discussion'";
    private final String SELECT_COMMENTS = "SELECT * FROM Comments WHERE post_id = ?";
    private final String SELECT_COMMENTS_BY_COMMENT = "SELECT * FROM Comments WHERE post_id = ? AND comment_id=?";


    public ForumDAO(JdbcTemplate jdbcTemplate, UserDAO userDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDAO = userDAO;
        commentMap = new HashMap<>();
    }

    private Discussion discussionMapper(ResultSet resultSet) throws SQLException {
        Discussion discussion = new Discussion(resultSet.getString("post_id"), resultSet.getString("description"), resultSet.getString("title"), userDAO.findByUsername(resultSet.getString("username")));
        return discussion;
    }

    private RowMapper<Comment> commentMapper = (resultSet, i) -> {
        Comment comment = new Comment(resultSet.getString("comment_id"), resultSet.getString("comment"), userDAO.findByUsername(resultSet.getString("username")));
        commentMap.put(comment.getCommentID(), comment);
        if (resultSet.getString("comment_id_fk") != null) {
            comment.setReply();
            commentMap.get(resultSet.getString("comment_id_fk")).setSons(comment);
        }
        return comment;
    };

    private RowMapper<Discussion> mapperEager = (resultSet, i) -> {
        Discussion discussion = discussionMapper(resultSet);
        for (Comment c : findCommentByDiscussion(discussion.getPostID())) {
            if (!c.isReply())
                discussion.addComment(c);
        }
        return discussion;
    };

    public int insertDiscussion(Discussion discussion, String username) {
        return jdbcTemplate.update(INSERT_DISCUSSION, discussion.getPostID(), discussion.getTitle(), discussion.getDescription(), discussion.getCreationDay(), discussion.getLikes(), discussion.isEnable(), discussion.hasBest(), "Discussion", username);
    }

    public int insertComment(Comment comment, String username, String id) {
        return jdbcTemplate.update(INSERT_COMMENT, comment.getCommentID(), comment.isBestComment(), comment.getComment(), comment.getCreationDay(), comment.getLikes(), comment.isEnable(), id, username);
    }

    public int insertCommentReply(Comment comment, String username, String id, String reply) {
        return jdbcTemplate.update(INSERT_COMMENT_REPLY, comment.getCommentID(), comment.isBestComment(), comment.getComment(), comment.getCreationDay(), comment.getLikes(), comment.isEnable(), id, username, reply);
    }

    public List<Comment> findCommentByDiscussion(String id) {
        return jdbcTemplate.query(SELECT_COMMENTS, new Object[]{id}, commentMapper);
    }

    public List<Discussion> getAllDiscussions() {
        return jdbcTemplate.query(SELECT_DISCUSSIONS, mapperEager);
    }

    public Discussion findDiscussion(String id) {
        return jdbcTemplate.queryForObject(SELECT_DISCUSSION, new Object[]{id}, mapperEager);
    }

    public List<Comment> findCommentByComment(String id, String comment_id) {
        return jdbcTemplate.query(SELECT_COMMENTS_BY_COMMENT, new Object[]{id, comment_id}, commentMapper);
    }
}
