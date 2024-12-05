package com.complaintphotos.model;

	import java.util.List;

	public interface ComplaintPhotosDAO_interface {
	    
		public void insert(ComplaintPhotosVO vo);
	    public void update(ComplaintPhotosVO vo);
	    public ComplaintPhotosVO findById(int comPicId);
	    public List<ComplaintPhotosVO> findAll();
	}

