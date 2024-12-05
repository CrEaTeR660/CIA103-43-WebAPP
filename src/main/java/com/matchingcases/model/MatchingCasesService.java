package com.matchingcases.model;


import java.util.List;

public class MatchingCasesService {
    
	private MatchingCasesDAO_interface dao;

    public MatchingCasesService() {
        dao = new MatchingCasesDAO(); // 這裡可以選擇使用 DI 或者其他方式
    }

    // 新增案件
    public void addMatchingCase(MatchingCasesVO matchingCasesVO) {
        dao.insert(matchingCasesVO);
    }

    // 更新案件
    public void updateMatchingCase(MatchingCasesVO matchingCasesVO) {
        dao.update(matchingCasesVO);
    }

    // 根據案件編號取得單一案件
    public MatchingCasesVO getMatchingCaseById(int caseId) {
        return dao.getOne(caseId);
    }

    // 取得所有案件
    public List<MatchingCasesVO> getAllMatchingCases() {
        return dao.getAll();
    }
}

