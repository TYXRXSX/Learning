package com.platform.learning.repo;

import com.platform.learning.dao.Comment;
import com.platform.learning.dao.Discipline;
import com.platform.learning.dao.Users;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment,Long> {

    Iterable<Comment> findAllByDiscipline(Discipline discipline);

    Iterable<Comment> findAllByUser(Users users);
}
