package com.example.sidechef.HomeActivity.View.ui.dashboard;

import com.example.sidechef.model.models.Meal;

import java.util.List;

public class ParentItem {

    // Declaration of the variables
    private String ParentItemTitle;
    private List<Meal> ChildItemList;

    // Constructor of the class
    // to initialize the variables
    public ParentItem(
            String ParentItemTitle,
            List<Meal> ChildItemList)
    {

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

    public List<Meal> getChildItemList()
    {
        return ChildItemList;
    }

    public void setChildItemList(
            List<Meal> childItemList)
    {
        ChildItemList = childItemList;
    }
}
