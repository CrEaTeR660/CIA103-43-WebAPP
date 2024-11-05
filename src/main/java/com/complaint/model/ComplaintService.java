package com.complaint.model;

import java.sql.Timestamp;
import java.util.List;

public class ComplaintService {

	private ComplaintDAO_interface dao;

	public ComplaintService() {
		dao = new ComplaintJDBCDAO();
	}
//Integer mem_id, Integer case_id,
	public ComplaintVO addComplaint(Integer complaintId,  String complaintCon,
			Timestamp complaintTime, Byte complaintStatus, String complaintResult) {

		ComplaintVO complaintVO = new ComplaintVO();

		complaintVO.setComplaintId(complaintId);
//		complaintVO.setMemberid(mem_id);
//		complaintVO.setCaseid(case_id);
		complaintVO.setComplaintCon(complaintCon);
		complaintVO.setComplaintTime(complaintTime);
		complaintVO.setComplaintStatus(complaintStatus);
		complaintVO.setComplaintResult(complaintResult);
		dao.insert(complaintVO);

		return complaintVO;
	}
//Integer mem_id, Integer case_id,
	public ComplaintVO updateComplaint(Integer complaintId, String complaintCon,
			Timestamp complaintTime, Byte complaintStatus, String complaintResult) {

		ComplaintVO complaintVO = new ComplaintVO();

		complaintVO.setComplaintId(complaintId);
//		complaintVO.setMemberid(mem_id);
//		complaintVO.setCaseid(case_id);
		complaintVO.setComplaintCon(complaintCon);
		complaintVO.setComplaintTime(complaintTime);
		complaintVO.setComplaintStatus(complaintStatus);
		complaintVO.setComplaintResult(complaintResult);
		dao.update(complaintVO);

		return complaintVO;
	}

	public void deleteComplaint(Integer complaintId) {
		dao.delete(complaintId);
	}

	public ComplaintVO getOneComplaint(Integer complaintId) {
		return dao.findByPrimaryKey(complaintId);
	}

	public List<ComplaintVO> getAll() {
		return dao.getAll();
	}
}
