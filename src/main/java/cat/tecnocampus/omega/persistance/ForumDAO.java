package cat.tecnocampus.omega.persistance;

import org.springframework.jdbc.core.JdbcTemplate;
import cat.tecnocampus.omega.domain.post.Discussion;
import cat.tecnocampus.omega.domain.post.Comment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ForumDAO {

    private JdbcTemplate jdbcTemplate;
    private UserDAO userDAO;

    private final String INSERT_DISCUSSION = "INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, user, hasBest, category,son_type) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
    private final String INSERT_COMMENT = "INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id, username,son_type) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
    private final String SELECT_DISCUSSION = "SELECT * FROM Posts WHERE post_id = ?";
    private final String SELECT_DISCUSSIONS = "SELECT * FROM Posts WHERE son_type = 'Discussion'";
    private final String SELECT_COMMENTS = "SELECT * FROM Comments WHERE post_id = ?";


    public ForumDAO (JdbcTemplate jdbcTemplate, UserDAO userDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDAO=userDAO;
    }

    private Discussion discussionMapper(ResultSet resultSet) throws SQLException {
        Discussion discussion = new Discussion(resultSet.getString("description"), resultSet.getString("title"), userDAO.findByUsername(resultSet.getString("username")));
        return discussion;
    }

    private RowMapper<Comment> commentMapper = (resultSet, i) -> {
        Comment comment = new Comment(resultSet.getString("comment"), userDAO.findByUsername(resultSet.getString("username")), getDiscussion(resultSet.getString("post_id")));
        return comment;
    };

    private RowMapper<Discussion> mapperEager = (resultSet, i) -> {
        Discussion discussion = discussionMapper(resultSet);
        List<Comment> comments = findCommentByDiscussion(discussion.getPostID());
        discussion.addComment(comments);
        return discussion;
    };

    public int insertDAODiscussion(Discussion discussion) {
        return jdbcTemplate.update(INSERT_DISCUSSION,discussion.getPostID(),discussion.getTitle(),discussion.getCreationDay(),discussion.getLikes(),discussion.isEnable(),discussion.getUser(),discussion.hasBest(),"Discussion");
    }

    public int insertDAOComment(Comment comment) {
        return jdbcTemplate.update(INSERT_COMMENT,comment.getCommentID(),comment.isBestComment(),comment.getCreationDay(),comment.getLikes(),comment.isEnable(),comment.getDiscussion(), comment.getUser(),"Comment");
    }
    public List<Comment> findCommentByDiscussion(String id){
        return jdbcTemplate.query(SELECT_COMMENTS,new Object[]{id},commentMapper);
    }

    public List<Discussion> getAllDiscussions(){
        return jdbcTemplate.query(SELECT_DISCUSSIONS,mapperEager);
    }

    public Discussion getDiscussion(String id){
        return jdbcTemplate.queryForObject(SELECT_DISCUSSION,mapperEager,id);
    }

}
