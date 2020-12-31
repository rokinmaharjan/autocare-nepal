package com.policynepal.autocare.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.policynepal.autocare.comment.dto.CommentDto;
import com.policynepal.autocare.comment.model.Comment;
import com.policynepal.autocare.comment.model.DeletedComment;
import com.policynepal.autocare.comment.repository.CommentRepository;
import com.policynepal.autocare.comment.repository.DeletedCommentRepository;
import com.policynepal.autocare.common.dto.PageDto;
import com.policynepal.autocare.exceptionhandler.GlobalException;
import com.policynepal.autocare.utils.AuthenticationUtils;
import com.policynepal.autocare.utils.CustomBeanUtils;

@Service
public class CommentService {

	@Autowired
	private AuthenticationUtils authUtils;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private DeletedCommentRepository deletedCommentRepository;

	public Comment addComment(CommentDto commentDto) {
		Comment comment = new Comment();
		CustomBeanUtils.copyNonNullProperties(commentDto, comment);
		comment.setUserId(authUtils.getUserId());

		return commentRepository.save(comment);
	}

	public Comment updateComment(String commentId, CommentDto commentDto) throws GlobalException {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new GlobalException("Comment not found", HttpStatus.BAD_REQUEST));
		
		validateCommentOwner(authUtils.getUserId(), comment);
		
		CustomBeanUtils.copyNonNullProperties(commentDto, comment);

		return commentRepository.save(comment);
	}

	private Comment validateCommentOwner(String userId, Comment comment) throws GlobalException {
		if (userId.equals(comment.getUserId())) {
			return comment;
		}

		throw new GlobalException("Cannot update other user's comment.", HttpStatus.BAD_REQUEST);
	}

	public PageDto findCommentsWithPaging(String vehicleId, Integer page, Integer size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Comment> commentsPage = commentRepository.findByVehicleId(vehicleId, pageRequest);

		PageDto pageDto = new PageDto();
		pageDto.setContent(commentsPage.getContent());
		pageDto.setTotalElements(commentsPage.getTotalElements());
		pageDto.setTotalPages(commentsPage.getTotalPages());
		pageDto.setPageElementCount(commentsPage.getNumberOfElements());

		return pageDto;
	}

	public void deleteComment(String commentId) throws GlobalException {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new GlobalException("Comment not found", HttpStatus.BAD_REQUEST));
		
		if(!authUtils.getUserId().equals(comment.getUserId())) {
			throw new GlobalException("Cannot delete comment added by other user", HttpStatus.BAD_REQUEST);
		}
		
		DeletedComment deletedComment = new DeletedComment(comment);
		deletedCommentRepository.save(deletedComment);
		
		commentRepository.deleteById(commentId);
	}

}
