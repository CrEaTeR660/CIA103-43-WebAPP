package com.complaint.model;

import java.util.*;

public interface ComplaintDAO_interface {
	public void insert(ComplaintVO complaintVO);

	public void update(ComplaintVO complaintVO);

	public ComplaintVO findByPrimaryKey(Integer complaintId);

	public List<ComplaintVO> getAll();
	
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<ComplaintVO> getAll(Map<String, String[]> map); 
}
