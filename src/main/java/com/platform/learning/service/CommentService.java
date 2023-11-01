package com.platform.learning.service;

import com.platform.learning.dao.Comment;
import com.platform.learning.dao.Discipline;
import com.platform.learning.dao.Users;
import com.platform.learning.repo.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepo commentRepo;
    private final DisciplineService disciplineService;
    private final UserService userService;
    public Iterable<Comment> getCommentsForDiscipline(Long id){
        return commentRepo.findAllByDiscipline(disciplineService.getDiscipline(id));
    }

    public Iterable<Comment> getCommentsForUser(Long id){
        return commentRepo.findAllByUser(userService.getById(id));
    }
    public void saveComment(Comment comment){
        commentRepo.save(comment);
    }
    public Comment getCommentById(Long id){
        return commentRepo.findById(id).get();
    }

    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }
}
