package com.masoongsoong.FreashKeepie.domain.Post;

public enum Board {

    FREE, RECIPE, SHARE;

    public static Board of (String category){
        if(category.equalsIgnoreCase("free")) return Board.FREE;
        else if(category.equalsIgnoreCase("recipe")) return Board.RECIPE;
        else if(category.equalsIgnoreCase("share")) return Board.SHARE;
        else return null;
    }
}
