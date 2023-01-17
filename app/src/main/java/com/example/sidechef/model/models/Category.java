package com.example.sidechef.model.models;

public class Category {
    private String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strCategoryDescription;

    public String getIDCategory() { return idCategory; }
    public void setIDCategory(String value) { this.idCategory = value; }

    public Category(String strCategory, String strCategoryThumb) {
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
    }

    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String value) { this.strCategory = value; }

    public String getStrCategoryThumb() { return strCategoryThumb; }
    public void setStrCategoryThumb(String value) { this.strCategoryThumb = value; }

    public String getStrCategoryDescription() { return strCategoryDescription; }
    public void setStrCategoryDescription(String value) { this.strCategoryDescription = value; }
}