package com.membertag.controller;

import com.membertag.model.MemberTagsVO;
import com.membertag.model.TagVO;
import com.membertag.model.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/tag")
public class TagServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TagService tagService;

    @Override
    public void init() throws ServletException {
        try {
            // 初始化 TagService 以便後續使用
            this.tagService = new TagService();
        } catch (SQLException e) {
            throw new ServletException("初始化 TagService 失敗", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("getMemberTags".equals(action)) {
            // 查詢會員的所有標籤
            int memId = Integer.parseInt(request.getParameter("memId"));
            try {
                List<TagVO> tags = tagService.getTags(memId);
                MemberTagsVO memberTags = new MemberTagsVO(memId, tags);
                request.setAttribute("memberTags", memberTags);
                request.getRequestDispatcher("/back-end/tag/tags.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("查詢會員標籤失敗: " + e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addTag".equals(action)) {
            // 新增標籤
            String tagName = request.getParameter("tagName");
            int tagTypeNo = Integer.parseInt(request.getParameter("tagTypeNo"));
            TagVO tag = new TagVO(0, tagTypeNo, tagName); // 0 代表尚未設定 tagId

            try {
                tagService.addTag(tag);
                response.sendRedirect("tag?action=getMemberTags&memId=" + request.getParameter("memId"));
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("新增標籤失敗: " + e.getMessage());
            }
        } else if ("removeTag".equals(action)) {
            // 刪除標籤
            int tagId = Integer.parseInt(request.getParameter("tagId"));

            try {
                tagService.removeTag(tagId);
                response.sendRedirect("tag?action=getMemberTags&memId=" + request.getParameter("memId"));
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("刪除標籤失敗: " + e.getMessage());
            }
        }
    }
}
