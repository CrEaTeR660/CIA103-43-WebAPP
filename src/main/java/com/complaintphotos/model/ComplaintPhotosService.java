package com.complaintphotos.model;

import java.util.List;

public class ComplaintPhotosService {
    private ComplaintPhotosDAO_interface dao;

    public ComplaintPhotosService() {
        dao = new ComplaintPhotosDAO();
    }

    public ComplaintPhotosVO addComplaintPhoto(int comId, byte[] comPic, String fileName) {
        // 組裝 VO
        ComplaintPhotosVO vo = new ComplaintPhotosVO();
        vo.setComId(comId);
        vo.setComPic(comPic);
        vo.setFileName(fileName);

        // 呼叫 DAO 進行新增操作
        dao.insert(vo);
        return vo;
    }

    public ComplaintPhotosVO updateComplaintPhoto(int comPicId, int comId, byte[] comPic, String fileName) {
        // 組裝 VO
        ComplaintPhotosVO vo = new ComplaintPhotosVO();
        vo.setComPicId(comPicId);
        vo.setComId(comId);
        vo.setComPic(comPic);
        vo.setFileName(fileName);

        // 呼叫 DAO 進行更新操作
        dao.update(vo);
        return vo;
    }

    public ComplaintPhotosVO getComplaintPhotoById(int comPicId) {
        return dao.findById(comPicId);
    }

    public List<ComplaintPhotosVO> getAllComplaintPhotos() {
        return dao.findAll();
    }
}
