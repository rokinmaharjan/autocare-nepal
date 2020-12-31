package com.policynepal.autocare.comment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.policynepal.autocare.comment.dto.CommentDto;
import com.policynepal.autocare.comment.model.Comment;
import com.policynepal.autocare.comment.service.CommentService;
import com.policynepal.autocare.common.dto.PageDto;
import com.policynepal.autocare.exceptionhandler.GlobalException;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	public Comment addComment(@RequestBody CommentDto commentDto) {
		return commentService.addComment(commentDto);
	}

	@PutMapping("/{id}")
	public Comment updateComment(@PathVariable("id") String commentId, @RequestBody CommentDto commentDto)
			throws GlobalException {
		return commentService.updateComment(commentId, commentDto);
	}

	@GetMapping
	public PageDto getCommentsWithPaging(
			@RequestParam(required = true) String vehicleId,
			@RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
		return commentService.findCommentsWithPaging(vehicleId, page, size);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteComment(@PathVariable("id") String commentId) throws GlobalException {
		commentService.deleteComment(commentId);
		HashMap<String, Boolean> response = new HashMap<>();
		response.put("success", true);
		
		return response;
	}

}
