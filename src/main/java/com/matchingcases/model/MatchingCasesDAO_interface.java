package com.matchingcases.model;

import java.util.List;

public interface MatchingCasesDAO_interface {
   
	void insert(MatchingCasesVO matchingCasesVO);
    void update(MatchingCasesVO matchingCasesVO);
    MatchingCasesVO getOne(int caseId);
    List<MatchingCasesVO> getAll();
}
