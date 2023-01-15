package com.example.sidechef.model.data.models;

public class Category {
    private String idCategory;
    private String strCategory;
    private String strCategoryThumb;
    private String strCategoryDescription;
    //constructor to test RecVeiw
    public Category(String strCategoryThumb,String strCategory){
        this.strCategory=strCategory;
        this.strCategoryThumb=strCategoryThumb;
    }
    public String getIDCategory() { return idCategory; }
    public void setIDCategory(String value) { this.idCategory = value; }

    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String value) { this.strCategory = value; }

    public String getStrCategoryThumb() { return strCategoryThumb; }
    public void setStrCategoryThumb(String value) { this.strCategoryThumb = value; }

    public String getStrCategoryDescription() { return strCategoryDescription; }
    public void setStrCategoryDescription(String value) { this.strCategoryDescription = value; }
}