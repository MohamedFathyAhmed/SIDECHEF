package com.example.sidechef.HomeActivity.View.ui.dashboard;

import com.example.sidechef.model.models.Meal;
import com.example.sidechef.model.models.WeekMeals;

import java.util.List;

public class ParentItem {

    // Declaration of the variables
    private String ParentItemTitle;
    private List<WeekMeals> ChildItemList;

    // Constructor of the class
    // to initialize the variables
    public ParentItem(
            String ParentItemTitle,
            List<WeekMeals> ChildItemList)
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

    public List<WeekMeals> getChildItemList()
    {
        return ChildItemList;
    }

    public void setChildItemList(
            List<WeekMeals> childItemList)
    {
        ChildItemList = childItemList;
    }
}
