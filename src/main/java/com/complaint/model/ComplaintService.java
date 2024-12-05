package com.complaint.model;

import java.sql.Timestamp;
import java.util.List;

public class ComplaintService {

    private ComplaintDAO_interface dao;

    public ComplaintService() {
        dao = new ComplaintDAO();
    }

    // 新增申訴
    public ComplaintVO addComplaint(Integer complaintId, Integer memberId, Integer caseId, String complaintCon,
                                     Timestamp complaintTime, Byte complaintStatus, String complaintResult) {

        ComplaintVO complaintVO = new ComplaintVO();

        complaintVO.setComplaintId(complaintId);
        complaintVO.setMemberId(memberId);
        complaintVO.setCaseId(caseId);
        complaintVO.setComplaintCon(complaintCon);
        complaintVO.setComplaintTime(complaintTime);
        complaintVO.setComplaintStatus(complaintStatus);
        complaintVO.setComplaintResult(complaintResult);
        dao.insert(complaintVO);

        return complaintVO;
    }

    // 更新申訴
    public ComplaintVO updateComplaint(Integer complaintId, Integer memberId, Integer caseId, String complaintCon,
                                        Timestamp complaintTime, Byte complaintStatus, String complaintResult) {

        ComplaintVO complaintVO = new ComplaintVO();

        complaintVO.setComplaintId(complaintId);
        complaintVO.setMemberId(memberId);
        complaintVO.setCaseId(caseId);
        complaintVO.setComplaintCon(complaintCon);
        complaintVO.setComplaintTime(complaintTime);
        complaintVO.setComplaintStatus(complaintStatus);
        complaintVO.setComplaintResult(complaintResult);
        dao.update(complaintVO);

        return complaintVO;
    }

    // 查詢單一申訴
    public ComplaintVO getOneComplaint(Integer complaintId) {
        return dao.findByPrimaryKey(complaintId);
    }

    // 查詢所有申訴
    public List<ComplaintVO> getAll() {
        return dao.getAll();
    }
}
