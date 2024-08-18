package com.chami.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVo {
    private int boardId;
    private String username;
    private String title;
    private String content;
    private int views;
    private int likes;
    private String type;
    private int isLocked;
    private String viewPermission;
    private Boolean hasFile;
    private Date created_at;
    private Date updated_at;
    private char use_yn;

    public BoardVo() {
    }

    public BoardVo(int boardId, String username, String title, String content, int views, int likes, String type, int isLocked, String viewPermission, Boolean hasFile, Date created_at, Date updated_at, char use_yn) {
        this.boardId = boardId;
        this.username = username;
        this.title = title;
        this.content = content;
        this.views = views;
        this.likes = likes;
        this.type = type;
        this.isLocked = isLocked;
        this.viewPermission = viewPermission;
        this.hasFile = hasFile;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.use_yn = use_yn;
    }
}

