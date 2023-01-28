package com.example.sidechef.HomeActivity.View.ui.planWeek;

import com.example.sidechef.model.models.WeekMeals;

import java.util.List;

public class ParentItem {

    // Declaration of the variables
    private String ParentItemTitle;
    private   List<WeekMeals> ChildItemList;
String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    // Constructor of the class
    // to initialize the variables
    public ParentItem(
            String sort,
            String ParentItemTitle,
            List<WeekMeals> ChildItemList)
    {
this.sort = sort;
        this.ParentItemTitle = ParentItemTitle;
        this.ChildItemList = ChildItemList;
    }

    // Getter and Setter methods
    // for each parameter
    public String getParentItemTitle()
    {
        return ParentItemTitle;
    }

    public void setParentItemTitle(
            String parentItemTitle)
    {
        ParentItemTitle = parentItemTitle;
    }

    public   List<WeekMeals> getChildItemList()
    {
        return ChildItemList;
    }

    public void setChildItemList(
           List<WeekMeals> childItemList)
    {
        ChildItemList = childItemList;
    }


}
